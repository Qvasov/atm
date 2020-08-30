package ru.sbrf.atm.server;

import ru.sbrf.atm.client.Card;

import java.util.Set;

public class Account {
    private final String number;
    private final Client owner;
    private final Currency currency;
    private long balance;
    private Set<Card> cards;

    public Account(String number, Currency currency, Client owner) {
        this.number = number;
        this.currency = currency;
        this.owner = owner;
    }
}
