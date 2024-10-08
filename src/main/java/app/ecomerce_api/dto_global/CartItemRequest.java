package app.ecomerce_api.dto_global;


public record CartItemRequest(Long productId, Integer quantity, Long userId) {}
