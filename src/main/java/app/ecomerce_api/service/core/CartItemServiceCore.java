package app.ecomerce_api.service.core;

import java.util.concurrent.CompletableFuture;

import org.springframework.cache.annotation.CachePut;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.ecomerce_api.service.CartItemService;
import app.ecomerce_api.controller.dto_controller.CartItemRequest;
import app.ecomerce_api.model.Cart;
import app.ecomerce_api.model.CartItem;
import app.ecomerce_api.model.Item;
import app.ecomerce_api.repository.CartItemRepository;

@Service
public class CartItemServiceCore implements CartItemService {

    private final CartServiceCore cartServiceCore;
    private final CartItemRepository cartItemRepository;
    private final ItemServiceCore itemServiceCore;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public CartItemServiceCore(
            CartServiceCore cartServiceCore,
            CartItemRepository cartItemRepository,
            ItemServiceCore itemServiceCore,
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper,
            RestTemplate restTemplate) {
        this.cartServiceCore = cartServiceCore;
        this.itemServiceCore = itemServiceCore;
        this.cartItemRepository = cartItemRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
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
        producerKafka(item);
        // Criar um novo CartItem e definir os relacionamentos
        CartItem cartItem = new CartItem(cart, item, reqItemCart.quantity());
        cartItem = cartItemRepository.save(cartItem);
        cart.addItem(cartItem);
        Cart cartSaved = cartServiceCore.saveCart(cart);
        enviarCallback(cartSaved);

        return CompletableFuture.completedFuture(cartSaved);
    }

    public void producerKafka(Item message) {
        try {
            var itemMapped = objectMapper.writeValueAsString(message);
            kafkaTemplate.send("estoque.request.topic", itemMapped);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarCallback(Cart cart) {
        String callbackUrl = "https://webhook.site/d757543e-ede8-4076-a732-e3cf5b4bec37"; // URL de exemplo
        try {
            restTemplate.postForEntity(callbackUrl, cart, String.class);
        } catch (Exception e) {
            System.out.println("Erro ao enviar callback para a empresa: " + e.getMessage());
        }
    }

}
