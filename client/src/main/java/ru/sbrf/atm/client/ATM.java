package ru.sbrf.atm.client;

import org.springframework.stereotype.Component;
import ru.sbrf.atm.enums.ECurrency;
import ru.sbrf.atm.interfaces.IAuthMethod;
import ru.sbrf.atm.exceptions.ATMException;
import ru.sbrf.atm.exceptions.AuthMethodException;
import ru.sbrf.atm.exceptions.ClientException;
import ru.sbrf.atm.exceptions.PasswordException;
import ru.sbrf.atm.server.Bank;

import javax.validation.constraints.NotNull;

@Component
public class ATM {
	@NotNull
	private Bank bank;
	private Card insertedCard;

	public ATM(Bank bank) {
		this.bank = bank;
	}

	/**
	 * Метод для аутификация на уровне банкомата.
	 * <p>
	 * <p>
	 * Получает из банка, зарегестрированные методы аутентификации на конкретного пользователя,
	 * и сравнивает метод аутентификации пользователя с зарегестрированными. Если способ зарегестрирован,
	 * происходит аутентификации на уровне банка. В другом случае - ошибка аутентификации.
	 *
	 * @param card       карта, с которой пользователь производит аутентификацию
	 * @param IAuthMethod метод, с помощью которого происходит аутентификация
	 * @return true - если аутентификация прошла успешна. false в обратном случае.
	 */
	public boolean authentication(Card card, IAuthMethod IAuthMethod) {
		boolean authResult = false;
		insertedCard = card;

		try {
			authResult = bank.authentication(card.getClientNumber(), IAuthMethod);
		} catch (ClientException c) {
			//Код обработки внешнего клиента (из другого банка)
		} catch (AuthMethodException a) {
			// Код обработки не существующего способа аутентификации.
		} catch (PasswordException p) {
			// Неправильный пин. Код обработки неправельного введеного пароля.
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

	public Card orderCard(ECurrency ECurrency, Passport passport) {
		return bank.createCard(ECurrency, passport);
	}

	public Card orderCard(Passport passport) {
		return orderCard(ECurrency.RUR, passport);
	}

	public String getBalance() {
		return bank.getBalance(insertedCard.getClientNumber(), insertedCard.getAccountNumber());
	}
}
