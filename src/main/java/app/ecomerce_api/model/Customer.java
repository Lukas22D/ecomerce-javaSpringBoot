package app.ecomerce_api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Item> items = new ArrayList<>();

    private BigDecimal balance = BigDecimal.ZERO;

    public Customer() {
    }	


    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
}
