package app.ecomerce_api.service;

import app.ecomerce_api.model.Cart;

import java.util.List;

public interface CartService {

    public Cart getCartById(Long id);

    public List<Cart> getAllCarts(); 
    
}
