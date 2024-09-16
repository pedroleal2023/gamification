package interfaces;

import model.Achievement;

import java.util.List;
import java.util.Observer;

public interface AchievementStorage {

    void addAchievement(Achievement achievement);

    List<Achievement> getAchievement(String user);
    Achievement getAchievement(String user, String achievement);
    void addObserver(AchievementObserver observer);
}
