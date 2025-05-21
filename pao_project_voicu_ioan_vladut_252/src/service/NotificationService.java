package service;

import model.*;
import model.Notification.NotificationType;
import model.Reaction.ReactionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationService {
    private List<Notification> notifications;
    private int nextNotificationId = 1;
    
    public NotificationService() {
        this.notifications = new ArrayList<>();
    }
    
    public Notification createCommentNotification(User commenter, User recipient, Post post) {
        String message = commenter.getUsername() + " commented on your post: " + post.getTitle();
        Notification notification = new Notification(nextNotificationId++, recipient, message, NotificationType.NEW_COMMENT);
        notifications.add(notification);
        return notification;
    }
    
    public Notification createReplyNotification(User replier, User recipient, Comment originalComment) {
        String message = replier.getUsername() + " replied to your comment";
        Notification notification = new Notification(nextNotificationId++, recipient, message, NotificationType.NEW_COMMENT);
        notifications.add(notification);
        return notification;
    }
    
    public Notification createReactionNotification(User reactor, User recipient, Post post, ReactionType reactionType) {
        String message = reactor.getUsername() + " " + reactionType.toString().toLowerCase() + "d your post: " + post.getTitle();
        Notification notification = new Notification(nextNotificationId++, recipient, message, NotificationType.NEW_REACTION);
        notifications.add(notification);
        return notification;
    }
    
    public Notification createReactionNotification(User reactor, User recipient, Comment comment, ReactionType reactionType) {
        String message = reactor.getUsername() + " " + reactionType.toString().toLowerCase() + "d your comment";
        Notification notification = new Notification(nextNotificationId++, recipient, message, NotificationType.NEW_REACTION);
        notifications.add(notification);
        return notification;
    }
    
    public Notification createSystemNotification(User recipient, String message) {
        Notification notification = new Notification(nextNotificationId++, recipient, message, NotificationType.SYSTEM);
        notifications.add(notification);
        return notification;
    }
    
    public List<Notification> getNotificationsForUser(User user) {
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getRecipient().getId() == user.getId()) {
                userNotifications.add(notification);
            }
        }
        return userNotifications;
    }
    
    public List<Notification> getUnreadNotificationsForUser(User user) {
        List<Notification> unreadNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.getRecipient().getId() == user.getId() && !notification.isRead()) {
                unreadNotifications.add(notification);
            }
        }
        return unreadNotifications;
    }
    
    public void markAsRead(Notification notification) {
        notification.setRead(true);
    }
    
    public void markAllAsRead(User user) {
        for (Notification notification : notifications) {
            if (notification.getRecipient().getId() == user.getId()) {
                notification.setRead(true);
            }
        }
    }
    
    public List<Notification> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }
}
