package edu.innotech.entity;

import com.fasterxml.jackson.annotation.JsonView;
import edu.innotech.repository.ViewProduct;
import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedEntityGraph(
        name = "Product.with-product-type",
        attributeNodes = {
                @NamedAttributeNode("productTypeId"),
                @NamedAttributeNode("userId")
        }
)

@Getter
@Setter
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @JsonView(ViewProduct.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonView(ViewProduct.Public.class)
    @Column(name = "account")
    private String account;

    @JsonView(ViewProduct.Public.class)
    @Column(name = "amount")
    private Double amount;

    @JsonView(ViewProduct.Public.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type_id")
    private ProductType productTypeId;

    @JsonView(ViewProduct.Public.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", amount=" + amount +
                ", productTypeId=" + productTypeId.toString() +
                ", userId=" + userId.toString() +
                '}';
    }
}
