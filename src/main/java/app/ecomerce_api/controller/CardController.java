package app.ecomerce_api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.ecomerce_api.controller.dto_controller.NewCardRequest;
import app.ecomerce_api.model.Card;
import app.ecomerce_api.service.CardService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/create")
    public Card createCard(@RequestBody NewCardRequest newCardRequest) {
        return cardService.createCard(newCardRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
    
    
}
