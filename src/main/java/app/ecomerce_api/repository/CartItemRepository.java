package app.ecomerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.ecomerce_api.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
}
