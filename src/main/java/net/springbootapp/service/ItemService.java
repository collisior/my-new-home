package net.springbootapp.service;

import java.util.List;
import java.util.Optional;

import net.springbootapp.model.Item;
import net.springbootapp.web.dto.ItemDTO;

public interface ItemService {
	Optional<Item> findById(Long id);
	Item save(ItemDTO registration);
	String delete(Item item);
	List<Item> findAll();
}
