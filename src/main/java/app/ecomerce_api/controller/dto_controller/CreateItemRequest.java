package app.ecomerce_api.controller.dto_controller;

public record CreateItemRequest(
    String name,
    String description,
    Double price,
    Integer quantity,
    Long customerId
) {
    
}
