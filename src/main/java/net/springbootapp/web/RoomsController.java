package net.springbootapp.web;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import net.springbootapp.model.Room;
import net.springbootapp.service.RoomService;

@Controller
@RequestMapping("/rooms")
public class RoomsController {

	@Autowired
	private RoomService roomService;

	@GetMapping
	public String showAll(Model model) {
		model.addAttribute("rooms", roomService.findAll());
		return "rooms";
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<String> getIssue(@PathVariable("id") long id) {
//		Optional<Room> room = roomService.findById(id);
//		if (!room.isPresent() ) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//		
//		return new ResponseEntity<>(room.get().getName(), HttpStatus.OK);
//	}

	@GetMapping("/{id}")
	public ModelAndView getRoomDetails(@PathVariable("id") long id) {
		Optional<Room> room = roomService.findById(id);
		System.out.println("ROOM NAME: "+room.get().getName());
	    ModelAndView modelAndView = new ModelAndView("rooms-details");
	    modelAndView.addObject("name", room.get().getName());
	    if(room.get().getDetails().isEmpty()) {
	    	modelAndView.addObject("details", "No details found.");
	    } else {
	    	modelAndView.addObject("details", room.get().getDetails());
	    }
	    if(room.get().getImage().length==0) {
	    	modelAndView.addObject("image", "No photo found.");
	    } else {
	    	modelAndView.addObject("image", getImgData(room.get().getImage()));
	    }
	    
	    return modelAndView;
	}
	
	public String getImgData(byte[] byteData) {
        return Base64.getMimeEncoder().encodeToString(byteData);
    }
}