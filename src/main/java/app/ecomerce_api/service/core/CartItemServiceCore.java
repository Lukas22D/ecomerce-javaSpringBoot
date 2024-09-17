package app.ecomerce_api.service.core;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import app.ecomerce_api.service.CartItemService;
import app.ecomerce_api.model.CartItem;
import app.ecomerce_api.dto_global.CartItemRequest;
import app.ecomerce_api.model.Cart;
import app.ecomerce_api.repository.CartItemRepository;
import app.ecomerce_api.repository.ItemRepository;
import app.ecomerce_api.repository.UserRepository;

@Service
public class CartItemServiceCore implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Cart addItemToCart(CartItemRequest reqItemCart) {
        var user = userRepository.findById(reqItemCart.getUserId()).orElseThrow( () -> new EntityNotFoundException("User not found"));
        var cart = user.getShoppingCart();
        var item = itemRepository.findById(reqItemCart.getProductId()).orElseThrow( () -> new EntityNotFoundException("Item not found"));
        Hibernate.initialize(cart.getCartItems());
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setQuantidadeSelecionada(reqItemCart.getQuantity());
        cartItemRepository.save(cartItem);
        return cart;
        
    }
    
}
