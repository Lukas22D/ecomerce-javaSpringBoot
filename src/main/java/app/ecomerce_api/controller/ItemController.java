package app.ecomerce_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import app.ecomerce_api.Config.View;
import app.ecomerce_api.controller.dto_controller.Response200;
import app.ecomerce_api.model.Item;
import app.ecomerce_api.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    @JsonView(View.ItemView.class)
    public ResponseEntity<Response200> findAllItems() {
        return new ResponseEntity<>(new Response200().setResponse200("Items found", HttpStatus.OK.value(), itemService.getAllItems()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(View.ItemView.class)
    public ResponseEntity<Response200> findItemById(Long id) {
        return new ResponseEntity<>(new Response200().setResponse200("Item found", HttpStatus.OK.value(), itemService.getItemById(id)), HttpStatus.OK);
    }    

    @PostMapping("/register")
    @JsonView(View.ItemView.class)
    public ResponseEntity<Response200> registerItem(Item item) {
        return new ResponseEntity<>(new Response200().setResponse200("Item registered", HttpStatus.OK.value(), itemService.saveItem(item)), HttpStatus.OK);
    }

    @PutMapping("/update")
    @JsonView(View.ItemView.class)
    public ResponseEntity<Response200> updateItem(Item item) {
        return new ResponseEntity<>(new Response200().setResponse200("Item updated", HttpStatus.OK.value(), itemService.updateItem(item)), HttpStatus.OK);
    }
}
