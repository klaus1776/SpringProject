package edu.innotech.entity;

import com.fasterxml.jackson.annotation.JsonView;
import edu.innotech.repository.ViewProduct;
import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pruduct_types")
@NoArgsConstructor
public class ProductType {
    @JsonView(ViewProduct.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonView(ViewProduct.Public.class)
    @Column(name = "product_type_name")
    private String productTypeName;

    @OneToMany(mappedBy = "productTypeId", fetch = FetchType.EAGER)
    private Set<Product> products;

    @Override
    public String toString() {
        return "PpruductTypes{" +
                "id=" + id +
                ", productTypeName='" + productTypeName + '\'' +
                '}';
    }
}
