package net.springbootapp;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import net.springbootapp.model.Item;

public class ImageHandler {

	public static boolean isEmpty(byte[] imageData) {
		if (imageData.length == 0) {
			return true;
		}
		return false;
	}

	public static String getImgData(byte[] byteData) {
		return Base64.getMimeEncoder().encodeToString(byteData);
	}

	public static void saveItemImage(Item item, MultipartFile file) throws IOException {
		item.setImage(file.getBytes());
	}
}
