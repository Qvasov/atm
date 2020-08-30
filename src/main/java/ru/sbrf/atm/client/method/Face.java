package ru.sbrf.atm.client.method;

import ru.sbrf.atm.interfaces.AuthMethod;

public class Face implements AuthMethod {
	private String faceCode;

	public String getSecret() {
		return faceCode;
	}
}
