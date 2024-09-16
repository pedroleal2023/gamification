package storage;

import interfaces.AchievementObserver;
import interfaces.AchievementStorage;
import model.Achievement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryAchievementStorage implements AchievementStorage {
    Map<String, List<Achievement>> achievements = new HashMap<>();
    List<AchievementObserver> observer = new ArrayList<>();

    @Override
    public void addAchievement(Achievement achievement) {
        for (AchievementObserver observer : observer) {

        }
    }

    @Override
    public List<Achievement> getAchievement(String user) {
        return List.of();
    }

    @Override
    public Achievement getAchievement(String user, String achievement) {
        return null;
    }

    @Override
    public void addObserver(AchievementObserver observer) {

    }
}
