package model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseGroup {

    private final int id;
    private final List<User> users;
    private final List<Expense> expenses;

    public ExpenseGroup(int id, List<User> users) {
        this.id = id;
        this.users = users;
        this.expenses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public List<User> getUsers() {
        return users;
    }
    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
}
