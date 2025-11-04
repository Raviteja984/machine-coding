import java.math.BigDecimal;
import java.math.RoundingMode;

import controller.WalletController;
import entity.Account;
import entity.User;

public class DigitalWalletDemoMain {


    private static int count = 1;

    private static int getEntityCounter() {
        return count++;
    }

    private static BigDecimal  getAdjustedAmount(double amount) {
        return new BigDecimal(amount).setScale(3, RoundingMode.HALF_UP);
    }
    
    public static void main(String[] args) throws Exception {

        WalletController wallet = new WalletController(DigitalWalletDemoMain :: getEntityCounter);

        // Users registration
        User Raviteja = wallet.addUser(new User(getEntityCounter(),"Raviteja"));
        User Priyanka = wallet.addUser(new User(getEntityCounter(),"Priyanka"));
        User Krishna = wallet.addUser(new User(getEntityCounter(),"Krishna"));

        // Accounts registration
        Account Raviteja_a = wallet.addAccount(new Account(getEntityCounter(), Raviteja, getAdjustedAmount(100.037)));
        Account Priyanka_a = wallet.addAccount(new Account(getEntityCounter(), Priyanka, getAdjustedAmount(500.048)));
        Account Krishna_a = wallet.addAccount(new Account(getEntityCounter(), Krishna, getAdjustedAmount(1000.098)));

        // load wallet

        // transfer money
        wallet.transfer(Krishna_a, Priyanka_a, getAdjustedAmount(100.967));
        wallet.transfer(Krishna_a, Raviteja_a, getAdjustedAmount(50.459));

        // withdraw from wallet

        // show balances
        wallet.showBalances();
    }
}
