package edu.innotech.repository;

import com.fasterxml.jackson.annotation.JsonView;
import edu.innotech.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Запрос продукта по id
    @EntityGraph(value = "Product.with-product-type")
    Optional<Product> findProductById(Long id);

    // Запрос продукта по номеру счета
    @EntityGraph(value = "Product.with-product-type")
    @Query(value = "select p from Product p where p.account = :account")
    Optional<Product> findProductByAccount(@Param("account") String account);

    // Запрос продукта по user_id
    @EntityGraph(value = "Product.with-product-type")
    @Query(value = "select p from Product p where p.userId.id = :userId")
    Optional<List<Product>> findProductsByUserId(@Param("userId") Long userId);

}
