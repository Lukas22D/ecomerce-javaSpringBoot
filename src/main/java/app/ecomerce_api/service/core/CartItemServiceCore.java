package app.ecomerce_api.service.core;

import java.util.concurrent.CompletableFuture;

import org.springframework.cache.annotation.CachePut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import app.ecomerce_api.service.CartItemService;
import app.ecomerce_api.model.Cart;
import app.ecomerce_api.model.CartItem;
import app.ecomerce_api.dto_global.CartItemRequest;
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
    @Async
    @CachePut(value = "cart", key = "#reqItemCart.userId()")
    public CompletableFuture<Cart> addItemToCart(CartItemRequest reqItemCart) {
        try{
        System.out.println("Thread sleeping");
        Thread.sleep(4000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        // Buscar o carrinho do usuário
        var cart = cartServiceCore.getCartById(reqItemCart.userId());
        // Buscar o item a ser adicionado
        var item = itemServiceCore.getItemById(reqItemCart.productId());
        // Criar um novo CartItem e definir os relacionamentos
        CartItem cartItem = new CartItem(cart, item, reqItemCart.quantity());
        cartItem = cartItemRepository.save(cartItem);
        cart.addItem(cartItem);
        Cart cartSaved = cartServiceCore.saveCart(cart);

        return CompletableFuture.completedFuture(cartSaved);
    }

}
