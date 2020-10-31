package net.springbootapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.springbootapp.model.Room;
import net.springbootapp.repository.ItemRepository;
import net.springbootapp.service.RoomService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private RoomService roomService;

	ItemRepository itemRepository;

	@GetMapping
	public String showAll(Model model) {
//		model.addAttribute("dashboard", roomService.findAll());
		return "dashboard";
	}

}