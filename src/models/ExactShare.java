package models;

public class ExactShare extends Share {

    public ExactShare(User user, double amount) {
        super(user);
        this.shareAmount = amount;
    }
}
