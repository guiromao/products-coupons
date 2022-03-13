package co.guiromao.spring.productservice.converter;

import co.guiromao.spring.productservice.dto.ProductDto;
import co.guiromao.spring.productservice.model.Product;

public class ProductConverter {

    private ProductConverter() {}

    public static Product dtoToProduct(ProductDto dto) {
        return new Product(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getCouponCode());
    }

    public static ProductDto productToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(),
                product.getDescription(), product.getPrice(), product.getCouponCode());
    }

}
