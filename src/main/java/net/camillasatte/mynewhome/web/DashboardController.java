package net.camillasatte.mynewhome.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.camillasatte.mynewhome.facade.IAuthenticationFacade;
import net.camillasatte.mynewhome.model.*;
import net.camillasatte.mynewhome.service.ItemService;
import net.camillasatte.mynewhome.service.RoomService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private ItemService itemService;
	
	
	@GetMapping
	public String showAll(Model model) {
		List<Item> items = itemService.findAll();
		List<Room> rooms = roomService.findAll();
		List<String> statusList = Status.getStatusList();
		
		double total = 0;
		double totalWishlist = 0;
		double totalPurchased = 0;
		double totalOther = 0;
		for(Item i: items) {
			total += i.getPrice();
			if(i.getStatus().equals("Wishlist")) {
				totalWishlist+=i.getPrice();
			} else if (i.getStatus().equals("Purchased")) {
				totalPurchased+=i.getPrice();
			} else {
				totalOther+=i.getPrice();
			}
		}
		model.addAttribute("rooms", rooms);
		model.addAttribute("roomsNumber", rooms.size());
		model.addAttribute("items", items);
		model.addAttribute("itemsNumber", items.size());
		model.addAttribute("statusList", statusList);
		model.addAttribute("total", total);
		model.addAttribute("totalWishlist", totalWishlist);
		model.addAttribute("totalPurchased", totalPurchased);
		model.addAttribute("totalOther", totalOther);
		
		return "dashboard";
	}

}
