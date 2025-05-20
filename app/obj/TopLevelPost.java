package obj;
import java.util.ArrayList;

public class TopLevelPost extends Post{
    String title;
    private ArrayList<Post> comms;
    public TopLevelPost(String title, String text, Subroddit sub, User author){
        super(text, sub, author);
        this.title=title;
        comms=new ArrayList<>();
    }
    public void makeComment(String text){
        Post tempComment=new Post(text, this.sub, this.author);
        comms.add(tempComment); 
    }
    public void peek(){
        System.out.println(title);
        System.out.println("CONTENT: ");
        System.out.println(content);
        System.out.println("VOTES:");
        System.out.println(score);
        System.out.println("COMMENTS:");
        System.out.println(comms.size());
    }
    public void expose(){
        System.out.println(title);
        System.out.println("CONTENT: ");
        System.out.println(content);
        System.out.println("VOTES:");
        System.out.println(score);
        System.out.println("COMMENTS:");
        for(var comment: comms){
            comment.expose();
        }
    }
}