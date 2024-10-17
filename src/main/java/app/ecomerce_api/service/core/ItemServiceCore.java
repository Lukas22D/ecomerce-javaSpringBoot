package app.ecomerce_api.service.core;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import app.ecomerce_api.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import app.ecomerce_api.repository.ItemRepository;
import app.ecomerce_api.controller.dto_controller.CreateItemRequest;
import app.ecomerce_api.model.Item;
import app.ecomerce_api.service.CustomerService;

@Service
@AllArgsConstructor
public class ItemServiceCore implements ItemService {
    
    private final ItemRepository itemRepository;

    private final CustomerService customerService;

    @Override
    public CompletableFuture<Item> saveItem(CreateItemRequest request) {
        var customer = customerService.findCustomerById(request.customerId());

        Item item = new Item(request.name(), request.description(), request.price(), request.quantity(), customer);
        itemRepository.save(item);
        return CompletableFuture.completedFuture(item);
    }

    @Cacheable(value = "item", key = "#id")
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Item not found"));
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item updateItem(Item item) {
        itemRepository.findById(item.getId()).orElseThrow( () -> new EntityNotFoundException("Item not found"));
        return itemRepository.save(item);
    }

    @Override
    public Item deleteItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Item not found"));
        itemRepository.deleteById(id);
        return item;
    }



}
