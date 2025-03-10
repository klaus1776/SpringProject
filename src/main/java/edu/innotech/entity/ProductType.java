package edu.innotech.entity;

import edu.innotech.dto.ProductTypeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pruduct_types")
@NoArgsConstructor
public class ProductType implements ProductTypeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_type_name")
    private String productTypeName;

    @Override
    public String toString() {
        return "PpruductTypes{" +
                "id=" + id +
                ", productTypeName='" + productTypeName + '\'' +
                '}';
    }
}
