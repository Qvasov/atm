package ru.sbrf.atm.server;

import ru.sbrf.atm.client.Auth;

import java.util.LinkedList;
import java.util.Set;

public class Client {
    private long number;
    private Set<Account> accounts;
    private Set<Auth> authMethods;

    public Set<Auth> getAuthMethods() {
        return authMethods;
    }
}
