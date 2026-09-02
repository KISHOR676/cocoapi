package com.cocobar.cocoapi.dto;

public class TopItemDTO {
    private String itemName;
    private Long quantitySold;

    public TopItemDTO(String itemName, Long quantitySold) {
        this.itemName = itemName;
        this.quantitySold = quantitySold;
    }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public Long getQuantitySold() { return quantitySold; }
    public void setQuantitySold(Long quantitySold) { this.quantitySold = quantitySold; }
}