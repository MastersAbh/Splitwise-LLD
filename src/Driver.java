import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static models.ExpenseType.*;

public class Driver {

    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        manager.addUser(new User("1", "A"));
        manager.addUser(new User("2", "B"));
        manager.addUser(new User("3", "C"));
        manager.addUser(new User("4", "D"));
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");

            switch (commands[0]) {
                case "SHOW" :
                    if (commands.length == 1) {
                        manager.showBalances();
                    } else {
                        manager.showBalance(commands[1]);
                    }
                    break;
                case "EXPENSE" :
                    String paidBy = commands[1];
                    int noUser = Integer.parseInt(commands[2]);
                    Double amount = Double.parseDouble(commands[3]);
                    String type = commands[4];
                    List<Share> bills = new ArrayList<>();
                    switch (type) {
                        case "EQUAL" :
                            for (int i = 5; i < 5 + noUser; i++) {
                                bills.add(new EqualShare(manager.userMap.get(commands[i])));
                            }
                            manager.addExpense(EQUAl, manager.userMap.get(paidBy), amount, bills);
                            break;
                        case "EXACT" :
                            for (int i = 5; i < 5 + noUser; i++) {
                                bills.add(new ExactShare(manager.userMap.get(commands[i]), Double.parseDouble(commands[i+noUser])));
                            }
                            manager.addExpense(EXACT, manager.userMap.get(paidBy), amount,  bills);
                            break;
                        case "PERCENT" :
                            for (int i = 5; i < 5 + noUser; i++) {
                                bills.add(new PercentShare(manager.userMap.get(commands[i]), Double.parseDouble(commands[i+noUser])));
                            }
                            manager.addExpense(PERCENT, manager.userMap.get(paidBy), amount,  bills);
                            break;
                    }
                    break;
            }
        }
    }
}
