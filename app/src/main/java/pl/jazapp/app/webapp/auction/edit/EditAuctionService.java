package pl.jazapp.app.webapp.auction.edit;

import pl.jazapp.app.webapp.auction.Auction;
import pl.jazapp.app.webapp.category.Category;
import pl.jazapp.app.webapp.user.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class EditAuctionService {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void saveAuction(Auction auction) {
        if(auction.getId() == null) {
            em.persist(auction);
        } else {
            em.merge(auction);
        }
    }

    public Auction getAuctionById(Long auctionId) {
        return em.find(Auction.class, auctionId);
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return Optional.ofNullable(em.find(Category.class, categoryId));
    }
}
