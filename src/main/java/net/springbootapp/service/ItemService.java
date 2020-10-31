package net.springbootapp.service;

import java.util.List;
import java.util.Optional;

import net.springbootapp.model.Item;
import net.springbootapp.web.dto.ItemDTO;

public interface ItemService {
	Optional<Item> findById(Long id);
	Item save(ItemDTO registration);
	void saveEdit(Item originalItem, Item editedItem);
	String delete(Item item);
	List<Item> findAll();
}
