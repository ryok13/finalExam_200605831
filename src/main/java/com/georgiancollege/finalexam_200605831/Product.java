// Ryo Kato, 200605831

package com.georgiancollege.finalexam_200605831;

public class Product {
  private String sku;
  private String name;
  private double salePrice;
  private double regularPrice;
  private String images;

  public Product() { }

  public Product(String sku, String name, double salePrice, double regularPrice, String images) {
    this.sku = sku;
    this.name = name;
    this.salePrice = salePrice;
    this.regularPrice = regularPrice;
    this.images = images;
  }

  public String getImages() {
    return images;
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
