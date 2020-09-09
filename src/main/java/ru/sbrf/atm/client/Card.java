package ru.sbrf.atm.client;

import lombok.Getter;
import ru.sbrf.atm.client.method.Pin;
import ru.sbrf.atm.server.Account;
import ru.sbrf.atm.server.Bank;
import ru.sbrf.atm.server.Currency;
import ru.sbrf.atm.server.SavingAccount;

/**
 * Создаёт карту, которая содержит номер счета и номер клинта которому принаджлежит.
 *
 * Если не указывать номер клиента в банке, то создатся новый номер клиента.
 */
public class Card {

    private String number;
    @Getter
    private String accountNumber;
    @Getter
    private long clientNumber;

    public Card(Bank bank, Long clientNumber) {
        this.number = String.format("%016d", bank.getNewCardNumber());
        this.clientNumber = bank.getClient(clientNumber).getNumber();
        SavingAccount account = new SavingAccount(Currency.RUR, bank.getClient(clientNumber).getNumber());
        this.accountNumber = account.getNumber();
        bank.getClient(clientNumber).putAccount(account);
        bank.getClient(clientNumber).putSecret(new Pin("1234"));
    }

    public Card(Bank bank) {
        this(bank, null);
    }
}
