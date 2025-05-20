package serv;
import obj.Post;
import obj.TopLevelPost;
public interface Serv{
     void signup(String name, String password);
     void login(String name, String password);
     void makeSubreddit(String name, String founderName);
     void grantModness(String name, String newMod);
     void revokeModness(String name, String sackedMod);
     void makePost(String subName, String title, String text, String userName);
     void reportPost(String user, Post reportedPost, String reason);
     void comment(TopLevelPost post, String comm);
     void printThread(Post post);
     void getFeed(String subName);
     void vote(String user, Post post, boolean isUpvote);
     void getKarma(String user); 
}