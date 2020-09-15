package ru.sbrf.atm.client;

import ru.sbrf.atm.exceptions.ATMException;
import ru.sbrf.atm.interfaces.AuthMethod;
import ru.sbrf.atm.exceptions.AuthMethodException;
import ru.sbrf.atm.exceptions.ClientException;
import ru.sbrf.atm.exceptions.PasswordException;
import ru.sbrf.atm.server.Bank;
import ru.sbrf.atm.server.Currency;

import java.util.Set;

public class ATM {
	private Bank bank;
	private Card insertedCard;

	public ATM(Bank bank) {
		this.bank = bank;
	}

	/**
	 * Метод для аутификация на уровне банкомата.
	 *
	 *
	 * Получает из банка, зарегестрированные методы аутентификации на конкретного пользователя,
	 * и сравнивает метод аутентификации пользователя с зарегестрированными. Если способ зарегестрирован,
	 * происходит аутентификации на уровне банка. В другом случае - ошибка аутентификации.
	 *
	 * @param card карта, с которой пользователь производит аутентификацию
	 * @param authMethod метод, с помощью которого происходит аутентификация
	 * @return true - если аутентификация прошла успешна. false в обратном случае.
	 */
	public boolean authentication(Card card, AuthMethod authMethod) {
		boolean authResult = false;
		insertedCard = card;
		try {
			// Получение зарегестрированных способов аутентификации с сервера для конкретного клиента, если он клиент нашего банка
			Set<AuthMethod> userAuthMethods = bank.getUserAuthMethods(card.getClientNumber());
			try {
				// Аутентификация клиента с помощью выбранного метода аутентификации
				if (userAuthMethods.contains(authMethod)) {
					//если аутентификация прошла успешно authResult устанавливается в true
					authResult = bank.authentication(card.getClientNumber(), authMethod);
				} else
					throw new AuthMethodException("Ошибка способа аутентификации");
			} catch (PasswordException p) {
				// Неправильный пин. Код обработки неправельного введеного пароля.
			}
		} catch (ClientException c) {
			//Код обработки внешнего клиента (из другого банка)
		}
		return authResult;
	}

	public boolean logout() {
		if (insertedCard != null) {
			insertedCard = null;
			return true;
		} else
			throw new ATMException("Карта не вставлена в банкомат");
	}

	public Card orderCard(Currency currency, Passport passport){
		return bank.createCard(currency, passport);
	}
	public Card orderCard(Passport passport){
		return orderCard(Currency.RUR, passport);
	}

	public String getBalance() {
		return bank.getBalance(insertedCard.getClientNumber(), insertedCard.getAccountNumber());
	}

}
