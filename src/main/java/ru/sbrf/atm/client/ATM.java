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

	/**
	 * Метод для аутификация на уровне банкомата.
	 *
	 *
	 * Получает из банка, зарегестрированные методы аутентификации на конкретного пользователя,
	 * и сравнивает метод аутентификации пользователя с зарегестрированными. Если способ зарегестрирован,
	 * происходит аутентификации на уровне банка. В другом случае - ошибка аутентификации.
	 *
	 * @param user пользователь, который производит аутентификацию
	 * @param authMethod метод, с помощью которого происходит аутентификация
	 * @return true - если аутентификация прошла успешна. false в обратном случае.
	 */
	public boolean authentication(User user, AuthMethod authMethod) {
		boolean authResult = false;
		try {
			// Получение зарегестрированных способов аутентификации с сервера для конкретного клиента, если он клиент нашего банка
			Set<AuthMethod> userAuthMethods = bank.getUserAuthMethods(user.getCard().getClientNumber());
			try {
				// Аутентификация клиента с помощью выбранного метода аутентификации
				if (userAuthMethods.contains(authMethod)) {
					//если аутентификация прошла успешно authResult устанавливается в true
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

	public String getBalance(User user) {
		return bank.getBalance(user.getCard().getClientNumber(), user.getCard().getAccountNumber());
	}

}
