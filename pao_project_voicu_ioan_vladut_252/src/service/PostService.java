package service;

import model.Post;
import model.Tag;
import model.Topic;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PostService {
    private List<Post> posts;
    private int nextPostId = 1;
    
    private final TagService tagService;
    
    public PostService(TagService tagService) {
        this.posts = new ArrayList<>();
        this.tagService = tagService;
    }
    
    public Post createPost(String title, String content, User author, Topic topic) {
        if (!author.isActive()) {
            throw new IllegalArgumentException("User is not active");
        }
        
        Post post = new Post(nextPostId++, title, content, author, topic);
        posts.add(post);
        topic.addPost(post);
        author.addPost(post);
        return post;
    }
    
    public void addTagToPost(Post post, String tagName) {
        if (!posts.contains(post)) {
            throw new IllegalArgumentException("Post does not exist in the system");
        }
        
        Tag tag = tagService.findOrCreateTag(tagName);
        post.addTag(tag);
    }
    
    public void removeTagFromPost(Post post, Tag tag) {
        if (!posts.contains(post)) {
            throw new IllegalArgumentException("Post does not exist in the system");
        }
        
        post.removeTag(tag);
    }
    
    public Post findPostById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }
    
    public List<Post> getPostsForTopic(Topic topic) {
        return topic.getPosts();
    }
    
    public List<Post> getPostsByAuthor(User author) {
        return author.getPosts();
    }
    
    public List<Post> getPostsSortedByPopularity() {
        List<Post> sortedPosts = new ArrayList<>(posts);
        Collections.sort(sortedPosts, Comparator.comparing(Post::getPopularityScore).reversed());
        return sortedPosts;
    }
    
    public List<Post> getPostsSortedByDate() {
        List<Post> sortedPosts = new ArrayList<>(posts);
        Collections.sort(sortedPosts, Comparator.comparing(Post::getCreationDate).reversed());
        return sortedPosts;
    }
    
    public List<Post> getPosts() {
        return Collections.unmodifiableList(posts);
    }
}
