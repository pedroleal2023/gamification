package test;

import interfaces.ForumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import proxy.ForumGamificationProxy;
import storage.AchievementStorageFactory;
import storage.MemoryAchievementStorage;
import model.Badge;
import model.Points;

import static org.junit.jupiter.api.Assertions.*;

public class ForumServiceGamificationProxyTest {
    private MemoryAchievementStorage storage;
    private ForumGamificationProxy proxy;
    private TestForumService realService;  // Use the real implementation

    @BeforeEach
    public void setUp() {
        storage = new MemoryAchievementStorage();
        AchievementStorageFactory.setAchievementStorage(storage);

        // Instancia a implementação de teste do ForumService
        realService = new TestForumService();

        // Cria o proxy usando o serviço real de teste
        proxy = new ForumGamificationProxy(realService);
    }

    @Test
    public void testAddTopic() {
        proxy.addTopic("user1", "topic1");

        Points points = (Points) storage.getAchievement("user1", "CREATION");
        Badge badge = (Badge) storage.getAchievement("user1", "I CAN TALK");

        assertNotNull(points);
        assertEquals(5, points.getPoints());
        assertNotNull(badge);
    }

    @Test
    public void testAddComment() {
        proxy.addComment("user1", "topic1", "comment1");

        Points points = (Points) storage.getAchievement("user1", "PARTICIPATION");
        Badge badge = (Badge) storage.getAchievement("user1", "LET ME ADD");

        assertNotNull(points);
        assertEquals(3, points.getPoints());
        assertNotNull(badge);
    }

    @Test
    public void testLikeTopic() {
        proxy.likeTopic("user1", "topic1", "topicUser");

        Points points = (Points) storage.getAchievement("user1", "CREATION");
        assertNotNull(points);
        assertEquals(1, points.getPoints());
    }

    @Test
    public void testLikeComment() {
        proxy.likeComment("user1", "topic1", "comment1", "commentUser");

        Points points = (Points) storage.getAchievement("user1", "PARTICIPATION");
        assertNotNull(points);
        assertEquals(1, points.getPoints());
    }

    @Test
    public void testMultipleAddTopic() {
        proxy.addTopic("user1", "topic1");
        proxy.addTopic("user1", "topic2");

        Points points = (Points) storage.getAchievement("user1", "CREATION");
        Badge badge = (Badge) storage.getAchievement("user1", "I CAN TALK");

        assertNotNull(points);
        assertEquals(10, points.getPoints());
        assertNotNull(badge);
    }

    @Test
    public void testProxyCallsRealService() {
        // Chama o método addTopic no proxy
        proxy.addTopic("user1", "topic1");

        // Verifica se o método no serviço real foi chamado
        assertTrue(realService.addTopicCalled, "O método addTopic deveria ter sido chamado.");

        // Chama o método addComment no proxy
        proxy.addComment("user1", "topic1", "comment1");

        // Verifica se o método no serviço real foi chamado
        assertTrue(realService.addCommentCalled, "O método addComment deveria ter sido chamado.");
    }

    // Implementação simples de ForumService que registra as chamadas de método
    private static class TestForumService implements ForumService {
        boolean addTopicCalled = false;
        boolean addCommentCalled = false;
        boolean likeTopicCalled = false;
        boolean likeCommentCalled = false;

        @Override
        public void addTopic(String user, String topic) {
            addTopicCalled = true; // Registra que o método foi chamado
        }

        @Override
        public void addComment(String user, String topic, String comment) {
            addCommentCalled = true; // Registra que o método foi chamado
        }

        @Override
        public void likeTopic(String user, String topic, String topicUser) {
            likeTopicCalled = true; // Registra que o método foi chamado
        }

        @Override
        public void likeComment(String user, String topic, String comment, String commentUser) {
            likeCommentCalled = true; // Registra que o método foi chamado
        }
    }
}
