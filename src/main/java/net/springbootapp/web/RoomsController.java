package net.springbootapp.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.springbootapp.ImageHandler;
import net.springbootapp.model.Item;
import net.springbootapp.model.Room;
import net.springbootapp.service.ItemService;
import net.springbootapp.service.RoomService;
import net.springbootapp.web.dto.ItemDTO;
import net.springbootapp.web.dto.RoomDTO;

@Controller
@RequestMapping("/rooms")
public class RoomsController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private ItemService itemService;

	@GetMapping
	public String showAll(Model model) {
		model.addAttribute("rooms", roomService.findAll());
		ImageHandler imageHandler = new ImageHandler();
		Optional<Room> defaultRoom = roomService.findById(0L);
		model.addAttribute("imageHandler",imageHandler);
		model.addAttribute("imageDefault", imageHandler.getImgData(defaultRoom.get().getImage()));
		return "rooms";
	}

	@GetMapping("/{id}")
	public ModelAndView getRoomDetails(@PathVariable("id") long id, Model model) {
		ImageHandler imageHandler = new ImageHandler();
		Optional<Room> room = roomService.findById(id);
		Optional<Room> defaultRoom = roomService.findById(0L);
		ModelAndView modelAndView = new ModelAndView("roomDetails");
		modelAndView.addObject("imageHandler", new ImageHandler());
		modelAndView.addObject("imageDefault", imageHandler.getImgData(defaultRoom.get().getImage()));
		modelAndView.addObject("room", room.get());

		modelAndView.addObject("name", room.get().getName());
		if (room.get().getDetails() == null) {
			modelAndView.addObject("details", "No details provided.");
		} else {
			modelAndView.addObject("details", room.get().getDetails());
		}
		modelAndView.addObject("items", room.get().getItems());
		model.addAttribute("room", room.get());
		return modelAndView;
	}

	@ModelAttribute("room")
	public RoomDTO roomRegistrationDto() {
		return new RoomDTO();
	}

	@GetMapping("/deleteRoom/{id}")
	public String deleteRoom(@PathVariable Long id) {
		System.out.println("ROOM DELETING " + id);
		roomService.delete(roomService.findById(id).get());
		return "redirect:/rooms";
	}

	@GetMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable("id") long id) {
		Optional<Item> item = itemService.findById(id);
		itemService.delete(item.get());
		return "redirect:/rooms/" + item.get().getRoomId();
	}

	@PostMapping("/saveItem")
	public String saveItem(@ModelAttribute Item itemObject) {
		System.out.println("Item itemitem " + itemObject.getName());
		System.out.println("Item itemitem price " + itemObject.getPrice());
		System.out.println("Item itemitem " + itemObject.getId());
		return "rooms";
	}

	@GetMapping("/editItem/{id}")
	public String editItemPage(@PathVariable("id") long id, Model model) {
		Item item = itemService.findById(id).get();
		if(item.getRoomId() != null) {
			System.out.println("Item room is not null id " + item.getRoomId());
		}
		model.addAttribute("itemObject", item);
		return "itemEdit";
	}

	@PostMapping("/saveEditItem")
	public String updateContact(Model model, @ModelAttribute("itemObject") Item itemObject) {
		System.out.println("Item itemObject " + itemObject.getName());
		Item item = itemService.findById(itemObject.getId()).get();
		
		item.setName(itemObject.getName());
		item.setPrice(itemObject.getPrice());
		item.setLink(itemObject.getLink());
		
		itemService.saveEdit(item, itemObject);
		
		return "redirect:/rooms/"+item.getRoomId();
	}
}