package ru.sbrf.atm;

import ru.sbrf.atm.client.ATM;
import ru.sbrf.atm.client.User;
import ru.sbrf.atm.client.Card;
import ru.sbrf.atm.client.method.Pin;
import ru.sbrf.atm.server.Bank;

public class Main {
    public static void main(String[] args) {
        //предварительные данные без аргументов, для общего дизайна. Без углубоения в реализацию банка, клиентов, способов авторизации.
        Bank bank = new Bank("ПАО Сбербанк");
        User user = new User(new Card(bank));
        ATM atm = new ATM(bank);
        Pin authMethod = new Pin("1234");

        //указать пинкод при создании карты
        
        //переделать хранение в банке счетов. 1 сущность != несколько массивов 21:02
        //стрим клиенты стрим сечта работа
        //optional почитать
        //Collections почитать

        //дополнить в резюме про автоматизацию тестирования devops 21:40 урок 14 или 15

        //Сессия
        if (atm.authentication(user, authMethod))
            atm.getBalance(user);
    }
}
