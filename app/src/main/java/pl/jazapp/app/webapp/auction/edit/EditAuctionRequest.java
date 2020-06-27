package pl.jazapp.app.webapp.auction.edit;

import pl.jazapp.app.webapp.auction.Auction;
import pl.jazapp.app.webapp.auction.AuctionPhoto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;

@Named
@RequestScoped
public class EditAuctionRequest {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private int version;
    private Long createdBy;
    private Long categoryId;
    private List<AuctionPhoto> photoList;

    public EditAuctionRequest(Auction auction) {
        this.id = auction.getId();
        this.title = auction.getTitle();
        this.description = auction.getDescription();
        this.price = auction.getPrice();
        this.version = auction.getVersion();
        this.createdBy = auction.getCreatedBy().getId();
        this.categoryId = auction.getCategoryId().getId();
        this.photoList = auction.getAuctionPhotoList();
    }

    public EditAuctionRequest() {}

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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<AuctionPhoto> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<AuctionPhoto> photoList) {
        this.photoList = photoList;
    }

    public Auction toAuctionEntity() {
        var auction = new Auction();
        auction.setId(id);
        auction.setTitle(title);
        auction.setDescription(description);
        auction.setPrice(price);
        auction.setVersion(1);
        auction.setAuctionPhotoList(photoList);
        return auction;
    }
}
