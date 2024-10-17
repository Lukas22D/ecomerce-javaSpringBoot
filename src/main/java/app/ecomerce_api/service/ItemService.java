package app.ecomerce_api.service;

import app.ecomerce_api.controller.dto_controller.CreateItemRequest;
import app.ecomerce_api.model.Item;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ItemService {

    public CompletableFuture<Item> saveItem(CreateItemRequest item);

    public List<Item> getAllItems();

    public Item updateItem(Item item);

    public Item deleteItemById(Long id);

    public Item getItemById(Long id);
    
    
}
