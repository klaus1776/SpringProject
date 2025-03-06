package edu.innotech.service;

import edu.innotech.entity.Product;
import edu.innotech.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService //implements CommandLineRunner
{
    private final ProductRepository productRepository;

    public Product findProductById(Long id) {
        Product product =  productRepository.findProductById(id).
                orElseThrow(EntityNotFoundException::new);
        return product;
    }

    public Product findProductByAccount(String account) {
        Product product =  productRepository.findProductByAccount(account).
                orElseThrow(EntityNotFoundException::new);
        return product;
    }

    public List<Product> findProductsByUserId(Long userId) {
        List<Product> products = productRepository.findProductsByUserId(userId).
                orElseThrow(EntityNotFoundException::new);
        return products;
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        Product productByid = findProductById(37L);
//        log.info("product: {}", productByid);
//
//        Product productByAccount = findProductByAccount("40817810600010000003");
//        log.info("product: {}", productByAccount);
//
//        List<Product> products = findProductsByUserId(102L);
//        products.forEach(record -> log.info("product: {}", record));
//        //products.forEach(record -> log.info("Founded products with user_id: {}", record.getUserId()));
//    }
}
