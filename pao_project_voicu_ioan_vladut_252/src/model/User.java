package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Date registrationDate;
    private boolean active;
    private List<Post> posts;
    private List<Comment> comments;
    
    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.registrationDate = new Date();
        this.active = true;
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Date getRegistrationDate() {
        return registrationDate;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public List<Post> getPosts() {
        return posts;
    }
    
    public List<Comment> getComments() {
        return comments;
    }
    
    public void addPost(Post post) {
        posts.add(post);
    }
    
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    
    public int getActivityScore() {
        return posts.size() * 10 + comments.size() * 2;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", active=" + active +
                ", postsCount=" + posts.size() +
                ", commentsCount=" + comments.size() +
                '}';
    }
}
