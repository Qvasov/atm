package ru.sbrf.atm.server;

import ru.sbrf.atm.interfaces.AuthMethod;
import ru.sbrf.atm.exceptions.AuthMethodException;
import ru.sbrf.atm.exceptions.ClientException;
import ru.sbrf.atm.exceptions.PasswordException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Bank {
    private HashMap<Long, Client> clients;
    private HashSet<Account> accounts;

    public Set<AuthMethod> getUserAuthMethods(long clientNumber) {
        if (clients.containsKey(clientNumber)) { //нужна ли обёртка? new Long()
            return clients.get(clientNumber).getAuthMethods();
        } else
            throw new ClientException("Клиент другого банка");
    }

    public boolean authentication(long clientNumber, AuthMethod authMethod) {
        Client client = clients.get(clientNumber);
        if (client.getAuthMethod(authMethod).getSecret().equals(authMethod.getSecret())) {
            return true;
        } else
            throw new PasswordException("Секреты не совападают");
    }

    public void createClient {}

    public long getBalance(long clientNumber, String accountNumber) {
        //1. валидация запроса
        //2.
    }
}
