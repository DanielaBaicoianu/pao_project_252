package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {
    private int id;
    private String content;
    private User author;
    private Post post;
    private Date creationDate;
    private List<Reaction> reactions;
    private Comment parentComment; // For replies to comments
    private List<Comment> replies;
    
    public Comment(int id, String content, User author, Post post) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.post = post;
        this.creationDate = new Date();
        this.reactions = new ArrayList<>();
        this.replies = new ArrayList<>();
    }
    
    // Constructor for replies to other comments
    public Comment(int id, String content, User author, Post post, Comment parentComment) {
        this(id, content, author, post);
        this.parentComment = parentComment;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
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
    
    public Post getPost() {
        return post;
    }
    
    public Date getCreationDate() {
        return creationDate;
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
    
    public Comment getParentComment() {
        return parentComment;
    }
    
    public List<Comment> getReplies() {
        return replies;
    }
    
    public void addReply(Comment reply) {
        replies.add(reply);
    }
    
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + (content.length() > 20 ? content.substring(0, 20) + "..." : content) + '\'' +
                ", author=" + author.getUsername() +
                ", creationDate=" + creationDate +
                ", reactionsCount=" + reactions.size() +
                ", repliesCount=" + replies.size() +
                '}';
    }
}
