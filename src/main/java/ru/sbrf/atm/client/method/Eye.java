package ru.sbrf.atm.client.method;

import ru.sbrf.atm.interfaces.AuthMethod;

public class Eye implements AuthMethod {
	private String eyeCode;

	@Override
	public String getCode() {
		return eyeCode;
	}
}
`