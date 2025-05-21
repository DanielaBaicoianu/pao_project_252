package service;

import model.Comment;
import model.Post;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentService {
    private List<Comment> comments;
    private int nextCommentId = 1;
    
    private final NotificationService notificationService;
    
    public CommentService(NotificationService notificationService) {
        this.comments = new ArrayList<>();
        this.notificationService = notificationService;
    }
    
    public Comment addCommentToPost(String content, User author, Post post) {
        if (!author.isActive()) {
            throw new IllegalArgumentException("User is not active");
        }
        
        Comment comment = new Comment(nextCommentId++, content, author, post);
        comments.add(comment);
        post.addComment(comment);
        author.addComment(comment);
        
        notificationService.createCommentNotification(author, post.getAuthor(), post);
        
        return comment;
    }
    
    public Comment replyToComment(String content, User author, Post post, Comment parentComment) {
        if (!author.isActive()) {
            throw new IllegalArgumentException("User is not active");
        }
        
        if (!comments.contains(parentComment)) {
            throw new IllegalArgumentException("Parent comment does not exist in the system");
        }
        
        Comment reply = new Comment(nextCommentId++, content, author, post, parentComment);
        comments.add(reply);
        post.addComment(reply);
        parentComment.addReply(reply);
        author.addComment(reply);
        
        notificationService.createReplyNotification(author, parentComment.getAuthor(), parentComment);
        
        return reply;
    }
    
    public Comment findCommentById(int id) {
        for (Comment comment : comments) {
            if (comment.getId() == id) {
                return comment;
            }
        }
        return null;
    }
    
    public List<Comment> getCommentsForPost(Post post) {
        return post.getComments();
    }
    
    public List<Comment> getCommentsByAuthor(User author) {
        return author.getComments();
    }
    
    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }
}
