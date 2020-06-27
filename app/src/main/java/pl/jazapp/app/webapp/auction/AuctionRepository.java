package pl.jazapp.app.webapp.auction;

import pl.jazapp.app.webapp.user.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class AuctionRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Auction> fetchAll() {
        Query query = em.createQuery("FROM Auction", Auction.class);
        return query.getResultList();
    }

    public List<Auction> fetchAllByUserId(User user) {
        TypedQuery<Auction> query = em.getEntityManagerFactory().createEntityManager().createQuery(
                "SELECT a FROM Auction a WHERE a.createdBy = ?1", Auction.class
        );
        query.setParameter(1, user);
        return query.getResultList();
    }
}
