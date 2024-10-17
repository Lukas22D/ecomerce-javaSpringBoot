package app.ecomerce_api.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.web.bind.annotation.RequestBody;

import app.ecomerce_api.config.View;
import app.ecomerce_api.controller.dto_controller.CartItemRequest;
import app.ecomerce_api.controller.dto_controller.Response200;
import app.ecomerce_api.service.CartItemService;

@RestController
@RequestMapping("/cart-item")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
        
    @PostMapping("/add")
    @JsonView({View.CartView.class})
    public CompletableFuture<ResponseEntity<Response200>> addItemToCart(@RequestBody CartItemRequest reqItemCart) {
        return CompletableFuture.supplyAsync(() -> {
            cartItemService.addItemToCart(reqItemCart);
            return ResponseEntity.ok().body(new Response200().setResponse200("Item added to cart", HttpStatus.OK.value(), "Cart updated"));
        }, executorService);
    }
}
