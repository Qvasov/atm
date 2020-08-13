package ru.sbrf.atm.client;

public class User {
    private long clientNumber;
    private Auth method;

    public Auth getMethod() {
        return method;
    }

    public void setMethod(Auth method) {
        this.method = method;
    }
}
