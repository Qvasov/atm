package ru.sbrf.atm.client.method;

import ru.sbrf.atm.interfaces.AuthMethod;

public class Face implements AuthMethod {
	private String faceCode;

	@Override
	public String getCode() {
		return faceCode;
	}
}
