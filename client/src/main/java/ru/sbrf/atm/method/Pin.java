package ru.sbrf.atm.method;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sbrf.atm.interfaces.IAuthMethod;

@Component
@AllArgsConstructor
public class Pin implements IAuthMethod {
	private String pinCode;

	@Override
	public String getCode() {
		return pinCode;
	}
}
