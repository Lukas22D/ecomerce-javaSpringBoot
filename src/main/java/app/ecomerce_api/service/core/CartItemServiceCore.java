package app.ecomerce_api.service.core;

import java.util.concurrent.CompletableFuture;

import org.springframework.cache.annotation.CachePut;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.ecomerce_api.service.CartItemService;
import jakarta.transaction.Transactional;
import app.ecomerce_api.model.Cart;
import app.ecomerce_api.model.CartItem;
import app.ecomerce_api.dto_global.CartItemRequest;
import app.ecomerce_api.repository.CartItemRepository;
import app.ecomerce_api.model.Item;


@Service
public class CartItemServiceCore implements CartItemService {

    private final CartServiceCore cartServiceCore;
    private final CartItemRepository cartItemRepository;
    private final ItemServiceCore itemServiceCore;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(CartItemServiceCore.class);

    public CartItemServiceCore(
            CartServiceCore cartServiceCore,
            CartItemRepository cartItemRepository,
            ItemServiceCore itemServiceCore,
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {
        this.cartServiceCore = cartServiceCore;
        this.itemServiceCore = itemServiceCore;
        this.cartItemRepository = cartItemRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    @CachePut(value = "cart", key = "#reqItemCart.userId()")
    @Transactional
    @Async
    public CompletableFuture<Cart> addItemToCart(CartItemRequest reqItemCart) {
        // Buscar o carrinho do usuário
        var cart = cartServiceCore.getCartById(reqItemCart.userId());
        // Buscar o item a ser adicionado
        var item = itemServiceCore.getItemById(reqItemCart.productId());
        // Verificar se a quantidade disponível é suficiente
        if (item.getQuantidadeDisponivel() < reqItemCart.quantity()) {
            throw new RuntimeException("Quantidade indisponível");
        }
        // Atualizar a quantidade disponível
        item.setQuantidadeDisponivel(item.getQuantidadeDisponivel() - reqItemCart.quantity());
        // Enviar mensagem para o tópico de estoque atualizar a quantidade disponível
        sendToKafka(item);
        // Criar um novo CartItem e definir os relacionamentos
        CartItem cartItem = new CartItem(cart, item, reqItemCart.quantity());
        cartItem = cartItemRepository.save(cartItem);
        cart.addItem(cartItem);
        Cart cartSaved = cartServiceCore.saveCart(cart);

        return CompletableFuture.completedFuture(cartSaved);
    }

    private void sendToKafka(Item item) {
        try {
            var itemMapped = objectMapper.writeValueAsString(item);
            kafkaTemplate.send("estoque.request.topic", itemMapped);
        } catch (Exception e) {
            LOGGER.error("Erro ao enviar mensagem para o Kafka", e);
        }
    }

}
