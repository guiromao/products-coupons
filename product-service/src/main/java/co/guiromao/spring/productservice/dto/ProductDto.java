package co.guiromao.spring.productservice.dto;

import co.guiromao.spring.productservice.model.Product;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String couponCode;

    public ProductDto(Long id, String name, String description, BigDecimal price, String couponCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.couponCode = couponCode;
    }

    public ProductDto withPrice(BigDecimal updatedPrice) {
        return new ProductDto(this.id, this.name, this.description, updatedPrice, this.couponCode);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCouponCode() {
        return couponCode;
    }

}
