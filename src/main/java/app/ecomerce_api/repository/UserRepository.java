package app.ecomerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import app.ecomerce_api.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Procurar por um unico login
    public Optional<User> findByLogin(String login);
    
}