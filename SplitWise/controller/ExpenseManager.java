package controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import model.Expense;
import model.ExpenseGroup;
import model.Split;
import model.User;
import service.SplitService;

public class ExpenseManager {

    private final SplitService splitService;

    private final Map<Integer, User> users;
    private final Map<Integer, Expense> expenses;
    private final Map<Integer, ExpenseGroup> expenseGroups;

    private final Map<Integer, Map<Integer, BigDecimal>> balances;

    public ExpenseManager(SplitService splitService) {
        this.splitService = splitService;
        this.balances = new HashMap<>();
        this.users = new HashMap<>();
        this.expenses = new HashMap<>();
        this.expenseGroups = new HashMap<>();
    }

    // add expense

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addGroup(ExpenseGroup group) {
        expenseGroups.put(group.getId(), group);
    }

    public void addExpense(Expense expense, ExpenseGroup group) {
        splitService.split(expense.getAmount(), expense.getSplits());
        expenses.put(expense.getId(), expense);
        group.addExpense(expense);

        User to = expense.getPaidBy();
        for(Split split : expense.getSplits()) {
            User from = split.getUser();

            if(from.equals(to)){
                continue;
            }
            addOrUpdateBalances(from, to, split.getAmount());
        }
    }

    // update the balances
    public void addOrUpdateBalances(User fromUser, User toUser, BigDecimal amount) {
        balances.putIfAbsent(fromUser.getId(), new HashMap<>());
        balances.putIfAbsent(toUser.getId(), new HashMap<>());

        BigDecimal fromTo = balances.get(fromUser.getId()).getOrDefault(toUser.getId(), BigDecimal.ZERO);
        BigDecimal toFrom = balances.get(toUser.getId()).getOrDefault(fromUser.getId(), BigDecimal.ZERO);

        BigDecimal net = fromTo.add(amount).subtract(toFrom);

        if(net.compareTo(BigDecimal.ZERO) >= 0) {
            balances.get(fromUser.getId()).put(toUser.getId(), net);
            balances.get(toUser.getId()).remove(fromUser.getId());
        } else {
            balances.get(toUser.getId()).put(fromUser.getId(), net);
            balances.get(fromUser.getId()).remove(toUser.getId());
        }
    }

    // show balances
    public void showBalances(ExpenseGroup group) {
        boolean empty = true;

        for(User user : group.getUsers()) {
            for(Map.Entry<Integer, BigDecimal> entry : balances.get(user.getId()).entrySet()){
                empty = false;
                System.out.println(user.getName() + " owes amount : " + entry.getValue() + " to " + users.get(entry.getKey()).getName());
            }
        }

        if(empty){
            System.out.println("No balances exists");
        }
    }

    // settle balances

    public SplitService getSplitService() {
        return splitService;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public Map<Integer, Expense> getExpenses() {
        return expenses;
    }

    public Map<Integer, ExpenseGroup> getExpenseGroups() {
        return expenseGroups;
    }

    public Map<Integer, Map<Integer, BigDecimal>> getBalances() {
        return balances;
    }
}
