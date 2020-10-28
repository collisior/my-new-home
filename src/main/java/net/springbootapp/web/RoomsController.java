package net.springbootapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.springbootapp.model.Room;
import net.springbootapp.repository.ItemRepository;
import net.springbootapp.service.RoomService;

@Controller
@RequestMapping("/rooms")
public class RoomsController {

	@Autowired
	private RoomService roomService;

	ItemRepository itemRepository;

	@GetMapping
	public String showAll(Model model) {
		model.addAttribute("rooms", roomService.findAll());
		return "rooms";
	}
	

//	@GetMapping("/images/items/{id}")
//	public void showProductImage(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
//		response.setContentType("image/jpg"); // Or whatever format you wanna use
//		System.out.println("PAthVariable id: " + id);
//
//		Optional<Item> itemOptional = itemService.findById(id);
//		if (itemOptional.isPresent()) {
//			InputStream is = new ByteArrayInputStream(itemOptional.get().getImage());
//			IOUtils.copy(is, response.getOutputStream());
//		}
//		
//	}

}