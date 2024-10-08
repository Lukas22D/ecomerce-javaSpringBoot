package app.ecomerce_api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.ecomerce_api.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    
    Optional<Cart> findByUserId(@Param("userId") Long userId);

    
} 