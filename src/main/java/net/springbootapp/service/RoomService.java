package net.springbootapp.service;

import java.util.List;
import java.util.Optional;

import net.springbootapp.model.*;
import net.springbootapp.web.dto.RoomDTO;

public interface RoomService {
	
	Optional<Room> findById(Long id);
	Room save(RoomDTO registration);
	String delete(Room room);
	List<Room> findAll();

}
