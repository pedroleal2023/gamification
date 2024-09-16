package storage;

import interfaces.AchievementStorage;

public class AchievementStorageFactory {
    private static AchievementStorage instance;

    public static void setAchievementStorage(AchievementStorage storage) {
        instance = storage;
    }

    public static AchievementStorage getAchievementStorage() {
        if (instance == null) {
            throw new IllegalStateException("AchievementStorage not initialized");
        }
        return instance;
    }
}
