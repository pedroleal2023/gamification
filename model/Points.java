package model;

public class Points extends Achievement {
    private int points;

    public Points(String name, int points) {
        super(name);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public void addAchievement(Achievement achievement) {
        if (achievement instanceof Points) {
            Points pointsAchievement = (Points) achievement;
            this.points += pointsAchievement.getPoints();
        }
    }
}
