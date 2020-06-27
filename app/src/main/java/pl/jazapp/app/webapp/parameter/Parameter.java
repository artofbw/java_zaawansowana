package pl.jazapp.app.webapp.parameter;

import pl.jazapp.app.webapp.auction.Auction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parameter")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "key")
    private String name;

//    @ManyToMany(mappedBy = "auction_parameter")
//    private Set<Auction> auction;

    public Parameter() {}

    public Parameter(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Set<Auction> getAuction() {
//        return auction;
//    }
//
//    public void setAuction(Set<Auction> auction) {
//        this.auction = auction;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
