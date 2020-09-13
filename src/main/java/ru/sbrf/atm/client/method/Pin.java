package ru.sbrf.atm.client.method;

import lombok.AllArgsConstructor;
import ru.sbrf.atm.interfaces.AuthMethod;

@AllArgsConstructor
public class Pin implements AuthMethod {
	private String pinCode;

	public String getCode() {
		return pinCode;
	}
}
