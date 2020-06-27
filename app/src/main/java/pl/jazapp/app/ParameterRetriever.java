package pl.jazapp.app;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
public class ParameterRetriever {
    @Inject
    private HttpServletRequest request;

    public boolean contains(String param) {
        return request.getParameter(param) != null;
    }

    public Long getParameterAsLong(String param) {
        return Long.parseLong(request.getParameter(param));
    }
}
