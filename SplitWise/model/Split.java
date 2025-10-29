package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import enums.SplitType;

public abstract class Split {

    private final int id;
    private BigDecimal amount;
    private final User user;
    private final SplitType splitType;

    public Split(int id, User user, SplitType splitType) {
        this.id = id;
        this.user = user;
        this.splitType = splitType;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public User getUser() {
        return user;
    }

    public SplitType getSplitType() {
        return splitType;
    }
}
