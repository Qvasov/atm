package ru.sbrf.atm;

import client.ATM;
import client.Passport;
import client.User;
import client.method.Pin;
import ru.sbrf.atm.server.Bank;

public class Main {
	public static void main(String[] args) {
		//предварительные данные без аргументов, для общего дизайна. Без углубоения в реализацию банка, клиентов, способов авторизации.
		Bank bank = new Bank("ПАО Сбербанк");
		ATM atm = new ATM(bank);
		User user = new User(new Passport("0102","334455"));
		user.orderCard(atm);

		if (user.autethication("0000 0000 0000 0001", new Pin("1234"), atm)) {
			String balance = user.getBalance(atm);
			System.out.println(balance);
			user.logout(atm);
		}
	}
}
