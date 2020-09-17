package ru.sbrf.atm;

import ru.sbrf.atm.client.ATM;
import ru.sbrf.atm.client.Passport;
import ru.sbrf.atm.client.User;
import ru.sbrf.atm.client.method.Pin;
import ru.sbrf.atm.interfaces.AuthMethod;
import ru.sbrf.atm.server.Bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

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
