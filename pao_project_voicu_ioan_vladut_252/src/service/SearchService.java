package service;

import model.Post;
import model.Tag;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private final PostService postService;
    private final TagService tagService;
    
    public SearchService(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }
    
    public List<Post> searchPostsByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String lowercaseKeyword = keyword.toLowerCase();
        List<Post> result = new ArrayList<>();
        
        for (Post post : postService.getPosts()) {
            if (post.getTitle().toLowerCase().contains(lowercaseKeyword) || 
                post.getContent().toLowerCase().contains(lowercaseKeyword)) {
                result.add(post);
            }
        }
        
        return result;
    }
    
    public List<Post> searchPostsByTag(String tagName) {
        Tag tag = tagService.findTagByName(tagName);
        if (tag == null) {
            return new ArrayList<>();
        }
        
        List<Post> result = new ArrayList<>();
        for (Post post : postService.getPosts()) {
            if (post.getTags().contains(tag)) {
                result.add(post);
            }
        }
        
        return result;
    }
    
    public List<Post> searchPostsByAuthor(String authorName) {
        List<Post> result = new ArrayList<>();
        for (Post post : postService.getPosts()) {
            if (post.getAuthor().getUsername().toLowerCase().contains(authorName.toLowerCase())) {
                result.add(post);
            }
        }
        
        return result;
    }
}
