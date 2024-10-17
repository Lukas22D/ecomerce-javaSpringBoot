package app.ecomerce_api.service;

import app.ecomerce_api.controller.dto_controller.NewCardRequest;
import app.ecomerce_api.model.Card;

public interface CardService {


    public Card createCard(NewCardRequest newCardRequest);

    public void deleteCard(Long id);
    
};