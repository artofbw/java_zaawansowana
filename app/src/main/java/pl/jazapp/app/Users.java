package pl.jazapp.app;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class Users {
    private static Map<String, User> users = new ConcurrentHashMap<>();

    public static Boolean userExists(String username) {
        return users.containsKey(username);
    }

    public static User getUser(String username) {
        return users.get(username);
    }

    public static void addUser(String username, User user) {
        users.put(username, user);
    }

    public static Boolean isUsernameAndPasswordCorrect(String username, String password) {
        try {
            var user = users.get(username);
            return user.getUsername().equals(username) && user.getPassword().equals(password);
        } catch (NullPointerException e) {
            return false;
        }
    }
}
