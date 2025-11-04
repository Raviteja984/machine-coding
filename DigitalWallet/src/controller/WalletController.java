package controller;

import java.math.BigDecimal;
import java.util.function.Supplier;

import entity.Account;
import entity.Constants;
import entity.TransactionRecord;
import entity.User;
import repository.EntityRepository;
import repository.EntityRepositoryImpl;

public class WalletController {

    private final EntityRepository<User> userRepo;
    private final EntityRepository<Account> accountRepo;
    private final EntityRepository<TransactionRecord> txnRepo;
    private final Supplier<Integer> counter;

    public WalletController(Supplier<Integer> counter) {
        this.userRepo = new EntityRepositoryImpl<>();
        this.accountRepo = new EntityRepositoryImpl<>();
        this.txnRepo = new EntityRepositoryImpl<>();
        this.counter = counter;
    }

    public User addUser(User user) {
        return userRepo.addEntity(user);
    }

    public Account addAccount(Account account) {
        return accountRepo.addEntity(account);
    }

    public void loadWallet(Account account, BigDecimal amount) {
        account.setBalance(account.getBalance().add(amount));
        TransactionRecord record = new TransactionRecord(counter.get(), null, account, Constants.ADD_MONEY, Constants.SUCCESS, amount);
        txnRepo.addEntity(record);
    }

    public void transfer(Account from, Account to, BigDecimal amt) {
        if(from.getBalance().compareTo(amt) < 0) {
            System.out.println("Insufficient Balance. Try Again");
            TransactionRecord record = new TransactionRecord(counter.get(), from, to, Constants.TRANSFER, Constants.FAILURE, amt);
            txnRepo.addEntity(record);
            return;
        }
        // handle deadlock
        Account lock1 = from.getId() < to.getId() ? from : to;
        Account lock2 = from.getId() < to.getId() ? to : from;
        synchronized(lock1) {
            synchronized(lock2) {
                debitWallet(from, amt);
                loadWallet(to, amt);
            }
        }
        TransactionRecord record = new TransactionRecord(counter.get(), from, to, Constants.TRANSFER, Constants.SUCCESS, amt);
        txnRepo.addEntity(record);
    }

    public void debitWallet(Account account, BigDecimal amt) {
        if(account.getBalance().compareTo(amt) < 0) {
            System.out.println("Insufficient funds");
            TransactionRecord record = new TransactionRecord(counter.get(), account, null, Constants.WITHDRAW, Constants.FAILURE, amt);
            txnRepo.addEntity(record);
            return;
        }
        account.setBalance(account.getBalance().subtract(amt));
        TransactionRecord record = new TransactionRecord(counter.get(), account, null, Constants.WITHDRAW, Constants.SUCCESS, amt);
        txnRepo.addEntity(record);
    }

    public void showBalances() {
        txnRepo.findAll().stream().forEach(txn -> System.out.println(txn.toString()));
    }
}
