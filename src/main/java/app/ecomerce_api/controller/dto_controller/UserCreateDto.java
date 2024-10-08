package app.ecomerce_api.controller.dto_controller;

public record UserCreateDto(
    String name,
    String login,
    String password
) {
    
}
