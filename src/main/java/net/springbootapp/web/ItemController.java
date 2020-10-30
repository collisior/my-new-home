package net.springbootapp.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import net.springbootapp.model.*;
import net.springbootapp.service.ItemServiceImplementation;
import net.springbootapp.service.RoomService;
import net.springbootapp.service.UserService;
import net.springbootapp.web.dto.ItemDTO;

@Controller
@RequestMapping("/newitem")
public class ItemController {

	@Autowired
	private ItemServiceImplementation itemService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private UserService userService;

	@ModelAttribute("item")
	public ItemDTO itemRegistrationDto() {
		return new ItemDTO();
	}

	@GetMapping
	public String showNewItemForm(Model model) {
		List<Room> roomsList = userService.findAllRooms();
		model.addAttribute("roomsList", roomsList);
//		Long roomId = 0L;
//		model.addAttribute("roomSelected", roomId);
		return "newitem";
	}

	@PostMapping
	public String createNewItem( @ModelAttribute("item") @Valid ItemDTO itemDTO, @RequestParam(value = "roomSelected", required = false) Room room, @RequestParam("image") MultipartFile file, BindingResult result) {
		
		if (result.hasErrors()) {
			System.out.println("ERROR");
			return "newitem";
		}
		
		try {
			itemService.save(itemDTO);
		} catch ( Exception e ){
			System.out.println("message = "+ e.getMessage());
		}
		
		return "redirect:/newitem?success";
	}
}
