package app.ecomerce_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.ecomerce_api.model.Cart;
import app.ecomerce_api.model.CartItem;
import app.ecomerce_api.model.Item;
import app.ecomerce_api.model.User;
import app.ecomerce_api.repository.CartRepository;
import app.ecomerce_api.repository.CartItemRepository;
import app.ecomerce_api.repository.ItemRepository;
import app.ecomerce_api.repository.UserRepository;

import org.hibernate.Hibernate;

@SpringBootTest // garante que a transação seja revertida após o teste, mantendo o banco de dados limpo.
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartItemRepository cartItemRepository;  // Novo repositório para CartItem

    @Test
    public void create_user_and_cart() {
        // Criação de um novo usuário
        User user = new User();
        user.setName("Test User");
        user.setLogin("testuser@example.com");
        user.setPassword("password123");

        // Criação de um novo carrinho de compras
        Cart cart = new Cart();

        // Estabelece o relacionamento bidirecional
        user.setShoppingCart(cart); // Associa o carrinho ao usuário

        // Salva o usuário (e automaticamente o carrinho através do cascade)
        userRepository.save(user);
    }

    @Test
    public void add_item() {

        // Criação de um novo item
        Item item2 = new Item();
        item2.setNome("Item 2");
        item2.setPreco(20.0);
        var itemSalvo2 = itemRepository.save(item2);

        // Buscar o usuário com o id 1
        User user = userRepository.findById(1L).get();

        // Obter o carrinho de compras do usuário
        Cart cart = user.getShoppingCart();

        // Forçar a inicialização dos items (se FetchType.LAZY for mantido)
        Hibernate.initialize(cart.getCartItems()); // Isso garante que a lista será inicializada

        // Criar uma nova entrada de CartItem para adicionar o item ao carrinho
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(itemSalvo2);
        cartItem.setQuantidadeSelecionada(2);  // Quantidade selecionada do item

        // Salvar o CartItem
        cartItemRepository.save(cartItem);

        // Salvar as mudanças no carrinho
        cartRepository.save(cart);
    }

    @Test
    public void find_user() {
        // Buscar o usuário pelo id
        User user = userRepository.findById(1L).get();

        // Imprimir informações sobre o usuário e o carrinho
        System.out.println("Usuário: " + user.getName());
        System.out.println("Carrinho: " + user.getShoppingCart().getId());

        // Inicializar e imprimir os itens do carrinho
        Hibernate.initialize(user.getShoppingCart().getCartItems());
        user.getShoppingCart().getCartItems().forEach(cartItem -> {
            System.out.println("Item: " + cartItem.getItem().getNome());
            System.out.println("Quantidade: " + cartItem.getQuantidadeSelecionada());
        });
    }
}
