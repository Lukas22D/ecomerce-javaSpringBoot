package app.ecomerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.ecomerce_api.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    
} 