package ru.sbrf.atm.client;

public class User {
    private Card card;

    public Card getCard() {
        return card;
    }

    public User(Card card) {
        this.card = card;
    }
}
