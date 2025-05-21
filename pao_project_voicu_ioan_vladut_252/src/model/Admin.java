package model;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<User> bannedUsers;
    
    public Admin(int id, String username, String email, String password) {
        super(id, username, email, password);
        this.bannedUsers = new ArrayList<>();
    }
    
    public void banUser(User user) {
        user.setActive(false);
        bannedUsers.add(user);
    }
    
    public void unbanUser(User user) {
        user.setActive(true);
        bannedUsers.remove(user);
    }
    
    public List<User> getBannedUsers() {
        return bannedUsers;
    }
    
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", bannedUsers=" + bannedUsers.size() +
                '}';
    }
}
