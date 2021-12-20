package service;

import models.*;

import java.util.List;

public class ExpenseService {

    public static Bill createExpense(ExpenseType type, double amount, User paidBy, List<Share> shares) {
        switch (type) {
            case EXACT:
                return new ExactBill(amount, "exact", paidBy, shares);
            case EQUAl:
                int total = shares.size();
                double splitAmt = amount / total;
                for (Share share : shares) {
                    share.setShareAmount(splitAmt);
                }
                shares.get(0).setShareAmount((amount - (splitAmt * (total - 1))));
                return new EqualBill(amount, "equal", paidBy, shares);
            case PERCENT:
                for (Share share : shares) {
                    share.setShareAmount((amount * ((PercentShare)share).getPercent()) / 100.0);
                }
                return new PercentBill(amount, "percent", paidBy, shares);
            default:
                return null;
        }
    }
}
