package pl.jazapp.app.webapp.auth;

import pl.jazapp.app.webapp.auction.edit.EditAuctionController;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/auctions/edit.xhtml"})
public class IsAuctionOwnerFilter extends HttpFilter {
    @Inject
    EditAuctionController editAuctionController;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        var auctionId = req.getParameter("auctionId");
        if(auctionId != null) {
            var isOwner = editAuctionController.checkIfIsAuctionOwner(auctionId);
            if(isOwner) {
                chain.doFilter(req, res);
            } else {
                res.sendRedirect(req.getContextPath() + "/");
            }
        } else {
            chain.doFilter(req, res);
        }
    }
}
