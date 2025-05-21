package service;

import model.Admin;
import model.Moderator;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserService {
    private List<User> users;
    private List<Admin> admins;
    private List<Moderator> moderators;
    
    private int nextUserId = 1;
    
    public UserService() {
        this.users = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.moderators = new ArrayList<>();
    }
    
    public User registerUser(String username, String email, String password) {
        if (findUserByEmail(email) != null) {
            throw new IllegalArgumentException("Email already in use");
        }
        
        User user = new User(nextUserId++, username, email, password);
        users.add(user);
        return user;
    }
    
    public Admin registerAdmin(String username, String email, String password) {
        if (findUserByEmail(email) != null) {
            throw new IllegalArgumentException("Email already in use");
        }
        
        Admin admin = new Admin(nextUserId++, username, email, password);
        admins.add(admin);
        users.add(admin);
        return admin;
    }
    
    public Moderator registerModerator(String username, String email, String password) {
        if (findUserByEmail(email) != null) {
            throw new IllegalArgumentException("Email already in use");
        }
        
        Moderator moderator = new Moderator(nextUserId++, username, email, password);
        moderators.add(moderator);
        users.add(moderator);
        return moderator;
    }
    
    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
    
    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    
    public User authenticateUser(String email, String password) {
        User user = findUserByEmail(email);
        if (user != null && user.getPassword().equals(password) && user.isActive()) {
            return user;
        }
        return null;
    }
    
    public void banUser(Admin admin, User userToBan) {
        if (!admins.contains(admin)) {
            throw new IllegalArgumentException("User is not an admin");
        }
        admin.banUser(userToBan);
    }
    
    public void unbanUser(Admin admin, User userToUnban) {
        if (!admins.contains(admin)) {
            throw new IllegalArgumentException("User is not an admin");
        }
        admin.unbanUser(userToUnban);
    }
    
    public List<User> getUsersSortedByActivity() {
        List<User> sortedUsers = new ArrayList<>(users);
        Collections.sort(sortedUsers, Comparator.comparing(User::getActivityScore).reversed());
        return sortedUsers;
    }
    
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
    
    public List<Admin> getAdmins() {
        return Collections.unmodifiableList(admins);
    }
    
    public List<Moderator> getModerators() {
        return Collections.unmodifiableList(moderators);
    }
}
