package app.ecomerce_api.service.core;

import java.util.concurrent.CompletableFuture;

import org.springframework.cache.annotation.CachePut;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.ecomerce_api.service.CartItemService;
import app.ecomerce_api.model.Cart;
import app.ecomerce_api.model.CartItem;
import app.ecomerce_api.dto_global.CartItemRequest;
import app.ecomerce_api.repository.CartItemRepository;


@Service
public class CartItemServiceCore implements CartItemService {

    private final CartServiceCore cartServiceCore;
    private final CartItemRepository cartItemRepository;
    private final ItemServiceCore itemServiceCore;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CartItemServiceCore(
            CartServiceCore cartServiceCore,
            CartItemRepository cartItemRepository,
            ItemServiceCore itemServiceCore,
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper
      ) {
        this.cartServiceCore = cartServiceCore;
        this.itemServiceCore = itemServiceCore;
        this.cartItemRepository = cartItemRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    @Async
    @CachePut(value = "cart", key = "#reqItemCart.userId()")
    public CompletableFuture<Cart> addItemToCart(CartItemRequest reqItemCart) {
        // Buscar o carrinho do usuário
        var cart = cartServiceCore.getCartById(reqItemCart.userId());
        // Buscar o item a ser adicionado
        var item = itemServiceCore.getItemById(reqItemCart.productId());
        item.setQuantidadeDisponivel(item.getQuantidadeDisponivel() - reqItemCart.quantity());
        try{
        var itemMapped = objectMapper.writeValueAsString(item); 
        kafkaTemplate.send("estoque.request.topic", itemMapped);
        }catch(Exception e){
            e.printStackTrace();
        }
        // Criar um novo CartItem e definir os relacionamentos
        CartItem cartItem = new CartItem(cart, item, reqItemCart.quantity());
        cartItem = cartItemRepository.save(cartItem);
        cart.addItem(cartItem);
        Cart cartSaved = cartServiceCore.saveCart(cart);


        return CompletableFuture.completedFuture(cartSaved);
    }

}
