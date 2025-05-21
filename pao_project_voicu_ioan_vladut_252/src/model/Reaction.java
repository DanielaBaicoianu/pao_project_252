package model;

import java.util.Date;

public class Reaction {
    public enum ReactionType {
        LIKE, LOVE, LAUGH, ANGRY, SAD
    }
    
    private int id;
    private User user;
    private ReactionType type;
    private Date creationDate;
    
    public Reaction(int id, User user, ReactionType type) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.creationDate = new Date();
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public User getUser() {
        return user;
    }
    
    public ReactionType getType() {
        return type;
    }
    
    public void setType(ReactionType type) {
        this.type = type;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    @Override
    public String toString() {
        return "Reaction{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", type=" + type +
                ", creationDate=" + creationDate +
                '}';
    }
}
