package ru.sbrf.atm.server;

import ru.sbrf.atm.interfaces.AuthMethod;
import ru.sbrf.atm.exceptions.ClientException;
import ru.sbrf.atm.exceptions.PasswordException;

import java.util.*;

public class Bank {
    private final String name;
    private long cardNumbers;
    private static long clientNumbers;
    private static Map<Long, Client<? extends Account>> clients;

    public Bank(String name) {
        this.name = name;
        if (clients == null)
            clients = new HashMap<>();
    }

    /**
     *
     * @return Получает новый номер карты
     */
    public long getNewCardNumber() {
        return ++cardNumbers;
    }

    /**
     *
     * @return Получает новый номер клиента
     */
    public static long getNewClientNumber() {
        return ++clientNumbers;
    }

    /**
     * Получает клиента по номеру клинета
     * @param clientNumber номер клиента
     * @return Полученный клиент из базы банка
     */
    public Client<? extends Account> getClient(Long clientNumber) {
        if (clientNumber == null)
            return createClient();
        else if (clients.containsKey(clientNumber))
            return clients.get(clientNumber);
        else
            throw new ClientException("Клиента с таким номером не существует");
    }

    /**
     * Создаёт клиента и сохраняет его в базе банка
     * @return Созданный клиент
     */
    public Client<? extends Account> createClient() {
        Client<SavingAccount> client = new Client<>();
        clients.put(client.getNumber(), client);
        return client;
    }

    /**
     * Получение зарегестрированых методов аутентификации пользователя по номеру клиента в банке, которому принадлежит ATM
     *
     * @param clientNumber номер клиента в банке
     * @return Множество зарегестрированных способов аутентификации
     */
    public Set<AuthMethod> getUserAuthMethods(long clientNumber) {
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
