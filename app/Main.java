import java.util.Date;
import obj.User;
import obj.Roddit;
import obj.Subroddit;
import obj.Feed;
import obj.Post;
import obj.TopLevelPost;
import obj.Report;
import serv.ServImpl;
public class Main {
    public static void main(String[] args) throws Exception {
        ServImpl service=new ServImpl();
        service.signup("alice", "pass1");
        service.signup("bernardo", "pass2");

        service.login("alice", "pass1");
        service.login("bernardo", "pass2");
        service.makeSubreddit("sub1", "bernardo");
        service.grantModness("sub1", "alice");
        Subroddit sub1=service.getSubByName("sub1");
        service.makePost("sub1", "I am Bernardo", "This is Bernardo and I'm here to rock", "bernardo");
        TopLevelPost examplePost=sub1.getPostByIndex(0);
        service.vote("alice", examplePost, true);
        service.comment(examplePost, "And I am Alice");
        service.printThread(examplePost);
        service.getFeed("sub1");//this is a 
        service.getKarma("alice");
        service.reportPost("alice", examplePost, "Testing reasons");

    }
}
//CAPABILITIES:
//1: create account W
//2: log into account W
//3: make a sub W
//4: post in a sub W
//5: Report a post W
//6: comment on a post W
//7: see all comments on a post W 
//8: see all posts in a sub W
//9: grant/revoke modness to other users W
//10: upvote/downvote posts W
//11: add a reply to comments (TBD?)
//12: find a user's karma (how many upvotes and downvotes he has on his posts) W
//13: (I dunno) see a random selecao of posts from all subs (Feed) 