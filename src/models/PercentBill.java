package models;

import java.util.List;

public class PercentBill extends Bill{
    private User user;
    private double shareAmount;

    public PercentBill(double amount, String description, User paidBy, List<Share> shareList) {
        super(amount, description, paidBy, shareList);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(double shareAmount) {
        this.shareAmount = shareAmount;
    }
}
