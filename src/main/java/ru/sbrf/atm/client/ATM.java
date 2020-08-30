package ru.sbrf.atm.client;

import ru.sbrf.atm.interfaces.AuthMethod;
import ru.sbrf.atm.exceptions.AuthMethodException;
import ru.sbrf.atm.exceptions.ClientException;
import ru.sbrf.atm.exceptions.PasswordException;
import ru.sbrf.atm.server.Bank;

import java.util.Set;

public class ATM {
	private Bank bank;

	public ATM(Bank bank) {
		this.bank = bank;
	}

	public boolean authentication(User user, AuthMethod authMethod) {
		boolean authResult = false;
		try {
			// Получение зарегестрированных способов аутентификации с сервера для конкретного клиента, если он клиент нашего банка
			Set<AuthMethod> userAuthMethods = bank.getUserAuthMethods(user.getCard().getClientNumber());
			try {
				// Аутентификация клиента с помощью выбранного метода аутентификации
				if (userAuthMethods.contains(authMethod)) {
					//Возвращает true если аутентификация прошла успешно
					authResult = bank.authentication(user.getCard().getClientNumber(), authMethod);
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

	public long getBalance(long clientNumber, String accountNumber) {
		return bank.getBalance(clientNumber, accountNumber);
	}

}
