package ru.sbrf.atm.server;

import ru.sbrf.atm.interfaces.AuthMethod;

import java.util.Map;
import java.util.Set;

public class Client {
	private long number;
	private Set<Account> accounts;
	private Map<AuthMethod, Secret> secrets;

	public Client(long number, Map<AuthMethod, Secret> secrets) {
		this.number = number;
	}

	public Set<AuthMethod> getAuthMethods() {
		return secrets.keySet();
	}

	public Secret getAuthMethod(AuthMethod authMethod) {
		return secrets.get(authMethod);
	}
}
