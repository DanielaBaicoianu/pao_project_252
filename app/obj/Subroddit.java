package obj;
import java.util.ArrayList;
import java.util.HashSet;
public class Subroddit{
     String name;
     private ArrayList<TopLevelPost> posts;
     private HashSet<User> mods;
     public Subroddit(String title, User founder){
        name="r/"+title;
        posts=new ArrayList<>();
        mods=new HashSet<>();
        mods.add(founder); //he who makes the subreddit is always a mod
    }
    public void addMod(User user){
       mods.add(user);
    }
    public void sackMod(User user){
        if(mods.contains(user) == false){
          System.out.println("This isn't a mod");
        }
        else{
           mods.remove(user);
        }
    }
    public void makePost(TopLevelPost newPost){
       posts.add(newPost);
    }
    public int countPosts(){
       return posts.size();
    }
    public TopLevelPost getPostByIndex(int i){
      return posts.get(i);
    }
    public String getName(){
      return name;
    }
}