// Ryo Kato, 200605831

package com.georgiancollege.finalexam_200605831;

public class Product {
  private String sku;
  private String name;
  private double salePrice;
  private double regularPrice;
  private String image;

  public Product() { }

  public Product(String sku, String name, double salePrice, double regularPrice, String image) {
    this.sku = sku;
    this.name = name;
    this.salePrice = salePrice;
    this.regularPrice = regularPrice;
    this.image = image;
  }

  public String getImage() {
    return image;
  }

  public String getName() {
    return name;
  }

  public double getRegularPrice() {
    return regularPrice;
  }

  public double getSalePrice() {
    return salePrice;
  }

  public String getSku() {
    return sku;
  }

  @Override
  public String toString() {
    return "%s-$%.2f".formatted(name, salePrice);
  }
}
