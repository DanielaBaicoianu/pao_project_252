package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Topic {
    private int id;
    private String title;
    private String description;
    private User creator;
    private Category category;
    private Date creationDate;
    private List<Post> posts;
    
    public Topic(int id, String title, String description, User creator, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.category = category;
        this.creationDate = new Date();
        this.posts = new ArrayList<>();
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public User getCreator() {
        return creator;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public List<Post> getPosts() {
        return posts;
    }
    
    public void addPost(Post post) {
        posts.add(post);
    }
    
    public void removePost(Post post) {
        posts.remove(post);
    }
    
    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creator=" + creator.getUsername() +
                ", category=" + category.getName() +
                ", creationDate=" + creationDate +
                ", postsCount=" + posts.size() +
                '}';
    }
}
