import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import controller.ExpenseManager;
import model.EqualSplit;
import model.ExactSplit;
import model.Expense;
import model.ExpenseGroup;
import model.PercentSplit;
import model.Split;
import model.User;
import service.SplitService;
import service.SplitStrategy;
import service.impl.EqualSplitStrategy;
import service.impl.SplitServiceImpl;

public class SplitWiseDemoMain {

    String requirements = 
        """
        - User should be able to 
            - Create an expense group
            - Add users to the group
            - Create an expense and mention all the splits
            - see the amount he/she owes to the all other users in the group
        
        - model
            - User
            - Expense
            - Split
            - EqualSplit
            - PercentSplit
            - ExactSplit
            - ExpenseGroup
        
        - service
            - PaymentService
            - SplitService
            - SplitStrategy
        
        - controller
            - ExpenseManager
        
        - service/impl
            - CommandLinePaymentServiceImpl
            - UPIPaymentServiceImpl
            - SplitServiceImpl
            - EqualSplitStrategy
            - ExactSplitStrategy
            - PercentSplitStrategy
        
        - SplitWiseDemoMain

        """;

    
    private int expenseCounter = 1;
    private int userCounter = 1;
    private int splitCounter = 1;
    private int groupCounter = 1;

    public static void main(String[] args) {
        SplitWiseDemoMain demo = new SplitWiseDemoMain();

        SplitStrategy splitStrategy = new EqualSplitStrategy();
        SplitService splitService = new SplitServiceImpl(splitStrategy);
        ExpenseManager expenseManager = new ExpenseManager(splitService);

        User u1 = new User(demo.getUserCounter(), "Raviteja", "raviteja@gmail.com", "913343423423");
        User u2 = new User(demo.getUserCounter(), "Priyanka", "priyanka@gmail.com", "913343423423");
        User u3 = new User(demo.getUserCounter(), "Krishna", "krishna@gmail.com", "3753485348534");

        expenseManager.addUser(u1);
        expenseManager.addUser(u2);
        expenseManager.addUser(u3);

        ExpenseGroup expenseGroup = new ExpenseGroup(demo.getGroupCounter(), Arrays.asList(u1,u2,u3));
        expenseManager.addGroup(expenseGroup);

        List<Split> splits_1 = List.of(
            new EqualSplit(demo.getSplitCounter(), u1),
            new EqualSplit(demo.getSplitCounter(), u2),
            new EqualSplit(demo.getSplitCounter(), u3)
        );

        Expense expense_1 = new Expense(demo.getExpenseCounter(), "Kerala trip day - 1 lunch", u2, splits_1, new BigDecimal(465));
        expenseManager.addExpense(expense_1,expenseGroup);

        List<Split> splits_2 = List.of(
            new PercentSplit(demo.getSplitCounter(), u1, new BigDecimal(20)),
            new PercentSplit(demo.getSplitCounter(), u2, new BigDecimal(35)),
            new PercentSplit(demo.getSplitCounter(), u3, new BigDecimal(45))
        );

        Expense expense_2 = new Expense(demo.getExpenseCounter(), "Kerala trip day - 4 dinner", u1, splits_2, new BigDecimal(1000));
        expenseManager.addExpense(expense_2,expenseGroup);

        List<Split> splits_3 = List.of(
            new ExactSplit(demo.getSplitCounter(), u1, new BigDecimal(100)),
            new ExactSplit(demo.getSplitCounter(), u2, new BigDecimal(200)),
            new ExactSplit(demo.getSplitCounter(), u1, new BigDecimal(500))
        );
        Expense expense_3 = new Expense(demo.getExpenseCounter(), "Kerala trip day - 2 breakfast", u3, splits_3, new BigDecimal(800));
        expenseManager.addExpense(expense_3,expenseGroup);

        expenseManager.showBalances(expenseGroup);

    }

    public int getExpenseCounter() {
        return expenseCounter++;
    }

    public int getUserCounter() {
        return userCounter++;
    }

    public int getSplitCounter() {
        return splitCounter++;
    }

    public int getGroupCounter() {
        return groupCounter++;
    }
}
