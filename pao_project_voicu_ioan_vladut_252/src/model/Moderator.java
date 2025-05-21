package model;

import java.util.ArrayList;
import java.util.List;

public class Moderator extends User {
    private List<Category> moderatedCategories;
    
    public Moderator(int id, String username, String email, String password) {
        super(id, username, email, password);
        this.moderatedCategories = new ArrayList<>();
    }
    
    public List<Category> getModeratedCategories() {
        return moderatedCategories;
    }
    
    public void addModeratedCategory(Category category) {
        moderatedCategories.add(category);
    }
    
    public void removeModeratedCategory(Category category) {
        moderatedCategories.remove(category);
    }
    
    public void deletePost(Post post) {
        // Logic to delete a post
    }
    
    public void deleteComment(Comment comment) {
        // Logic to delete a comment
    }
    
    @Override
    public String toString() {
        return "Moderator{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", moderatedCategories=" + moderatedCategories.size() +
                '}';
    }
}
