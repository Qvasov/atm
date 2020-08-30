package ru.sbrf.atm;

import ru.sbrf.atm.client.ATM;
import ru.sbrf.atm.client.User;
import ru.sbrf.atm.client.Card;
import ru.sbrf.atm.client.method.Pin;
import ru.sbrf.atm.server.Bank;

public class Main {
    public static void main(String[] args) {
        //предварительные данные без аргументов, для общего дизайна. Без углубоения в реализацию банка, клиентов, способов авторизации.
        ATM atm = new ATM(new Bank());
        User user = new User(new Card());
        Pin authMethod = new Pin();

        //Сессия
        atm.authentication(user, authMethod);
    }
}
