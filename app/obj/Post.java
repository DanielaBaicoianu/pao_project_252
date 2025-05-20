package obj;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;
public class Post { //should this be an interface? it has a lot of fields
    protected String content;
    HashMap<User, Vote> votes; //which user has which vote, for O(1) lookup of voters
    int score;
    User author;
    Subroddit sub;
    LocalDateTime timestamp;
    Post(String text, Subroddit sub, User author){
       content=text;
       this.sub=sub;
       this.author=author;
       timestamp=LocalDateTime.now();
       votes=new HashMap<>();
       score=0;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    public void vote(User voter, boolean isUpvote) {
        if (votes.containsKey(voter)){
            System.out.println("Ai votat de 2 ori"); 
            return;
        }
        votes.put(voter, new Vote(voter, isUpvote));
        score += isUpvote ? 1 : -1;
        if(isUpvote){
            author.giveKarma();
        }
        else{
            author.takeKarma();
        }
    }
    public void expose(){
        System.out.println(content);
        System.out.println(String.format("Voturi: %d", score));
    }
    public String getBrief(int limit){
        if(content.length()>limit){
            return content.substring(0, limit);
        }
        return content;
    }
}
