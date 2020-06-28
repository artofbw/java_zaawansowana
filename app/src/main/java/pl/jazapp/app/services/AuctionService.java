package pl.jazapp.app.services;

import pl.jazapp.app.webapp.auction.Auction;
import pl.jazapp.app.webapp.category.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class AuctionService {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void saveAuction(Auction auction) {
        if(auction.getId() == null) {
            em.persist(auction);
        } else {
            em.merge(auction);
        }
        auction.getAuctionPhotoList().forEach(photo -> {
            if(photo.getId() == null) {
                em.persist(photo);
            } else {
                em.merge(photo);
            }
        });
    }

    public Auction getAuctionById(Long auctionId) {
        return em.find(Auction.class, auctionId);
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return Optional.ofNullable(em.find(Category.class, categoryId));
    }
}
