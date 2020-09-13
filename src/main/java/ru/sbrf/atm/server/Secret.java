package ru.sbrf.atm.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Secret {
	@Getter
	private final String secret;

	/**
	 * Условный шифровальщик кода (ПИН, ФЕЙС, ПАЛЕЦ)
	 * @param code код понимающий человек
	 * @return зашифрованная хеш-функция кода
	 */
	public static Secret generateSecret(String code) {
		//Условная зашифровка кода
		String hash = code;
		return new Secret(hash);
	}

}
