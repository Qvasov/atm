package ru.sbrf.atm.server;

import org.springframework.stereotype.Component;

@Component
public class CurrentAccount extends Account {
	public CurrentAccount(ru.sbrf.atm.enums.ECurrency ECurrency, long ownerNumber) {
		super(ECurrency, ownerNumber);
	}
}
