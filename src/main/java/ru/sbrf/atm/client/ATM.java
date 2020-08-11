package ru.sbrf.atm.client;

import ru.sbrf.atm.server.Bank;

public class ATM {
    private Bank bank;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public long getBalance(long clientNumber, String accountNumber) {

       return bank.getBalance(clientNumber, accountNumber);
    }

}
