package proxy;

import interfaces.AchievementStorage;
import interfaces.ForumService;
import model.Badge;
import model.Points;

public class ForumGamificationProxy implements ForumService {
    private ForumService forumService;
    private AchievementStorage achievementStorage;

    public ForumGamificationProxy(ForumService forumService, AchievementStorage achievementStorage) {
        this.forumService = forumService;
        this.achievementStorage = achievementStorage;
    }

    @Override
    public void addTopic(String user, String topic) {
        forumService.addTopic(user, topic);
        achievementStorage.addAchievement(user, new Points("CREATION", 5));
        achievementStorage.addAchievement(user, new Badge("I CAN TALK"));
    }

    @Override
    public void addComment(String user, String topic, String comment) {
        forumService.addComment(user, topic, comment);
        achievementStorage.addAchievement(user, new Points("PARTICIPATION", 3));
        achievementStorage.addAchievement(user, new Badge("LET ME ADD"));
    }

    @Override
    public void likeTopic(String user, String topic, String topicUser) {
        forumService.likeTopic(user, topic, topicUser);
        achievementStorage.addAchievement(topicUser, new Points("CREATION", 1));
    }

    @Override
    public void likeComment(String user, String topic, String comment, String commentUser) {
        forumService.likeComment(user, topic, comment, commentUser);
        achievementStorage.addAchievement(commentUser, new Points("PARTICIPATION", 1));
    }
}
