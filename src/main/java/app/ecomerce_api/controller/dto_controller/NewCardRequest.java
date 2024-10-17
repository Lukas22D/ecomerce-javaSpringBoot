package app.ecomerce_api.controller.dto_controller;

public record NewCardRequest(
    String paymentMethod,
    String cardNumber,
    String cardHolder,
    String expirationDate,
    String securityCode,
    Long userId
) {
    
}
