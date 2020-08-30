package ru.sbrf.atm.server;

import ru.sbrf.atm.client.Auth;
import ru.sbrf.atm.exceptions.AuthMethodException;
import ru.sbrf.atm.exceptions.ClientException;

import java.util.HashMap;
import java.util.HashSet;

public class Bank {
    private HashMap<Long, Client> clients;
    private HashSet<Account> accounts;

    public void authentication(long clientNumber, Auth authMethod) throws ClientException, AuthMethodException {
        Client client = clients.get(new Long(clientNumber));
        if (client != null) {

            if (client.getAuthMethods().contains(authMethod)) {

            } else
                throw new AuthMethodException("Данный метод аутеннтификации клиента не зарегистрирован");

        } else
            throw new ClientException("Данного клиента в банке не существует");
    }

    public long getBalance(long clientNumber, String accountNumber) {
        //1. валидайия запросы
        //2.
    }
}
