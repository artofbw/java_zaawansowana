package pl.jazapp.app.webapp.auction.edit;

import pl.jazapp.app.ParameterRetriever;
import pl.jazapp.app.webapp.user.UserContext;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

    public EditAuctionRequest getEditAuctionRequest() {
        if(editAuctionRequest == null) {
            if(parameterRetriever.contains("auctionId")) {
                var auctionId = parameterRetriever.getParameterAsLong("auctionId");
                var auctionEntity = editAuctionService.getAuctionById(auctionId);
                editAuctionRequest = new EditAuctionRequest(auctionEntity);
            } else {
                editAuctionRequest = new EditAuctionRequest();
            }
        }
        return editAuctionRequest;
    }

    public String save() {
        var category = editAuctionService.getCategoryById(editAuctionRequest.getCategoryId()).get();
        var createdBy = userContext.getUser().get();
        var auction = editAuctionRequest.toAuctionEntity();
        auction.setCategoryId(category);
        auction.setCreatedBy(createdBy);
        editAuctionService.saveAuction(auction);
        return "/auctions/mine.xhtml?faces-redirect=true";
    }
}
