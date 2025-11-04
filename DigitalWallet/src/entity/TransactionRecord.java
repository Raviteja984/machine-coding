package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRecord implements Identifyable{

    private final int id;
    private final Account fromAccount;
    private final Account toAccount;
    private final String txnType;
    private String txnStatus;
    private final BigDecimal amount;
    private final LocalDateTime createdAt;

    public TransactionRecord(int id, Account fromAccount, Account toAccount, String txnType, String txnStatus,
            BigDecimal amount) {
        this.id = id;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.txnType = txnType;
        this.txnStatus = txnStatus;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public String getTxnType() {
        return txnType;
    }

    public String getTxnStatus() {
        return txnStatus;
    }

    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "TransactionRecord [id=" + id + ", fromAccount=" + fromAccount + ", toAccount=" + toAccount
                + ", txnType=" + txnType + ", txnStatus=" + txnStatus + ", amount=" + amount + ", createdAt="
                + createdAt + "]";
    }
    
}
