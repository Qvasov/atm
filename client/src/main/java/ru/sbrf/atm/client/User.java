package ru.sbrf.atm.client;

import org.springframework.stereotype.Component;
import ru.sbrf.atm.exceptions.CardException;
import ru.sbrf.atm.interfaces.IAuthMethod;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Component
public class User {
	@NotNull
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

	public boolean autethication(String cardNumber, IAuthMethod IAuthMethod, ATM atm) {
		Card card = wallet.get(cardNumber.replaceAll("\\s+", ""));
		if (card == null)
			throw new CardException("В кошелке нет карты с таким номером.");
        return atm.authentication(card, IAuthMethod);
	}

	public boolean logout(ATM atm) {
		return atm.logout();
	}

	public String getBalance(ATM atm){
		return atm.getBalance();
	}
}
