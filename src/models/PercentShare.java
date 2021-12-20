package models;

public class PercentShare extends Share {

    double percent;

    public PercentShare(User user, double percent) {
        super(user);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }
}
