import models.Bill;
import models.ExpenseType;
import models.Share;
import models.User;
import service.ExpenseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    List<Bill> expenses;
    Map<String, User> userMap;
    Map<String, Map<String, Double> > balanceSheet;

    public ExpenseManager() {
        expenses = new ArrayList<>();
        userMap = new HashMap<>();
        balanceSheet = new HashMap<String, Map<String, Double>>();
    }

    public void addUser(User user) {
        userMap.putIfAbsent(user.getUserId(), user);
        balanceSheet.putIfAbsent(user.getUserId(), new HashMap<String, Double>());
    }

    public void addExpense(ExpenseType expenseType, User paidBy, Double amount, List<Share> bills) {
        Bill bill = ExpenseService.createExpense(expenseType, amount, paidBy, bills);
        expenses.add(bill);
        for (Share share : bill.getShareList()) {
            String paidTo = share.getUser().getUserId();
            Map<String, Double> balances = balanceSheet.get(paidBy.getUserId());
            if (!balances.containsKey(paidTo)) {
                balances.put(paidTo, 0.0);
            }
            balances.put(paidTo, balances.get(paidTo) + share.getShareAmount());

            balances = balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy.getUserId())) {
                balances.put(paidBy.getUserId(), 0.0);
            }
            balances.put(paidBy.getUserId(), balances.get(paidBy.getUserId()) - share.getShareAmount());
        }
    }

    public void showBalance(String user) {
        for (String user2 : balanceSheet.get(user).keySet()) {
            printBalance(userMap.get(user), userMap.get(user2), balanceSheet.get(user).get(user2));
        }
    }

    public void showBalances() {
        for (String user1 : userMap.keySet()) {
            for (String user2 : balanceSheet.get(user1).keySet()) {
                printBalance(userMap.get(user1), userMap.get(user2), balanceSheet.get(user1).get(user2));
            }
        }
    }

    private void printBalance(User user1, User user2, double amount) {
        if (amount < 0) {
            System.out.println(user1.getUserId() + " owes " + amount + " to " + user2.getUserId());
        } else if (amount > 0) {
            System.out.println(user1.getUserId() + " gets " + Math.abs(amount) + " from " + user2.getUserId());
        } else {
            System.out.println(user1.getUserId() + " has balance clear with " + user2.getUserId());
        }
    }

}
