package model;

public class Points extends Achievement{

    private int points;

    public Points(String name, int points) {
        super(name);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public void addAchievement(Achievement other) {
        if (other instanceof Points && other.getName().equals(this.getName())) {
            this.points += ((Points) other).getPoints();
        }
    }
}
