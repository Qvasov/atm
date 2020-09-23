package ru.sbrf.atm.client;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * Создаёт карту, которая содержит номер счета и номер клинта которому принаджлежит.
 *
 * Если не указывать номер клиента в банке, то создатся новый номер клиента.
 */

@Component
@Getter
public class Card {

    private String number;
    private String accountNumber;
    private long clientNumber;

    public Card(String cardNumber, long clientNumber, String accountNumber) {
        this.number = cardNumber;
        this.clientNumber = clientNumber;
        this.accountNumber = accountNumber;
    }
}
