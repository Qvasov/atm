package ru.sbrf.atm.client;

import ru.sbrf.atm.server.Bank;

public class ATM {
    private Bank bank;
    private User user; //текущий юзер

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public long getBalance(long clientNumber, String accountNumber) {
       return bank.getBalance(clientNumber, accountNumber);
    }

    public void authorization(User user) {
        this.user = user;
    }
    public void authentication(Auth method) {

    }
}
