package models;

public class Product {
    private String productCode;
    private String name;
    private String description;
    private double price;

    public Product(String productCode, String name, String description, double price) {
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}