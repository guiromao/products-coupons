package co.guiromao.spring.productservice.dto;

import java.math.BigDecimal;

public class CouponDto {

    private Long id;
    private String code;
    private BigDecimal discount;
    private String expDate;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

}
