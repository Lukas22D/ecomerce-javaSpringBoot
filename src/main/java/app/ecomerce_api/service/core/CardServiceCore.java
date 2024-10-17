package app.ecomerce_api.service.core;

import org.springframework.stereotype.Service;

import app.ecomerce_api.controller.dto_controller.NewCardRequest;
import app.ecomerce_api.model.Card;
import app.ecomerce_api.repository.CardRepository;
import app.ecomerce_api.service.CardService;
import app.ecomerce_api.service.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CardServiceCore implements CardService{

    private final CardRepository cardRepository;

    private final UserService userService;

    @Override
    public Card createCard(NewCardRequest newCardRequest) {
        var user = userService.getUserById(newCardRequest.userId());
        var card = new Card(newCardRequest.paymentMethod(), newCardRequest.cardNumber(), newCardRequest.cardHolder(), newCardRequest.expirationDate(), newCardRequest.securityCode());
        user.addCard(card);
        return cardRepository.save(card);
    }

    @Override
    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
        cardRepository.delete(card);
    }


    
}
