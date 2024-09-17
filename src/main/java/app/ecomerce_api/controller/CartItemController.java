package app.ecomerce_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import app.ecomerce_api.controller.dto_controller.Response200;
import app.ecomerce_api.dto_global.CartItemRequest;
import app.ecomerce_api.service.CartItemService;

@RestController
@RequestMapping("/cart-item")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;
        
    @PostMapping("/add")
    public ResponseEntity<Response200> addItemToCart(@RequestBody CartItemRequest reqItemCart) {
        var cart = cartItemService.addItemToCart(reqItemCart);
        return new ResponseEntity<>(new Response200().setResponse200("Item added to cart", 200, cart), HttpStatus.OK);
    }
    
}
