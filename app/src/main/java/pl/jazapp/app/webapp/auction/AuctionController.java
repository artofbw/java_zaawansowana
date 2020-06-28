package pl.jazapp.app.webapp.auction;

import pl.jazapp.app.repository.AuctionRepository;
import pl.jazapp.app.webapp.user.UserContext;
import pl.jazapp.app.webapp.user.Users;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AuctionController {
    @Inject
    AuctionRepository auctionRepository;
    @Inject
    private UserContext userContext;
    @Inject
    Users users;

    public List<Auction> getAuctionList() {
        return auctionRepository.fetchAll();
    }

    public List<Auction> getAuctionListByUserId() {
        var user = users.getUserEntity(userContext.getUser());
        return auctionRepository.fetchAllByUserId(user);
    }
}
