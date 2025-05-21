package model;

import java.util.Date;

public class Notification {
    public enum NotificationType {
        NEW_COMMENT, NEW_REACTION, MENTION, SYSTEM
    }
    
    private int id;
    private User recipient;
    private String message;
    private NotificationType type;
    private Date creationDate;
    private boolean read;
    
    public Notification(int id, User recipient, String message, NotificationType type) {
        this.id = id;
        this.recipient = recipient;
        this.message = message;
        this.type = type;
        this.creationDate = new Date();
        this.read = false;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public User getRecipient() {
        return recipient;
    }
    
    public String getMessage() {
        return message;
    }
    
    public NotificationType getType() {
        return type;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public boolean isRead() {
        return read;
    }
    
    public void setRead(boolean read) {
        this.read = read;
    }
    
    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", recipient=" + recipient.getUsername() +
                ", message='" + message + '\'' +
                ", type=" + type +
                ", creationDate=" + creationDate +
                ", read=" + read +
                '}';
    }
}
