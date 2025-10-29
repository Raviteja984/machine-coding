package model;

import java.math.BigDecimal;

import enums.SplitType;

public class ExactSplit extends Split{

    private final BigDecimal exactAmount;

    public ExactSplit(int id, User user, BigDecimal exactAmount) {
        super(id, user, SplitType.EXACT);
        this.exactAmount = exactAmount;
    }

    public BigDecimal getExactAmount() {
        return exactAmount;
    }
}
