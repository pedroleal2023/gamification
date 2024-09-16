package interfaces;

import model.Achievement;
import java.util.List;

public interface AchievementStorage {
    void addAchievement(String user, Achievement achievement);
    List<Achievement> getAchievements(String user);
    Achievement getAchievement(String user, String achievementName);
}
