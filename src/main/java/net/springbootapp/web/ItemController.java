package net.springbootapp.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/newItem")
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
		List<String> statusList = Status.getStatusList();
		model.addAttribute("statusList", statusList);
		return "newItem";
	}

	@PostMapping
	public String createNewItem( @ModelAttribute("item") @Valid ItemDTO itemDTO, 
			@RequestParam(value = "roomSelected", required = false) Room room, 
			@RequestParam(value = "statusSelected", required = false) String status, 
			@RequestParam("image") MultipartFile file, BindingResult result) {
		System.out.println("creating new item");
		if (result.hasErrors()) {
			return "newItem";
		}
		try {
			System.out.println("TRY creating new item");
			itemService.save(itemDTO);
			System.out.println("Exit TRY creating new item");
		} catch ( Exception e ){
			System.out.println("ERROR CATCH creating new item");
			System.out.println("message = "+ e.getMessage());
		}
		
		return "redirect:/newItem?success";
	}
}
