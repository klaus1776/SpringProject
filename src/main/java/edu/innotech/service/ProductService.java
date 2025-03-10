package edu.innotech.service;

import edu.innotech.dto.ProductDto;
import edu.innotech.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductDto findProductById(Long id) {
        ProductDto product =  productRepository.findProductById(id).
                orElseThrow(EntityNotFoundException::new);
        return product;
    }

    public ProductDto findProductByAccount(String account) {
        ProductDto product =  productRepository.findProductByAccount(account).
                orElseThrow(EntityNotFoundException::new);
        return product;
    }

    public List<ProductDto> findProductsByUserId(Long userId) {
        List<ProductDto> products = productRepository.findProductsByUserId(userId).
                orElseThrow(EntityNotFoundException::new);
        return products;
    }
}
