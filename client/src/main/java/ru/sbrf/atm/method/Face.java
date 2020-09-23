package ru.sbrf.atm.method;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sbrf.atm.interfaces.IAuthMethod;

@Component
@AllArgsConstructor
public class Face implements IAuthMethod {
	private String faceCode;

	@Override
	public String getCode() {
		return faceCode;
	}
}
