package ru.sbrf.atm.server;

import lombok.Getter;
import ru.sbrf.atm.interfaces.AuthMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Client<E extends Account> {
	@Getter
	private long number;
	private Map<String, Account> accounts;
	private Map<AuthMethod, Secret> secrets;

	public Client() {
		this.number = Bank.getNewClientNumber();
		this.accounts = new HashMap<>();
		this.secrets = new HashMap<>();
	}

	public void putAccount(Account account) {
		accounts.put(account.getNumber(), account);
	}

	/**
	 * Возвращает баланс с конкретного счета
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
		if (!secrets.containsKey(authMethod))
			secrets.put(authMethod, Secret.generateSecret(authMethod.getCode()));
		else
			secrets.replace(authMethod, Secret.generateSecret(authMethod.getCode()));
	}

	public Set<AuthMethod> getAuthMethods() {
		return secrets.keySet();
	}

	public Secret getAuthMethod(AuthMethod authMethod) {
		return secrets.get(authMethod);
	}
}
