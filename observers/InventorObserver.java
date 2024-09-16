package observers;

import interfaces.AchievementObserver;
import model.Achievement;
import model.Points;
import model.Badge;
import storage.MemoryAchievementStorage;

public class InventorObserver implements AchievementObserver {
    private final MemoryAchievementStorage storage;

    public InventorObserver(MemoryAchievementStorage storage) {
        this.storage = storage;
    }

    @Override
    public void achievementUpdate(String user, Achievement achievement) {
        if (achievement instanceof Points) {
            Points points = (Points) achievement;
            int totalPoints = points.getPoints();
            if (totalPoints >= 100) {
                storage.addAchievement(user, new Badge("INVENTOR"));
            }
        }
    }
}
