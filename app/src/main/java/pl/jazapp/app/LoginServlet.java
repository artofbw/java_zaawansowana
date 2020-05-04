package pl.jazapp.app;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("login")
public class LoginServlet extends HttpServlet {
    @Inject
    private UserContext userContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var writer = resp.getWriter();
        resp.setStatus(200);
        resp.setContentType("text/plain");

        writer.println(String.format("I am logged: %s", userContext.isLogged()));
        userContext.logIn();
    }
}
