package service;

import model.Category;
import model.Topic;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryService {
    private List<Category> categories;
    private List<Topic> topics;
    
    private int nextCategoryId = 1;
    private int nextTopicId = 1;
    
    public CategoryService() {
        this.categories = new ArrayList<>();
        this.topics = new ArrayList<>();
    }
    
    public Category createCategory(String name, String description) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                throw new IllegalArgumentException("Category with this name already exists");
            }
        }
        
        Category category = new Category(nextCategoryId++, name, description);
        categories.add(category);
        return category;
    }
    
    public Topic createTopic(String title, String description, User creator, Category category) {
        if (!categories.contains(category)) {
            throw new IllegalArgumentException("Category does not exist in the system");
        }
        
        Topic topic = new Topic(nextTopicId++, title, description, creator, category);
        topics.add(topic);
        category.addTopic(topic);
        return topic;
    }
    
    public Category findCategoryById(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }
    
    public Category findCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }
    
    public Topic findTopicById(int id) {
        for (Topic topic : topics) {
            if (topic.getId() == id) {
                return topic;
            }
        }
        return null;
    }
    
    public List<Topic> getTopicsForCategory(Category category) {
        if (!categories.contains(category)) {
            throw new IllegalArgumentException("Category does not exist in the system");
        }
        return category.getTopics();
    }
    
    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }
    
    public List<Topic> getTopics() {
        return Collections.unmodifiableList(topics);
    }
}
