package pl.jazapp.app;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class UserContext implements Serializable {
    private static final Long serialVersionUid = 1L;
    private boolean isLogged;

    public boolean isLogged() {
        return isLogged;
    }

    public void logIn() {
        isLogged = true;
    }
}
