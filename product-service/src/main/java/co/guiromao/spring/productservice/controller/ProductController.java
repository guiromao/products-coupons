package co.guiromao.spring.productservice.controller;

import co.guiromao.spring.productservice.converter.ProductConverter;
import co.guiromao.spring.productservice.dto.CouponDto;
import co.guiromao.spring.productservice.dto.ProductDto;
import co.guiromao.spring.productservice.model.Product;
import co.guiromao.spring.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/products-api/products")
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;

    @Value("${couponService.url}")
    private String couponServiceUrl;

    @Autowired
    public ProductController(ProductService service, RestTemplate restTemplate) {
        this.productService = service;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        CouponDto coupon =
                restTemplate.getForObject(couponServiceUrl + productDto.getCouponCode(), CouponDto.class);

        BigDecimal actualPrice = coupon != null ?
                    productDto.getPrice().subtract(
                            productDto.getPrice().multiply(coupon.getDiscount().divide(BigDecimal.valueOf(100)))
                    )
                    : productDto.getPrice();

        Product savedProduct =
                ProductConverter.dtoToProduct(coupon != null ? productDto.withPrice(actualPrice) : productDto);

        return ProductConverter.productToDto(productService.saveProduct(savedProduct));
    }

}
