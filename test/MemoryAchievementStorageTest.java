package test;

import model.Achievement;
import model.Badge;
import model.Points;
import storage.MemoryAchievementStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MemoryAchievementStorageTest {
    private MemoryAchievementStorage storage;

    @BeforeEach
    public void setUp() {
        storage = new MemoryAchievementStorage();
    }

    @Test
    public void testAddAchievement() {
        Achievement points = new Points("CREATION", 10);
        storage.addAchievement("user1", points);
        Achievement badge = new Badge("I CAN TALK");
        storage.addAchievement("user1", badge);

        List<Achievement> achievements = storage.getAchievements("user1");
        assertTrue(achievements.contains(points));
        assertTrue(achievements.contains(badge));
    }

    @Test
    public void testAddPoints() {
        Points initialPoints = new Points("CREATION", 10);
        storage.addAchievement("user1", initialPoints);

        Points additionalPoints = new Points("CREATION", 20);
        storage.addAchievement("user1", additionalPoints);

        Points result = (Points) storage.getAchievement("user1", "CREATION");
        assertEquals(30, result.getPoints());
    }

    @Test
    public void testAddBadge() {
        Badge badge = new Badge("I CAN TALK");
        storage.addAchievement("user1", badge);

        Badge result = (Badge) storage.getAchievement("user1", "I CAN TALK");
        assertNotNull(result);
    }

    @Test
    public void testAddSameBadgeTwice() {
        Badge badge1 = new Badge("I CAN TALK");
        storage.addAchievement("user1", badge1);
        Badge badge2 = new Badge("I CAN TALK");
        storage.addAchievement("user1", badge2);

        List<Achievement> achievements = storage.getAchievements("user1");
        long badgeCount = achievements.stream().filter(a -> a.getName().equals("I CAN TALK")).count();
        assertEquals(1, badgeCount);
    }

    @Test
    public void testGetAchievements() {
        Points points = new Points("CREATION", 50);
        Badge badge = new Badge("I CAN TALK");
        storage.addAchievement("user1", points);
        storage.addAchievement("user1", badge);

        List<Achievement> achievements = storage.getAchievements("user1");
        assertEquals(2, achievements.size());
    }

    @Test
    public void testGetAchievement() {
        Points points = new Points("CREATION", 10);
        storage.addAchievement("user1", points);

        Points result = (Points) storage.getAchievement("user1", "CREATION");
        assertNotNull(result);
        assertEquals(10, result.getPoints());
    }
}
