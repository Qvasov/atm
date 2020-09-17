package ru.sbrf.atm.client.method;

import lombok.AllArgsConstructor;
import ru.sbrf.atm.interfaces.AuthMethod;

@AllArgsConstructor
public class Face implements AuthMethod {
	private String faceCode;

	@Override
	public String getCode() {
		return faceCode;
	}
}
