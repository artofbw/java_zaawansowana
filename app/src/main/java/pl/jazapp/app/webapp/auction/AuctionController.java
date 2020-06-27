package pl.jazapp.app.webapp.auction;

import pl.jazapp.app.webapp.user.UserContext;

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

    public List<Auction> getAuctionList() {
        return auctionRepository.fetchAll();
    }

    public List<Auction> getAuctionListByUserId() {
        var user = userContext.getUser().get();
        return auctionRepository.fetchAllByUserId(user);
    }
}
