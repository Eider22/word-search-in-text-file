package com.eider.controllers.dtos;

public class DetailsWordTimesQuantityInFileDto {
    private String fileName;
    private int quantity;

    public DetailsWordTimesQuantityInFileDto() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
