package net.camillasatte.mynewhome.service;

import java.util.List;
import java.util.Optional;

import net.camillasatte.mynewhome.model.*;
import net.camillasatte.mynewhome.web.dto.RoomDTO;

public interface RoomService {
	
	Optional<Room> findById(Long id);
	Room save(RoomDTO registration);
	String delete(Room room);
	List<Room> findAll();

}
