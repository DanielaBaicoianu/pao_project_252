package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private String description;
    private List<Topic> topics;
    
    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.topics = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Topic> getTopics() {
        return topics;
    }
    
    public void addTopic(Topic topic) {
        topics.add(topic);
    }
    
    public void removeTopic(Topic topic) {
        topics.remove(topic);
    }
    
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", topicsCount=" + topics.size() +
                '}';
    }
}
