package app.ecomerce_api.service;

import app.ecomerce_api.model.Item;
import java.util.List;

public interface ItemService {

    public Item saveItem(Item item);

    public List<Item> getAllItems();

    public Item updateItem(Item item);

    public Item deleteItemById(Long id);
    
    
}
