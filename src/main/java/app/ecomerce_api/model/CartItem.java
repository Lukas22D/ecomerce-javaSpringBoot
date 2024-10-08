package app.ecomerce_api.model;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import app.ecomerce_api.config.View;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "cart_item")
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.UserWithCart.class, View.CartView.class})
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonView({View.UserWithCart.class, View.CartView.class, View.ItemView.class})
    private Item item;

    @JsonView({View.UserWithCart.class, View.CartView.class})
    private Integer quantidadeSelecionada;

    public CartItem() {
    }

    public CartItem(Cart Cart, Item item, Integer quantidadeSelecionada) {
        this.cart = Cart;
        this.item = item;
        this.quantidadeSelecionada = quantidadeSelecionada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantidadeSelecionada() {
        return quantidadeSelecionada;
    }

    public void setQuantidadeSelecionada(Integer quantidadeSelecionada) {
        this.quantidadeSelecionada = quantidadeSelecionada;
    }
}
