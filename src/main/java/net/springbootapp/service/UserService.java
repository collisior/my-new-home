package net.springbootapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.springbootapp.model.Item;
import net.springbootapp.model.Room;
import net.springbootapp.model.User;
import net.springbootapp.web.dto.UserRegistrationDTO;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User save(UserRegistrationDTO registration);
    List<Item> findAllItems();
    List<Room> findAllRooms();
}