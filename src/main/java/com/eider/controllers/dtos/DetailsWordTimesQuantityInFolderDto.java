package com.eider.controllers.dtos;

import java.util.List;

public class DetailsWordTimesQuantityInFolderDto {

    private List<DetailsWordTimesQuantityInFileDto> detailsWordTimesQuantityInFiles;
    private int quantity;

    public DetailsWordTimesQuantityInFolderDto() {
    }

    public List<DetailsWordTimesQuantityInFileDto> getDetailsWordTimesQuantityInFiles() {
        return detailsWordTimesQuantityInFiles;
    }

    public void setDetailsWordTimesQuantityInFiles(
            List<DetailsWordTimesQuantityInFileDto> detailsWordTimesQuantityInFiles) {
        this.detailsWordTimesQuantityInFiles = detailsWordTimesQuantityInFiles;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
