package models;

import java.util.List;

public class Bill {
    private double amount;
    private String description;
    private User paidBy;
    private List<Share> shareList;

    public Bill(double amount, String description, User paidBy, List<Share> shareList) {
        this.amount = amount;
        this.description = description;
        this.paidBy = paidBy;
        this.shareList = shareList;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<Share> getShareList() {
        return shareList;
    }

    public void setShareList(List<Share> shareList) {
        this.shareList = shareList;
    }
}
