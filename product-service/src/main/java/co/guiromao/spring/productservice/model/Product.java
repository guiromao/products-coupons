package co.guiromao.spring.productservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    @Transient
    private String couponCode;

    public Product(String name, String description, BigDecimal price, String couponCode) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.couponCode = couponCode;
    }

    public Product withPrice(BigDecimal updatedPrice) {
        return new Product(this.name, this.description, updatedPrice, this.couponCode);
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
