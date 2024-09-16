package storage;

import interfaces.AchievementObserver;
import interfaces.AchievementStorage;
import model.Achievement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryAchievementStorage implements AchievementStorage {
    private Map<String, Map<String, Achievement>> userAchievements = new HashMap<>();
    private List<AchievementObserver> observers = new ArrayList<>();

    @Override
    public void addAchievement(String user, Achievement achievement) {
        userAchievements.putIfAbsent(user, new HashMap<>());
        Map<String, Achievement> achievements = userAchievements.get(user);
        Achievement existingAchievement = achievements.get(achievement.getName());

        if (existingAchievement != null) {
            existingAchievement.addAchievement(achievement);
        } else {
            achievements.put(achievement.getName(), achievement);
        }

        notifyObservers(user, achievement);
    }

    @Override
    public List<Achievement> getAchievements(String user) {
        return new ArrayList<>(userAchievements.getOrDefault(user, new HashMap<>()).values());
    }

    @Override
    public Achievement getAchievement(String user, String achievementName) {
        return userAchievements.getOrDefault(user, new HashMap<>()).get(achievementName);
    }

    public void addObserver(AchievementObserver observer) {
        this.observers.add(observer);
    }

    private void notifyObservers(String user, Achievement achievement) {
        for (AchievementObserver observer : observers) {
            observer.achievementUpdate(user, achievement);
        }
    }
}
