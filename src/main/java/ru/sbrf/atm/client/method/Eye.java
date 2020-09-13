package ru.sbrf.atm.client.method;

import ru.sbrf.atm.interfaces.AuthMethod;

public class Eye implements AuthMethod {
	String eyeCode;

	public String getCode() {
		return eyeCode;
	}
}
