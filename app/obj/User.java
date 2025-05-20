package obj;
import java.util.Date;
import java.util.HashSet;
public class User {
    private String username;
    private Date creationDate;
    private int karma;
    private HashSet<Post> votedPosts;
    public User(String user, Date creation){
        username=user;
        creationDate=creation;
        karma=0; //user has no karma whe his account is made
    }
    public void giveKarma(){
        karma++; //but he gains it when he gets an upvote
    }
    public void takeKarma(){
        karma--;
    }
    public Subroddit makeSubroddit(String title){
         Subroddit newSub=new Subroddit(title, this);
         return newSub;
    }
    public String getName(){
        return username;
    }
    public int getKarma(){
        return karma;
    }
}
