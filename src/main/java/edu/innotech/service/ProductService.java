package edu.innotech.service;

import edu.innotech.dto.ProductDto;
import edu.innotech.entity.Product;
import edu.innotech.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductDto findProductById(Long id) {
        Product product =  productRepository.findProductById(id).
                orElseThrow(EntityNotFoundException::new);
        return new ProductDto(Collections.singletonList(product));
    }

    public ProductDto findProductByAccount(String account) {
        Product product =  productRepository.findProductByAccount(account).
                orElseThrow(EntityNotFoundException::new);
        return new ProductDto(Collections.singletonList(product));
    }

    public ProductDto findProductsByUserId(Long userId) {
        List<Product> products = productRepository.findProductsByUserId(userId).
                orElseThrow(EntityNotFoundException::new);
        return new ProductDto(products);
    }
}
