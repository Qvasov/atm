package ru.sbrf.atm.client;

import ru.sbrf.atm.server.Account;

public class Card {
    private int number;
    private long clientNumber;
    private Account account;

    public long getClientNumber() {
        return clientNumber;
    }

}
