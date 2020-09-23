package ru.sbrf.atm.server;

import org.springframework.stereotype.Component;

@Component
public class SavingAccount extends Account {
	public SavingAccount(ru.sbrf.atm.enums.ECurrency ECurrency, long ownerNumber) {
		super(ECurrency, ownerNumber);
	}
}
