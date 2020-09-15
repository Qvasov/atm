package ru.sbrf.atm;

import ru.sbrf.atm.client.ATM;
import ru.sbrf.atm.client.Passport;
import ru.sbrf.atm.client.User;
import ru.sbrf.atm.client.Card;
import ru.sbrf.atm.client.method.Pin;
import ru.sbrf.atm.server.Bank;
import ru.sbrf.atm.server.Currency;

public class Main {
    public static void main(String[] args) {
        //предварительные данные без аргументов, для общего дизайна. Без углубоения в реализацию банка, клиентов, способов авторизации.
        Bank bank = new Bank("ПАО Сбербанк");
        ATM atm = new ATM(bank);
        User user = new User(new Passport());
        user.orderCard(atm);

        if (user.autethication("1234 4321 5678 8765", new Pin("1234"), atm)){
            String balance = user.getBalance(atm);
            System.out.println(balance);
            user.logout(atm);
        }

        // сделать в карте класс реквизиты (номер счета номер пользователя)
        //функцианальный интерфейс для authmethod
        //comtains key переделать так как гет возвращает null
        //проанализировать null в в методе getClient класса Bank
        //currency name а не to String

    }
}
