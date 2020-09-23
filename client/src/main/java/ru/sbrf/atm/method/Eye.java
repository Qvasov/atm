package ru.sbrf.atm.method;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sbrf.atm.interfaces.IAuthMethod;

@Component
@AllArgsConstructor
public class Eye implements IAuthMethod {
	private String eyeCode;

	@Override
	public String getCode() {
		return eyeCode;
	}
}
