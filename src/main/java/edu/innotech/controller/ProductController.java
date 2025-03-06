package edu.innotech.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.innotech.entity.Product;
import edu.innotech.repository.ViewProduct;
import edu.innotech.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @JsonView(ViewProduct.Public.class)
    @GetMapping(value = "/product")
    public Product findProductById(@RequestParam("id") Long productId) {
        Product product = productService.findProductById(productId);
        return product;
    }

    @JsonView(ViewProduct.Public.class)
    @GetMapping(value = "/account")
    public Product findProductByAccount(@RequestParam("num") String account) {
        return productService.findProductByAccount(account);
    }

    @JsonView(ViewProduct.Public.class)
    @GetMapping(value = "/user")
    public List<Product> findProductsByUserId(@RequestParam("id") Long userId) {
        List<Product> products = productService.findProductsByUserId(userId);
        return products;
    }

}
