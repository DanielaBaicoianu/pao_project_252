package service;

import model.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagService {
    private Set<Tag> tags;
    private int nextTagId = 1;
    
    public TagService() {
        this.tags = new HashSet<>();
    }
    
    public Tag createTag(String name) {
        for (Tag tag : tags) {
            if (tag.getName().equalsIgnoreCase(name)) {
                return tag;
            }
        }
        
        Tag tag = new Tag(nextTagId++, name);
        tags.add(tag);
        return tag;
    }
    
    public Tag findOrCreateTag(String name) {
        for (Tag tag : tags) {
            if (tag.getName().equalsIgnoreCase(name)) {
                return tag;
            }
        }
        
        return createTag(name);
    }
    
    public Tag findTagByName(String name) {
        for (Tag tag : tags) {
            if (tag.getName().equalsIgnoreCase(name)) {
                return tag;
            }
        }
        return null;
    }
    
    public Tag findTagById(int id) {
        for (Tag tag : tags) {
            if (tag.getId() == id) {
                return tag;
            }
        }
        return null;
    }
    
    public void deleteTag(Tag tag) {
        tags.remove(tag);
    }
    
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
    
    public List<Tag> getTagsAsList() {
        return new ArrayList<>(tags);
    }
}
