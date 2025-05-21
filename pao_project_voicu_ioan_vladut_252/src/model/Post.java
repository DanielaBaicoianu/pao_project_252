package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post {
    private int id;
    private String title;
    private String content;
    private User author;
    private Topic topic;
    private Date creationDate;
    private List<Comment> comments;
    private List<Reaction> reactions;
    private Set<Tag> tags;
    
    public Post(int id, String title, String content, User author, Topic topic) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.topic = topic;
        this.creationDate = new Date();
        this.comments = new ArrayList<>();
        this.reactions = new ArrayList<>();
        this.tags = new HashSet<>();
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public User getAuthor() {
        return author;
    }
    
    public Topic getTopic() {
        return topic;
    }
    
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public List<Comment> getComments() {
        return comments;
    }
    
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }
    
    public List<Reaction> getReactions() {
        return reactions;
    }
    
    public void addReaction(Reaction reaction) {
        reactions.add(reaction);
    }
    
    public void removeReaction(Reaction reaction) {
        reactions.remove(reaction);
    }
    
    public Set<Tag> getTags() {
        return tags;
    }
    
    public void addTag(Tag tag) {
        tags.add(tag);
    }
    
    public void removeTag(Tag tag) {
        tags.remove(tag);
    }
    
    public int getPopularityScore() {
        return comments.size() * 2 + reactions.size();
    }
    
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author.getUsername() +
                ", creationDate=" + creationDate +
                ", commentsCount=" + comments.size() +
                ", reactionsCount=" + reactions.size() +
                ", tags=" + tags.size() +
                '}';
    }
}
