package ru.sbrf.atm.server;

import ru.sbrf.atm.client.Card;
import ru.sbrf.atm.client.Passport;
import ru.sbrf.atm.client.method.Pin;
import ru.sbrf.atm.exceptions.AuthMethodException;
import ru.sbrf.atm.exceptions.ClientException;
import ru.sbrf.atm.exceptions.PasswordException;
import ru.sbrf.atm.interfaces.AuthMethod;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

public class Bank {
	@NotBlank
	private final String name;
	private Map<Passport, Client<? extends Account>> clients;
	private long cardNumbers;
	private long clientNumbers;

	public Bank(String name) {
		this.name = name;
		this.clients = new HashMap<>();
	}

	/**
	 * Метод создаёт карту по паспорту клиента и наименованию валюты
	 *
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
	 * @return Получает новый номер карты
	 */
	private long getNewCardNumber() {
		return ++cardNumbers;
	}

	/**
	 * Получает клиента по данным паспорта, если его нет то создаёт кладёт клиента в базу банка
	 *
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
	 *
	 * @return Созданный клиент
	 */
	public Client<? extends Account> createClient(Passport passport) {
		Client<SavingAccount> client = new Client<>(getNewClientNumber());
		clients.put(passport, client);
		return client;
	}

	/**
	 * @return Получает новый номер клиента
	 */
	public long getNewClientNumber() {
		return ++this.clientNumbers;
	}

	/**
	 * Аутентификация в банке на стороне сервера
	 *
	 * @param clientNumber Номер клиента в банке
	 * @param authMethod   Способ аутентификации
	 * @return true в случае успеха
	 */
	public boolean authentication(long clientNumber, AuthMethod authMethod) {
		// Делаем обход по значениям
		for (Client<? extends Account> client : clients.values()) {
			if (client.getNumber() == clientNumber) {
				//Если метод аутентификации не зарегистрирован то выбрасывается исключение
				if (client.getAuthMethod(authMethod) != null) {
					if (client.getAuthMethod(authMethod).getSecret().equals(authMethod.getCode())) {
						return true;
					} else
						throw new PasswordException("Секреты не совападают");
				} else
					throw new AuthMethodException("Ошибка способа аутентификации");
			}
		}
		throw new ClientException("Данного клиента в банке не существует");
	}

	/**
	 * Метод возвращения баланса счета
	 *
	 * @param clientNumber  номер клиента
	 * @param accountNumber номер счета
	 * @return Сумма на счете
	 */
	public String getBalance(long clientNumber, String accountNumber) {
		for (Client<? extends Account> client : clients.values()) {
			if (client.getNumber() == clientNumber) {
				return client.getAccountBalance(accountNumber);
			}
		}
		throw new ClientException("Данного клиента в банке не существует");
	}
}
