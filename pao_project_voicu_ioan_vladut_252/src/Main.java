import model.*;
import model.Reaction.ReactionType;
import service.ForumFacade;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Utilizam ForumFacade pentru a avea acces la toate serviciile
        ForumFacade forumFacade = new ForumFacade();

        System.out.println("======= FORUM APPLICATION DEMO =======");

        // 1. Inregistrare utilizatori
        User user1 = forumFacade.registerUser("john_doe", "john@example.com", "password123");
        User user2 = forumFacade.registerUser("jane_smith", "jane@example.com", "password456");
        User user3 = forumFacade.registerUser("mike_brown", "mike@example.com", "password789");
        Admin admin = forumFacade.registerAdmin("admin", "admin@forum.com", "admin123");
        Moderator moderator = forumFacade.registerModerator("mod1", "mod@forum.com", "mod123");

        System.out.println("--- Utilizatori înregistrați ---");
        for (User user : forumFacade.getUsers()) {
            System.out.println(user);
        }

        // 2. Autentificare utilizator
        User authenticatedUser = forumFacade.authenticateUser("john@example.com", "password123");
        if (authenticatedUser != null) {
            System.out.println("Autentificare reușită pentru: " + authenticatedUser.getUsername());
        } else {
            System.out.println("Autentificare eșuată");
        }

        // 3. Creare categorii si subiecte
        Category programmingCategory = forumFacade.createCategory("Programming", "Discussions about programming languages and technologies");
        Category gamingCategory = forumFacade.createCategory("Gaming", "Discussions about video games");

        Topic javaTopic = forumFacade.createTopic("Java Programming", "All about Java programming", user1, programmingCategory);
        Topic pythonTopic = forumFacade.createTopic("Python Programming", "All about Python programming", user2, programmingCategory);
        Topic minecraftTopic = forumFacade.createTopic("Minecraft", "Discussions about Minecraft", user3, gamingCategory);

        System.out.println("--- Categorii create ---");
        for (Category category : forumFacade.getCategories()) {
            System.out.println(category);
        }

        System.out.println("--- Subiecte create ---");
        for (Topic topic : forumFacade.getTopics()) {
            System.out.println(topic);
        }

        // 4. Creare postari
        Post javaPost1 = forumFacade.createPost(
            "Java Collections Overview",
            "In this post, we'll talk about Java Collections framework and its main interfaces.",
            user1,
            javaTopic
        );

        Post javaPost2 = forumFacade.createPost(
            "Java Stream API Tutorial",
            "Learn how to use Stream API to process collections of objects.",
            user2,
            javaTopic
        );

        Post pythonPost = forumFacade.createPost(
            "Python vs Java comparison",
            "Let's compare Python and Java in terms of syntax, performance, and use cases.",
            user3,
            pythonTopic
        );

        Post minecraftPost = forumFacade.createPost(
            "Best Minecraft mods 2023",
            "Here's a list of the best Minecraft mods to try in 2023.",
            user2,
            minecraftTopic
        );

        System.out.println("--- Postări create ---");
        for (Post post : forumFacade.getPosts()) {
            System.out.println(post);
        }

        // 5. Adaugare tag-uri la postari
        forumFacade.addTagToPost(javaPost1, "java");
        forumFacade.addTagToPost(javaPost1, "collections");
        forumFacade.addTagToPost(javaPost1, "programming");

        forumFacade.addTagToPost(javaPost2, "java");
        forumFacade.addTagToPost(javaPost2, "stream");
        forumFacade.addTagToPost(javaPost2, "functional");

        forumFacade.addTagToPost(pythonPost, "python");
        forumFacade.addTagToPost(pythonPost, "java");
        forumFacade.addTagToPost(pythonPost, "comparison");

        System.out.println("--- Tag-uri create ---");
        for (Tag tag : forumFacade.getTags()) {
            System.out.println(tag);
        }

        // 6. Adaugare comentarii la postari
        Comment comment1 = forumFacade.addCommentToPost(
                "Great overview! I especially liked the part about LinkedList vs ArrayList.",
                user3,
                javaPost1
        );

        Comment comment2 = forumFacade.addCommentToPost(
                "Could you add more examples for HashMap?",
                user2,
                javaPost1
        );

        Comment comment3 = forumFacade.addCommentToPost(
                "Stream API is really powerful for data processing!",
                user1,
                javaPost2
        );

        // 7. Adaugare raspunsuri la comentarii
        Comment reply1 = forumFacade.replyToComment(
                "Thanks! I'll add more examples in a follow-up post.",
                user1,
                javaPost1,
                comment2
        );

        System.out.println("--- Comentarii create ---");
        for (Comment comment : forumFacade.getComments()) {
            System.out.println(comment);
        }

        // 8. Adaugare reactii
        forumFacade.addReactionToPost(user3, javaPost1, ReactionType.LIKE);
        forumFacade.addReactionToPost(user2, javaPost1, ReactionType.LOVE);
        forumFacade.addReactionToPost(user1, javaPost2, ReactionType.LIKE);
        forumFacade.addReactionToComment(user1, comment1, ReactionType.LIKE);

        // 9. Cautare postari dupa cuvinte cheie
        System.out.println("--- Căutare postări cu cuvântul cheie 'java' ---");
        List<Post> searchResults = forumFacade.searchPostsByKeyword("java");
        for (Post post : searchResults) {
            System.out.println(post);
        }

        // 10. Listare postari sortate dupa popularitate
        System.out.println("--- Postări sortate după popularitate ---");
        List<Post> popularPosts = forumFacade.getPostsSortedByPopularity();
        for (Post post : popularPosts) {
            System.out.println(post + " - Scor popularitate: " + post.getPopularityScore());
        }

        // 11. Listare utilizatori sortati dupa activitate
        System.out.println("--- Utilizatori sortați după activitate ---");
        List<User> activeUsers = forumFacade.getUsersSortedByActivity();
        for (User user : activeUsers) {
            System.out.println(user.getUsername() + " - Scor activitate: " + user.getActivityScore());
        }

        // 12. Ban utilizator
        forumFacade.banUser(admin, user3);
        System.out.println("--- Utilizatori blocați de admin ---");
        for (User user : admin.getBannedUsers()) {
            System.out.println(user.getUsername() + " - Status activ: " + user.isActive());
        }

        // 13. Verificare notificari
        System.out.println("--- Notificări pentru utilizatori ---");
        for (Notification notification : forumFacade.getNotifications()) {
            System.out.println(notification);
        }

        System.out.println("=== DEMO ÎNCHEIAT CU SUCCES ===");
    }
}
