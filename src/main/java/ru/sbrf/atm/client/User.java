package ru.sbrf.atm.client;

public class User {
    private long clientNumber;

    public long getClientNumber() {
        return clientNumber;
    }

    public User(long clientNumber) {
        this.clientNumber = clientNumber;
    }
}
