package app.ecomerce_api.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.ecomerce_api.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
    
}
