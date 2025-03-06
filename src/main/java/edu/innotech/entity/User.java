package edu.innotech.entity;

import com.fasterxml.jackson.annotation.JsonView;
import edu.innotech.repository.ViewProduct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @JsonView(ViewProduct.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonView(ViewProduct.Public.class)
    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private Set<Product> products;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
