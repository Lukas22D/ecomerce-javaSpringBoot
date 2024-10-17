package app.ecomerce_api.service;

import java.util.concurrent.CompletableFuture;

import app.ecomerce_api.dto_global.CartItemRequest;
import app.ecomerce_api.model.Cart;

public interface CartItemService {
    
    public CompletableFuture<Cart> addItemToCart(CartItemRequest cartItemRequest) ;
}
