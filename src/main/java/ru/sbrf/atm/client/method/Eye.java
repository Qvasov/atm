package ru.sbrf.atm.client.method;

import lombok.AllArgsConstructor;
import ru.sbrf.atm.interfaces.AuthMethod;

@AllArgsConstructor
public class Eye implements AuthMethod {
	private String eyeCode;

	@Override
	public String getCode() {
		return eyeCode;
	}
}
