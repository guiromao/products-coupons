package co.guiromao.spring.productservice.service;

import co.guiromao.spring.productservice.model.Product;
import co.guiromao.spring.productservice.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository repo) {
        productRepository = repo;
    }

    public Product saveProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

}
