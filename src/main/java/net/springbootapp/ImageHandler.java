package net.springbootapp;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import net.springbootapp.model.Item;
import net.springbootapp.model.Room;
import net.springbootapp.service.ItemService;

public class ImageHandler {

	public boolean isEmpty(byte[] imageData) {
		if (imageData.length == 0) {
			return true;
		}
		return false;
	}

	public String getImgData(byte[] byteData) {
		return Base64.getMimeEncoder().encodeToString(byteData);
	}
	
	public String getImageOfItem(Item item) {
		System.out.println("getImageOfItem =" +item);
		return Base64.getMimeEncoder().encodeToString(item.getImage());
	}
	
	public String getImageOfRoom(Room room) {
		System.out.println("getImageOfRoom =" + room);
		return Base64.getMimeEncoder().encodeToString(room.getImage());
	}
	
	public void saveItemImage(Item item, MultipartFile file) throws IOException {
		item.setImage(file.getBytes());
	}
}
