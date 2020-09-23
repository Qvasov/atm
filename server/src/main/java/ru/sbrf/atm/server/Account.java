package ru.sbrf.atm.server;

import lombok.Getter;
import ru.sbrf.atm.enums.ECurrency;

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
    private final ru.sbrf.atm.enums.ECurrency ECurrency;

    public Account(ECurrency ECurrency, long ownerNumber) {
        this.number = String.format("%020d", this.getNewAccountNumber());
        this.balanceRub = 0;
        this.balanceKop = 0;
        this.ECurrency = ECurrency;
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
