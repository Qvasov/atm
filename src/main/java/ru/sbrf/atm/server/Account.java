package ru.sbrf.atm.server;

import lombok.Getter;

public abstract class Account {
    private long accountNumbers;
    @Getter
    private final String number;
    private final long ownerNumber;
    @Getter
    private long balanceRub;
    @Getter
    private long balanceKop;
    @Getter
    private final Currency currency;

    public Account(Currency currency, long ownerNumber) {
        this.number = String.format("%020d", this.getNewAccountNumber());
        this.balanceRub = 0;
        this.balanceKop = 0;
        this.currency = currency;
        this.ownerNumber = ownerNumber;
    }

    /**
     *
     * @return Получает новый номер карты
     */
    public long getNewAccountNumber() {
        return ++accountNumbers;
    }
}
