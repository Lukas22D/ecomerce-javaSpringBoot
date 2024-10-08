package app.ecomerce_api.service.core;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import app.ecomerce_api.service.CartItemService;
import app.ecomerce_api.model.CartItem;
import app.ecomerce_api.dto_global.CartItemRequest;
import app.ecomerce_api.model.Cart;
import app.ecomerce_api.repository.CartItemRepository;


@Service
public class CartItemServiceCore implements CartItemService {

    final private CartServiceCore cartServiceCore;
    final private CartItemRepository cartItemRepository;
    final private ItemServiceCore itemServiceCore;

    public CartItemServiceCore(
            CartServiceCore cartServiceCore,
            CartItemRepository cartItemRepository,
            ItemServiceCore itemServiceCore
      ) {
        this.cartServiceCore = cartServiceCore;
        this.itemServiceCore = itemServiceCore;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @CachePut(value = "cart", key = "#reqItemCart.userId()")
    public Cart addItemToCart(CartItemRequest reqItemCart) {
        // Buscar o carrinho do usuário
        var cart = cartServiceCore.getCartById(reqItemCart.userId());
        // Buscar o item a ser adicionado
        var item = itemServiceCore.getItemById(reqItemCart.productId());
        // Criar um novo CartItem e definir os relacionamentos
        CartItem cartItem = new CartItem(cart, item, reqItemCart.quantity());
        cartItem = cartItemRepository.save(cartItem);
        cart.addItem(cartItem);
        return cartServiceCore.saveCart(cart);
    }

}
