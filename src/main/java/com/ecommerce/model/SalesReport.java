package com.ecommerce.model;

public class SalesReport {

    private String productName;

    private int totalSold;

    public String getProductName() {
        return productName;
    }

    public void setProductName(
            String productName) {

        this.productName = productName;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(
            int totalSold) {

        this.totalSold = totalSold;
    }
}