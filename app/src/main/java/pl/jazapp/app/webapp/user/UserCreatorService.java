package pl.jazapp.app.webapp.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserCreatorService {
    @PersistenceContext
    private EntityManager em;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void createUser(String username, User user) {
        var userEntity = user;
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        em.persist(userEntity);
    }
}
