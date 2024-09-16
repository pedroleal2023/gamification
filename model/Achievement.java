package model;

public abstract class Achievement {
    private String name;

    public Achievement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void addAchievement(Achievement achievement);
}
