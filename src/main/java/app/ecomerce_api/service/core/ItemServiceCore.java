package app.ecomerce_api.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import app.ecomerce_api.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import app.ecomerce_api.repository.ItemRepository;
import app.ecomerce_api.model.Item;

@Service
public class ItemServiceCore implements ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
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
