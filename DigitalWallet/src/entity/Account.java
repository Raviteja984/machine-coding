package entity;

import java.math.BigDecimal;

public class Account implements Identifyable{

    private final int id;
    private final User user;
    private BigDecimal balance;

    public Account(int id, User user, BigDecimal balance) {
        this.id = id;
        this.user = user;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    
}
