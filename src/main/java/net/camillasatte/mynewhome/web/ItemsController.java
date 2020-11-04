package net.camillasatte.mynewhome.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import net.camillasatte.mynewhome.ImageHandler;
import net.camillasatte.mynewhome.model.*;
import net.camillasatte.mynewhome.repository.ItemRepository;
import net.camillasatte.mynewhome.service.ItemService;

@Controller
@RequestMapping("/items")
public class ItemsController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	ItemRepository itemRepository;

	@GetMapping
	public String showAll(Model model) {
		Optional<Item> defaultItem = itemService.findById(0L);
		model.addAttribute("items", itemService.findAll());
		ImageHandler imageHandler = new ImageHandler();
		model.addAttribute("imageHandler",imageHandler);
		model.addAttribute("imageDefault", imageHandler.getImgData(defaultItem.get().getImage()));
		return "items";
	}

	@GetMapping("/{id}")
	public ModelAndView getItemDetails(@PathVariable("id") long id, Model model) {
		ImageHandler imageHandler = new ImageHandler();
		Optional<Item> item = itemService.findById(id);
		Optional<Item> defaultItem = itemService.findById(0L);
		
		ModelAndView modelAndView = new ModelAndView("itemDetails");
		modelAndView.addObject("imageHandler", new ImageHandler());
		modelAndView.addObject("item", item.get());
		modelAndView.addObject("name", item.get().getName());
		modelAndView.addObject("imageDefault", imageHandler.getImgData(defaultItem.get().getImage()));
		if (item.get().getImage() != null) {
			modelAndView.addObject("image",  imageHandler.getImgData(item.get().getImage()));
		}
		
		if (item.get().getDetails().isEmpty()) {
			modelAndView.addObject("details", "No details provided..");
		} else {
			modelAndView.addObject("details", item.get().getDetails());
		}
		
		modelAndView.addObject("price", item.get().getPrice());
		if (item.get().getLink().isEmpty()) {
			modelAndView.addObject("link", "No link found.");
		} else {
			modelAndView.addObject("link", item.get().getLink());
		}
		model.addAttribute("item", item.get());
		return modelAndView;
	}
	
    
	@GetMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable("id") long id) {
		Optional<Item> item = itemService.findById(id);
		itemService.delete(item.get());
	    return "items";
	}

	@GetMapping("/editItem/{id}")
	public String editItemPage(@PathVariable("id") long id, Model model) {
		Item item = itemService.findById(id).get();
		if(item.getRoomId() != null) {
			System.out.println("Item room is not null id " + item.getRoomId());
		}
		List<String> statusList = Status.getStatusList();
		model.addAttribute("statusList", statusList);
		model.addAttribute("itemObject", item);
		return "itemEdit";
	}
	

	@PostMapping("/saveEditItem")
	public String updateContact(Model model, @ModelAttribute("itemObject") Item itemObject,
			@RequestParam(value = "statusSelected", required = false) String status) {
		System.out.println("Item itemObject " + itemObject.getName());
		Item item = itemService.findById(itemObject.getId()).get();
		
		item.setName(itemObject.getName());
		item.setPrice(itemObject.getPrice());
		item.setLink(itemObject.getLink());
		
		itemService.saveEdit(item, itemObject);
		
		return "redirect:/items/"+item.getId();
	}
	

}