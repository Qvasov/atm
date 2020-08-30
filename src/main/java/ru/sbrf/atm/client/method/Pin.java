package ru.sbrf.atm.client.method;

import ru.sbrf.atm.interfaces.AuthMethod;

public class Pin implements AuthMethod {
	private String pinCode;

	public String getSecret() {
		return pinCode;
	}
}
