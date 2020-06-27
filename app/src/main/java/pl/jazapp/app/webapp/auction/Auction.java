package pl.jazapp.app.webapp.auction;

import pl.jazapp.app.webapp.category.Category;
import pl.jazapp.app.webapp.parameter.Parameter;
import pl.jazapp.app.webapp.user.User;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name ="auction")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @DecimalMin(inclusive = true, value = "1")
    private BigDecimal price = new BigDecimal(1);

    @Column(name = "version")
    private int version;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "auction",
        cascade = {CascadeType.MERGE, CascadeType.REMOVE},
        orphanRemoval = true
    )
    @OrderColumn(name = "order")
    private List<AuctionPhoto> auctionPhotoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public List<AuctionPhoto> getAuctionPhotoList() {
        return auctionPhotoList;
    }

    public void setAuctionPhotoList(List<AuctionPhoto> auctionPhotoList) {
        this.auctionPhotoList = auctionPhotoList;
    }

    public Auction(
        Long id,
        String title,
        String description,
        @DecimalMin(inclusive = true, value = "1") BigDecimal price,
        User created_by,
        Category category_id,
        List<AuctionPhoto> auctionPhotos
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.version = 1;
        this.createdBy = created_by;
        this.categoryId = category_id;
        this.auctionPhotoList = auctionPhotos;
    }

    public Auction() {}
}
