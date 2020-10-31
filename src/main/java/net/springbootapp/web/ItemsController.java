package net.springbootapp.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import net.springbootapp.ImageHandler;
import net.springbootapp.model.Item;
import net.springbootapp.model.Room;
import net.springbootapp.repository.ItemRepository;
import net.springbootapp.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	ItemRepository itemRepository;

	@GetMapping
	public String showAll(Model model) {
		model.addAttribute("items", itemService.findAll());
		model.addAttribute("ImageHandler", new ImageHandler());
		return "items";
	}

	@GetMapping("/{id}")
	public ModelAndView getItemDetails(@PathVariable("id") long id, Model model) {
		Optional<Item> item = itemService.findById(id);
		Optional<Item> defaultItem = itemService.findById(0L);
		ModelAndView modelAndView = new ModelAndView("itemDetails");
		modelAndView.addObject("name", item.get().getName());
		
		if (item.get().getDetails().isEmpty()) {
			modelAndView.addObject("details", "No details found.");
		} else {
			modelAndView.addObject("details", item.get().getDetails());
		}
		
		modelAndView.addObject("price", item.get().getPrice());
		if (item.get().getLink().isEmpty()) {
			modelAndView.addObject("link", "No link found.");
		} else {
			modelAndView.addObject("link", item.get().getLink());
		}
		if (item.get().getImage()==null) {
			modelAndView.addObject("image", ImageHandler.getImgData(defaultItem.get().getImage()));
		} else {
			modelAndView.addObject("image", ImageHandler.getImgData(item.get().getImage()));
		}
		model.addAttribute("item", item);
		return modelAndView;
	}
	
//	@PostMapping("itemEdit/{id}")
//	public ModelAndView showEditForm(@PathVariable("id") long id, Model model) {
//		return modelAndView;
//	}
    
	@GetMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable("id") long id) {
		System.out.println("Item DELETING " + id);
		Optional<Item> item = itemService.findById(id);
		itemService.delete(item.get());
	    return "items";
	}


}