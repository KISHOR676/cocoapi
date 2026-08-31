package com.cocobar.cocoapi.model;

public class SaleRequest {
    private Long recipeId;
    private Integer quantity;

    public Long getRecipeId() { return recipeId; }
    public void setRecipeId(Long recipeId) { this.recipeId = recipeId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}