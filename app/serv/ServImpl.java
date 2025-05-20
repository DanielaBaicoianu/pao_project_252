package serv;
import java.util.Date;
import java.util.HashMap;
import obj.Roddit;
import obj.Subroddit;
import obj.TopLevelPost;
import obj.User;
import obj.Post;
import obj.Feed;
import obj.Report;
public class ServImpl implements Serv{
    Roddit roddit;
    Date systemDate;
    HashMap<String, User> userReference;
    public ServImpl(){
        roddit=new Roddit();
        systemDate=new Date();
        userReference=new HashMap<>();
    }
    public void signup(String username, String password){
         User newUser=new User(username, systemDate);
         roddit.signup(newUser, password);
         userReference.put(username, newUser);
    }
    public void login(String name, String password){
        User attemptedLogin=userReference.get(name);
        roddit.login(attemptedLogin, password);
    }
    public void makeSubreddit(String name, String founderName){
        User founder=userReference.get(founderName);
        roddit.addSub(founder, name);
    }
    public void grantModness(String subName, String newMod){
        User newModRef=userReference.get(newMod);
        Subroddit sub=roddit.getSubByName(subName);
        sub.addMod(newModRef);
    }
    public void revokeModness(String subName, String newMod){
        User newModRef=userReference.get(newMod);
        Subroddit sub=roddit.getSubByName(subName);
        sub.sackMod(newModRef);
    }
    public void reportPost(String username, Post reportedPost, String reason){
        User user=userReference.get(username);
        Report newReport=new Report(user, reportedPost, reason);
        newReport.log();
    }
    public void makePost(String subName, String title, String text, String authorName){
        TopLevelPost newThread=new TopLevelPost(title, text, roddit.getSubByName(subName), userReference.get(authorName));
        roddit.getSubByName(subName).makePost(newThread);
    }
    public void comment(TopLevelPost post, String text){
        post.makeComment(text);
    }
    public void printThread(Post post){
        post.expose();
    }
    public void getFeed(String subName){
        Feed newFeed=new Feed(roddit.getSubByName(subName));
        newFeed.expose();
    }
    public void vote(String user, Post post, boolean isUpvote){
        post.vote(userReference.get(user), isUpvote);
    }
    public void getKarma(String User){
        User sought=userReference.get(User);
        System.out.println(sought.getKarma());
    }
    public Subroddit getSubByName(String name){
        return roddit.getSubByName(name);
    }
}