package co.guiromao.spring.productservice.controller;

import co.guiromao.spring.productservice.converter.ProductConverter;
import co.guiromao.spring.productservice.dto.SimpleProductDto;
import co.guiromao.spring.productservice.model.Product;
import co.guiromao.spring.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/showCreateProduct")
    public String createProduct() {
        return "createProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(SimpleProductDto dto) {
        Product product = ProductConverter.simpleDtoToProduct(dto);
        System.out.println("Product: " + product);
        productService.saveProduct(product);

        return "createResponse";
    }

    @GetMapping("/showGetProduct")
    public String showGetProduct() {
        return "getProduct";
    }

    @PostMapping("/getProduct")
    public ModelAndView getProduct(String productName) {
        ModelAndView modelAndView = new ModelAndView("productDetails");
        Product product = productService.getProductByName(productName);
        modelAndView.addObject(product);

        return modelAndView;
    }

}
