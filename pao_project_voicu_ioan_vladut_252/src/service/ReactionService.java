package service;

import model.Comment;
import model.Post;
import model.Reaction;
import model.Reaction.ReactionType;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ReactionService {
    private int nextReactionId = 1;
    private final NotificationService notificationService;
    
    public ReactionService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    public Reaction addReactionToPost(User user, Post post, ReactionType type) {
        if (!user.isActive()) {
            throw new IllegalArgumentException("User is not active");
        }
        
        for (Reaction reaction : post.getReactions()) {
            if (reaction.getUser().getId() == user.getId()) {
                reaction.setType(type);
                return reaction;
            }
        }
        
        Reaction reaction = new Reaction(nextReactionId++, user, type);
        post.addReaction(reaction);
        
        notificationService.createReactionNotification(user, post.getAuthor(), post, type);
        
        return reaction;
    }
    
    public Reaction addReactionToComment(User user, Comment comment, ReactionType type) {
        if (!user.isActive()) {
            throw new IllegalArgumentException("User is not active");
        }
        
        for (Reaction reaction : comment.getReactions()) {
            if (reaction.getUser().getId() == user.getId()) {
                reaction.setType(type);
                return reaction;
            }
        }
        
        Reaction reaction = new Reaction(nextReactionId++, user, type);
        comment.addReaction(reaction);
        
        notificationService.createReactionNotification(user, comment.getAuthor(), comment, type);
        
        return reaction;
    }
    
    public void removeReactionFromPost(User user, Post post) {
        for (Reaction reaction : new ArrayList<>(post.getReactions())) {
            if (reaction.getUser().getId() == user.getId()) {
                post.removeReaction(reaction);
                return;
            }
        }
    }
    
    public void removeReactionFromComment(User user, Comment comment) {
        for (Reaction reaction : new ArrayList<>(comment.getReactions())) {
            if (reaction.getUser().getId() == user.getId()) {
                comment.removeReaction(reaction);
                return;
            }
        }
    }
}
