package pl.jazapp.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class Users {
    @PersistenceContext
    private EntityManager em;

    private static Map<String, User> users = new ConcurrentHashMap<>();

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private User getUserEntity(String username) {
        var emManager = em.getEntityManagerFactory().createEntityManager();
        TypedQuery<User> query = emManager.createQuery("SELECT u FROM User u WHERE u.username = ?1", User.class);
        query.setParameter(1, username);
        return query.getSingleResult();
    }

    public Boolean userExists(String username) {
        try {
            getUserEntity(username);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public String getFullName(String username) {
        try {
            User user = getUserEntity(username);
            return user.getFullName();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User getUser(String username, String password) {
        try {
            var user = getUserEntity(username);

            if(isUsernameAndPasswordCorrect(password, user.getPassword())) {
                return user;
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    public Boolean isUsernameAndPasswordCorrect(String requestedPassword, String password) {
        if (passwordEncoder.matches(requestedPassword, password)) {
            return true;
        } else {
            return false;
        }
    }
}
