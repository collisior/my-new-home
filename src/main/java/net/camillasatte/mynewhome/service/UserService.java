package net.camillasatte.mynewhome.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.camillasatte.mynewhome.model.Item;
import net.camillasatte.mynewhome.model.Room;
import net.camillasatte.mynewhome.model.User;
import net.camillasatte.mynewhome.web.dto.UserRegistrationDTO;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User save(UserRegistrationDTO registration);
    List<Item> findAllItems();
    List<Room> findAllRooms();
}