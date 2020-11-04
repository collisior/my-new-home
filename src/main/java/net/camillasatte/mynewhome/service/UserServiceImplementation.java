package net.camillasatte.mynewhome.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.camillasatte.mynewhome.facade.IAuthenticationFacade;
import net.camillasatte.mynewhome.model.*;
import net.camillasatte.mynewhome.repository.UserRepository;
import net.camillasatte.mynewhome.web.dto.UserRegistrationDTO;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired 
	private UserService userService;
    
    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }
    
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDTO registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }
    
	@RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public User getCurrentUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return userService.findByEmail(authentication.getName());
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

	@Override
	public List<Item> findAllItems() {
		List<Item> items = new ArrayList<Item>();
		User user = getCurrentUser();
		for(Room r: user.getRooms()) {
			for(Item i: r.getItems()) {
				items.add(i);
			}	
		}
		return items;
	}

	@Override
	public List<Room> findAllRooms() {
		List<Room> rooms = new ArrayList<Room>();
		User user = getCurrentUser();
		for(Room r: user.getRooms()) {
			rooms.add(r);
		}
		return rooms;
	}
	
	
}
