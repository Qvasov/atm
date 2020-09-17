package ru.sbrf.atm.server;

import lombok.Getter;
import ru.sbrf.atm.client.method.Pin;
import ru.sbrf.atm.interfaces.AuthMethod;

import java.util.HashMap;
import java.util.Map;

public class Client<E extends Account> {
	@Getter
	private long number;
	private Map<String, Account> accounts;
	private Map<Class<? extends AuthMethod>, Secret> secrets;

	public Client(long clientNumber) {
		this.number = clientNumber;
		this.accounts = new HashMap<>();
		this.secrets = new HashMap<>();
	}

	public void putAccount(Account account) {
		accounts.put(account.getNumber(), account);
	}

	/**
	 * Возвращает баланс с конкретного счета
	 *
	 * @param accountNumber номер счета
	 * @return Значение баланса с валютой
	 */
	public String getAccountBalance(String accountNumber) {
		Account account = accounts.get(accountNumber);
		return String.format("%s,%s %s",
				account.getBalanceRub(),
				account.getBalanceKop(),
				account.getCurrency().toString());
	}

	public void putSecret(AuthMethod authMethod) {
		if (!secrets.containsKey(authMethod.getClass()))
			secrets.put(authMethod.getClass(), Secret.generateSecret(authMethod.getCode()));
		else
			secrets.replace(authMethod.getClass(), Secret.generateSecret(authMethod.getCode()));
	}

	public Secret getAuthMethod(AuthMethod authMethod) {
		return secrets.get(authMethod.getClass());
	}
}
