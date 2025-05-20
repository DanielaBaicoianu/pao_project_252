package obj;
import java.util.ArrayList;
import java.util.Comparator;
public class Feed{
    ArrayList<TopLevelPost> posts;
    String title;
    public Feed(Subroddit sub){//a feed of a sub
        int limitPosts=10;
        title=sub.getName();
        posts=new ArrayList<>();
        for(int i=0; i<Math.min(limitPosts, sub.countPosts()); i++){
             posts.add(sub.getPostByIndex(i));
        }
        posts.sort(Comparator.comparing(TopLevelPost::getTimestamp));
    }
    public void expose(){
        System.out.println(title);
        for(var post: posts){
            post.peek();
        }
    }
}