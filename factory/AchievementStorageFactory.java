package factory;

import interfaces.AchievementStorage;
import storage.MemoryAchievementStorage;

public class AchievementStorageFactory {
    private static AchievementStorage achievementStorage;

    public static AchievementStorage getAchievementStorage() {
        if (achievementStorage == null) {
            achievementStorage = new MemoryAchievementStorage();
        }
        return achievementStorage;
    }

    public static void setAchievementStorage(AchievementStorage storage) {
        achievementStorage = storage;
    }
}

