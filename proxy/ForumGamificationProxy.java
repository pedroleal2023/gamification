package proxy;

import interfaces.ForumService;
import storage.AchievementStorageFactory;
import model.Points;
import model.Badge;

public class ForumGamificationProxy implements ForumService {
    private final ForumService realService;

    public ForumGamificationProxy(ForumService realService) {
        this.realService = realService;
    }

    @Override
    public void addTopic(String user, String topic) {
        realService.addTopic(user, topic);
        AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Points("CREATION", 5));
        AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Badge("I CAN TALK"));
    }

    @Override
    public void addComment(String user, String topic, String comment) {
        realService.addComment(user, topic, comment);
        AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Points("PARTICIPATION", 3));
        AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Badge("LET ME ADD"));
    }

    @Override
    public void likeTopic(String user, String topic, String topicUser) {
        realService.likeTopic(user, topic, topicUser);
        AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Points("CREATION", 1));
    }

    @Override
    public void likeComment(String user, String topic, String comment, String commentUser) {
        realService.likeComment(user, topic, comment, commentUser);
        AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Points("PARTICIPATION", 1));
    }
}
