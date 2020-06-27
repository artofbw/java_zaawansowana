package pl.jazapp.app.webapp.auction;

import javax.persistence.*;

@Entity
@Table(name ="auction_photo")
public class AuctionPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @Column(name = "url")
    private String url;

    @Column(name = "order")
    private Integer order;

    public AuctionPhoto() {}

    public AuctionPhoto(Long id, Auction auction, String url) {
        this.id = id;
        this.auction = auction;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
