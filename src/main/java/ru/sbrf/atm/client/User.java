package ru.sbrf.atm.client;

import ru.sbrf.atm.exceptions.CardException;
import ru.sbrf.atm.interfaces.AuthMethod;

import java.util.HashMap;
import java.util.Map;

public class User {
	private Passport passport;
	private Map<String, Card> wallet;

	public User(Passport passport) {
		this.passport = passport;
		wallet = new HashMap<>();
	}

	public void orderCard(ATM atm) {
		Card card = atm.orderCard(passport);
		wallet.put(card.getNumber(), card);
	}

	public boolean autethication(String cardNumber, AuthMethod authMethod, ATM atm) {
		Card card = wallet.get(cardNumber);
		if (card == null)
			throw new CardException("В кошелке нет карты с таким номером.");
        return atm.authentication(card, authMethod);
	}

	public boolean logout(ATM atm) {
		return atm.logout();
	}

	public String getBalance(ATM atm){
		return atm.getBalance();
	}
}
