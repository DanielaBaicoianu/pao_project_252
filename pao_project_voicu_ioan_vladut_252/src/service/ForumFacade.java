package service;

import model.*;
import model.Reaction.ReactionType;

import java.util.List;
import java.util.Set;

public class ForumFacade {
    private final UserService userService;
    private final CategoryService categoryService;
    private final PostService postService;
    private final CommentService commentService;
    private final ReactionService reactionService;
    private final NotificationService notificationService;
    private final TagService tagService;
    private final SearchService searchService;
    
    public ForumFacade() {
        this.tagService = new TagService();
        this.notificationService = new NotificationService();
        this.userService = new UserService();
        this.categoryService = new CategoryService();
        this.postService = new PostService(tagService);
        this.commentService = new CommentService(notificationService);
        this.reactionService = new ReactionService(notificationService);
        this.searchService = new SearchService(postService, tagService);
    }
    
    // User operations
    public User registerUser(String username, String email, String password) {
        return userService.registerUser(username, email, password);
    }
    
    public Admin registerAdmin(String username, String email, String password) {
        return userService.registerAdmin(username, email, password);
    }
    
    public Moderator registerModerator(String username, String email, String password) {
        return userService.registerModerator(username, email, password);
    }
    
    public User authenticateUser(String email, String password) {
        return userService.authenticateUser(email, password);
    }
    
    public void banUser(Admin admin, User userToBan) {
        userService.banUser(admin, userToBan);
    }
    
    // Category and Topic operations
    public Category createCategory(String name, String description) {
        return categoryService.createCategory(name, description);
    }
    
    public Topic createTopic(String title, String description, User creator, Category category) {
        return categoryService.createTopic(title, description, creator, category);
    }
    
    // Post operations
    public Post createPost(String title, String content, User author, Topic topic) {
        return postService.createPost(title, content, author, topic);
    }
    
    public void addTagToPost(Post post, String tagName) {
        postService.addTagToPost(post, tagName);
    }
    
    // Comment operations
    public Comment addCommentToPost(String content, User author, Post post) {
        return commentService.addCommentToPost(content, author, post);
    }
    
    public Comment replyToComment(String content, User author, Post post, Comment parentComment) {
        return commentService.replyToComment(content, author, post, parentComment);
    }
    
    // Reaction operations
    public Reaction addReactionToPost(User user, Post post, ReactionType type) {
        return reactionService.addReactionToPost(user, post, type);
    }
    
    public Reaction addReactionToComment(User user, Comment comment, ReactionType type) {
        return reactionService.addReactionToComment(user, comment, type);
    }
    
    // Search operations
    public List<Post> searchPostsByKeyword(String keyword) {
        return searchService.searchPostsByKeyword(keyword);
    }
    
    public List<Post> searchPostsByTag(String tagName) {
        return searchService.searchPostsByTag(tagName);
    }
    
    // Sorting operations
    public List<Post> getPostsSortedByPopularity() {
        return postService.getPostsSortedByPopularity();
    }
    
    public List<User> getUsersSortedByActivity() {
        return userService.getUsersSortedByActivity();
    }
    
    // Getters for collections
    public List<User> getUsers() {
        return userService.getUsers();
    }
    
    public List<Admin> getAdmins() {
        return userService.getAdmins();
    }
    
    public List<Moderator> getModerators() {
        return userService.getModerators();
    }
    
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }
    
    public List<Topic> getTopics() {
        return categoryService.getTopics();
    }
    
    public List<Post> getPosts() {
        return postService.getPosts();
    }
    
    public List<Comment> getComments() {
        return commentService.getComments();
    }
    
    public Set<Tag> getTags() {
        return tagService.getTags();
    }
    
    public List<Notification> getNotifications() {
        return notificationService.getNotifications();
    }
    
    public List<Notification> getUnreadNotificationsForUser(User user) {
        return notificationService.getUnreadNotificationsForUser(user);
    }
}
