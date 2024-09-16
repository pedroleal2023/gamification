package interfaces;

import model.Achievement;

public interface AchievementObserver {
    void achievementUpdate(String user, Achievement achievement);
}
