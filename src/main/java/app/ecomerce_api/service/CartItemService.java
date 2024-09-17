package app.ecomerce_api.service;

import app.ecomerce_api.dto_global.CartItemRequest;
import app.ecomerce_api.model.Cart;

public interface CartItemService {
    
    public Cart addItemToCart(CartItemRequest cartItemRequest);
}
