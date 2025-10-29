package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Expense {

    private final int id;
    private final String description;
    private final User paidBy;
    private final LocalDateTime createdOn;
    private final List<Split> splits;
    private final BigDecimal amount;

    public Expense(int id, String description, User paidBy, List<Split> splits, BigDecimal amount) {
        this.id = id;
        this.description = description;
        this.paidBy = paidBy;
        this.splits = splits;
        this.amount = amount;
        this.createdOn = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
