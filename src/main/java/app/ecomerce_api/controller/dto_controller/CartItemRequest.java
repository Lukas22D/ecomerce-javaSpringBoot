package app.ecomerce_api.controller.dto_controller;


public record CartItemRequest(Long productId, Integer quantity, Long userId) {}
