package model;

import java.math.BigDecimal;

import enums.SplitType;

public class PercentSplit extends Split{

    private final BigDecimal percentage;

    public PercentSplit(int id, User user, BigDecimal percentage) {
        super(id, user, SplitType.PERCENT);
        this.percentage = percentage;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }
}
