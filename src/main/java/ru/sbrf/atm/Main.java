package ru.sbrf.atm;

import ru.sbrf.atm.client.ATM;
import ru.sbrf.atm.client.User;
import ru.sbrf.atm.server.Bank;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM(new Bank());

        User user = new User();
    }
}
