package obj;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
public class Roddit{
    private HashMap<String, String> accounts; //this holds (user, password) keys, where the key is held encrypted
    private HashMap<String, Subroddit> subs;
    private HashSet<String> loggedInUsers;
    //TASK: move this to a database
    public Roddit(){
        accounts=new HashMap<>();
        subs=new HashMap<>();
        loggedInUsers=new HashSet<>();
    }
    public void signup(User user, String password){
      if(accounts.containsKey(user.getName()) == false){
        accounts.put(user.getName(), password); //TODO don't hold the password in plaintext
      }
      else{
         System.out.println("Username deja in uz");
      }
    }
    public void login(User user, String password){
        String username=user.getName();
        String rightPass=accounts.get(username);
        if(password.equals(rightPass)){
           loggedInUsers.add(username);
        }
        else{
            System.out.println("Login esuat");
        }
    }
    public void addSub(User user, String subName){ 
        if(loggedInUsers.contains(user.getName()) == false){ //should I use exception handling?
            System.out.println("Login necesar");
        }
        else{
            Subroddit newSub=user.makeSubroddit(subName);
            subs.put(subName, newSub);
        }
    }
    public Subroddit getSubByName(String key){
        return subs.get(key);
    }
}