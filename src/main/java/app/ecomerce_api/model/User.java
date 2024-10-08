package app.ecomerce_api.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import app.ecomerce_api.config.View;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;

@Entity(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.UserWithCart.class)
    private Long id;

    @Column(name = "social_name")
    @JsonView(View.UserWithCart.class)
    private String name;

    @Column(name = "login", unique = true)
    @JsonView(View.UserWithCart.class)
    private String login;

    @Column(name = "password", length = 100)
    @JsonIgnore
    private String password;

    // Relacionamento OneToOne com Cart
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "cart_id")
    private Cart shoppingCart;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.shoppingCart = new Cart();
    }

    public User() {
        this.shoppingCart = new Cart();
    }

    @PrePersist
    public void prePersist() {
        this.shoppingCart.setUser(this);
    }

    public Cart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Cart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}