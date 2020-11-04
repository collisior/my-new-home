package net.camillasatte.mynewhome.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.camillasatte.mynewhome.facade.IAuthenticationFacade;
import net.camillasatte.mynewhome.model.*;
import net.camillasatte.mynewhome.repository.RoomRepository;
import net.camillasatte.mynewhome.web.dto.RoomDTO;

@Service
@Transactional
public class RoomServiceImplementation implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired 
	private UserService userService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;
    
	public RoomServiceImplementation(RoomRepository roomRepository) {
    	this.roomRepository = roomRepository;
    }
    

	public Optional<Room> findById(Long id) {
		return roomRepository.findById(id);
	}

	public Room save(RoomDTO registration) {
		Room room = new Room();
		room.setName(registration.getName());
		room.setDetails(registration.getDetails());
		User user = userService.findByEmail(currentUserEmail());
		room.setUser(user);
		room.setUserId(user.getId());
		try {
			saveRoomImage(room, registration.getImage());
			System.out.println("Room image saved.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Room's image couldn't saved.");
			e.printStackTrace();
		}
		
		return roomRepository.save(room);
	}
	
	public String delete(Room room) {
		roomRepository.delete(room);
		return "Successfully deleted the room";
	}
	
	public void saveRoomImage(Room room, MultipartFile file) throws IOException {
		room.setImage(file.getBytes());
	}
	
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserEmail() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }
    
    @Override
	public List<Room> findAll() {
		List<Room> rooms = new ArrayList<Room>();
		User user = userService.findByEmail(currentUserEmail());
		for(Room r: user.getRooms()) {
			rooms.add(r);	
		}
		return rooms;
	}
}
