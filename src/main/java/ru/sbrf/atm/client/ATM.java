package ru.sbrf.atm.client;

import ru.sbrf.atm.server.Bank;

public class ATM {
    private Bank bank;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public boolean authentication(User user, Auth authMethod) {
        bank.authentication(user.getClientNumber(), authMethod);
    }

    public long getBalance(long clientNumber, String accountNumber) {
        return bank.getBalance(clientNumber, accountNumber);
    }

}
