package net.springbootapp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.springbootapp.service.RoomService;
import net.springbootapp.web.dto.RoomDTO;

@Controller
@RequestMapping("/newRoom")
public class RoomController {

    @Autowired
    private RoomService roomService;
    

    @ModelAttribute("room")
    public RoomDTO roomRegistrationDto() {
        return new RoomDTO();
    }

    @GetMapping
    public String showNewRoomForm(Model model) {
        return "newRoom";
    }
    
    @PostMapping
    public String createNewRoom(@ModelAttribute("room") @Valid RoomDTO roomDTO, @RequestParam("image") MultipartFile file,
            BindingResult result){
    	if (result.hasErrors()){
            return "newRoom";
        }
    	roomService.save(roomDTO);
    	return "redirect:/newRoom?success";
    }

}
