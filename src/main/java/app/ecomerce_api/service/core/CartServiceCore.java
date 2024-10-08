package app.ecomerce_api.service.core;

import app.ecomerce_api.model.Cart;
import app.ecomerce_api.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import app.ecomerce_api.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
public class CartServiceCore implements CartService {
    
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Cacheable(value = "cart", key = "#id")
    protected Cart getCartByUserId(Long userId) {
        System.out.println("Procurando carrinho no banco de dados");
        return cartRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("Cart not found"));
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
    
}
