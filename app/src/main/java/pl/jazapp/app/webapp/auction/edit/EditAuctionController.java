package pl.jazapp.app.webapp.auction.edit;

import pl.jazapp.app.ParameterRetriever;
import pl.jazapp.app.webapp.auction.AuctionPhoto;
import pl.jazapp.app.repository.AuctionRepository;
import pl.jazapp.app.webapp.user.UserContext;
import pl.jazapp.app.webapp.user.Users;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.LinkedList;

@Named
@RequestScoped
public class EditAuctionController {
    private EditAuctionRequest editAuctionRequest;

    @Inject
    UserContext userContext;

    @Inject
    EditAuctionService editAuctionService;

    @Inject
    ParameterRetriever parameterRetriever;

    @Inject
    Users users;

    @Inject
    AuctionRepository auctionRepository;

    public boolean checkIfIsAuctionOwner(String auctionId) {
        var user = users.getUserEntity(userContext.getUser());
        var userAuctions = auctionRepository.fetchAllByUserId(user);
        return userAuctions.stream().anyMatch(auction -> auction.getId().longValue() == Long.parseLong(auctionId));
    }

    public EditAuctionRequest getEditAuctionRequest() {
        if(editAuctionRequest == null) {
            if(parameterRetriever.contains("auctionId")) {
                var auctionId = parameterRetriever.getParameterAsLong("auctionId");
                var auctionEntity = editAuctionService.getAuctionById(auctionId);
                editAuctionRequest = new EditAuctionRequest(auctionEntity);

                var photoList = auctionEntity.getAuctionPhotoList();
                var photoListSize = photoList.size();
                if(photoListSize > 0) {
                    if(photoListSize == 1) {
                        editAuctionRequest.setPhotoLink1(photoList.get(0).getUrl());
                    }
                    if(photoListSize == 2) {
                        editAuctionRequest.setPhotoLink1(photoList.get(0).getUrl());
                        editAuctionRequest.setPhotoLink2(photoList.get(1).getUrl());
                    }
                    if(photoListSize == 3) {
                        editAuctionRequest.setPhotoLink1(photoList.get(0).getUrl());
                        editAuctionRequest.setPhotoLink2(photoList.get(1).getUrl());
                        editAuctionRequest.setPhotoLink3(photoList.get(2).getUrl());
                    }
                }
            } else {
                editAuctionRequest = new EditAuctionRequest();
            }
        }
        return editAuctionRequest;
    }

    public String save() {
        var category = editAuctionService.getCategoryById(editAuctionRequest.getCategoryId()).get();
        var createdBy = users.getUserEntity(userContext.getUser());
        var auction = editAuctionRequest.toAuctionEntity();
        auction.setCategoryId(category);
        auction.setCreatedBy(createdBy);

        var auctionPhotos = new LinkedList<AuctionPhoto>();
        var order = 0;
        if(!editAuctionRequest.getPhotoLink1().isEmpty()) {
            auctionPhotos.add(order, new AuctionPhoto(null, auction, editAuctionRequest.getPhotoLink1()));
            order++;
        }
        if(!editAuctionRequest.getPhotoLink2().isEmpty()) {
            auctionPhotos.add(order, new AuctionPhoto(null, auction, editAuctionRequest.getPhotoLink2()));
            order++;
        }
        if(!editAuctionRequest.getPhotoLink3().isEmpty()) {
            auctionPhotos.add(order, new AuctionPhoto(null, auction, editAuctionRequest.getPhotoLink3()));
            order++;
        }
        auction.setAuctionPhotoList(auctionPhotos);

        editAuctionService.saveAuction(auction);
        return "/auctions/mine.xhtml?faces-redirect=true";
    }
}
