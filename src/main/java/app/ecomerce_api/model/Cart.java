package app.ecomerce_api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import app.ecomerce_api.Config.View;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "shopping_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    @JsonView({View.UserWithCart.class, View.CartView.class})
    private Long id;

    @OneToOne(mappedBy = "shoppingCart")
    @JsonView({View.CartView.class})
    private User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    @JsonView({View.UserWithCart.class, View.CartView.class})
    private List<CartItem> cartItems;

    @Column(name = "total")
    @JsonView({ View.UserWithCart.class, View.CartView.class})
    private Double total;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
