package ru.sbrf.atm.server;

import ru.sbrf.atm.client.Card;
import ru.sbrf.atm.client.Passport;
import ru.sbrf.atm.client.method.Pin;
import ru.sbrf.atm.interfaces.AuthMethod;
import ru.sbrf.atm.exceptions.ClientException;
import ru.sbrf.atm.exceptions.PasswordException;

import java.util.*;

public class Bank {
    private final String name;
    private static Map<Passport, Client<? extends Account>> clients;

    private long cardNumbers;
    private long clientNumbers;

    public Bank(String name) {
        this.name = name;
        if (clients == null)
            clients = new HashMap<>();
    }

    /**
     * Метод создаёт карту по паспорту клиента и наименованию валюты
     * @param currency валюта счёта
     * @param passport паспорт клиента
     * @return созданную карту
     */
    public Card createCard(Currency currency, Passport passport) {
        String cardNumber = String.format("%016d", this.getNewCardNumber());
        Client<? extends Account> client = this.getClient(passport);
        SavingAccount account = new SavingAccount(currency, client.getNumber());
        client.putAccount(account);
        client.putSecret(new Pin("1234"));
        return new Card(cardNumber, client.getNumber(), account.getNumber());
    }

    public Card createCard(Passport passport) {
        return createCard(Currency.RUR, passport);
    }

    /**
     *
     * @return Получает новый номер карты
     */
    private long getNewCardNumber() {
        return ++cardNumbers;
    }

    /**
     * Получает клиента по данным паспорта, если его нет то создаёт кладёт клиента в базу банка
     * @param passport паспорт клиента
     * @return Полученный клиент из базы банка
     */
    public Client<? extends Account> getClient(Passport passport) {
        if (clients.containsKey(passport))
            return clients.get(passport);
        else
            return createClient(passport);
    }

    /**
     * Создаёт клиента и сохраняет его в базе банка
     * @return Созданный клиент
     */
    public Client<? extends Account> createClient(Passport passport) {
        Client<SavingAccount> client = new Client<>(getNewClientNumber());
        clients.put(passport, client);
        return client;
    }

    /**
     *
     * @return Получает новый номер клиента
     */
    public long getNewClientNumber() {
        return this.clientNumbers++;
    }

    /**
     * Получение зарегестрированых методов аутентификации пользователя по номеру клиента в банке, которому принадлежит ATM
     *
     * @param clientNumber номер клиента в банке
     * @return Множество зарегестрированных способов аутентификации
     */
    public Set<AuthMethod> getUserAuthMethods(long clientNumber) {
        //TODO
        if (clients.containsKey(clientNumber)) {
            return clients.get(clientNumber).getAuthMethods();
        } else
            throw new ClientException("Клиент другого банка");
    }

    /**
     * Аутентификация в банке на стороне сервера
     *
     * @param clientNumber Номер клиента в банке
     * @param authMethod Способ аутентификации
     * @return true в случае успеха. false в случае неудачи.
     */
    public boolean authentication(long clientNumber, AuthMethod authMethod) {
        Client<? extends Account> client = clients.get(clientNumber);
        if (authMethod.getCode().equals(client.getAuthMethod(authMethod).getSecret())) {
            return true;
        } else
            throw new PasswordException("Секреты не совападают");
    }

    /**
     * Метод возвращения баланса счета
     * @param clientNumber номер клиента
     * @param accountNumber номер счета
     * @return Сумма на счете
     */
    public String getBalance(long clientNumber, String accountNumber) {
        if (clients.containsKey(clientNumber)) {
            Client<? extends Account> client = clients.get(clientNumber);
            return client.getAccountBalance(accountNumber);
        } else
            throw new ClientException("Данного клиента в банке не существует");
    }
}
