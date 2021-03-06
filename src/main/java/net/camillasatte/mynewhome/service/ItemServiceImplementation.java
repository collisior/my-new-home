package net.camillasatte.mynewhome.service;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import net.camillasatte.mynewhome.ImageHandler;
import net.camillasatte.mynewhome.facade.IAuthenticationFacade;
import net.camillasatte.mynewhome.model.Item;
import net.camillasatte.mynewhome.model.Room;
import net.camillasatte.mynewhome.model.User;
import net.camillasatte.mynewhome.repository.ItemRepository;
import net.camillasatte.mynewhome.web.dto.ItemDTO;

@Service
@Transactional
public class ItemServiceImplementation implements ItemService {

	@Autowired
    private ItemRepository itemRepository;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private RoomService roomService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;
    
	public ItemServiceImplementation(ItemRepository itemRepository) {
    	this.itemRepository = itemRepository;
    }

	public void saveEdit(Item originalItem, Item editedItem) {
		
		originalItem.setName(editedItem.getName());
		originalItem.setPrice(editedItem.getPrice());
		originalItem.setLink(editedItem.getLink());
		originalItem.setStatus(editedItem.getStatus());
		
		itemRepository.save(originalItem);
	}
	
	public Item save(ItemDTO registration) {
		ImageHandler imageHandler = new ImageHandler();
		Item item = new Item();
		User user = userService.findByEmail(currentUserEmail());
//		Room defaultRoom = new Room();
		for(Room r: user.getRooms()) {
//			defaultRoom = r;
			for(Item i: r.getItems()) {
				System.out.println("In " + r + " item = " + i);
			}	
		}
		item.setUser(user);
		item.setUserId(user.getId());
		item.setName(registration.getName());
        item.setDetails(registration.getDetails());
        item.setLink(registration.getLink());
        item.setPrice(registration.getPrice());
        item.setStatus(registration.getStatusSelected());
        item.setRoom(registration.getRoomSelected());
        item.setRoomId(registration.getRoomSelected().getId());
        try {
        	imageHandler.saveItemImage(item, registration.getImage());
			System.out.println("Item's image saved.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Item's image couldn't saved.");
			e.printStackTrace();
		}
        return itemRepository.save(item);
	}

	public String delete(Item item) {
		itemRepository.delete(item);
		return "Successfully deleted the item";
	}

	public Optional<Item> findById(Long id) {
		return itemRepository.findById(id);
	}
	
	
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserEmail() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return authentication.getName();
    }

	@Override
	public List<Item> findAll() {
		List<Item> items = new ArrayList<Item>();
		User user = userService.findByEmail(currentUserEmail());
		for(Room r: user.getRooms()) {
			for(Item i: r.getItems()) {
				items.add(i);
			}	
		}
		return items;
	}

}
