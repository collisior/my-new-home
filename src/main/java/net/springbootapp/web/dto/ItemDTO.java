package net.springbootapp.web.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import net.springbootapp.model.Room;

public class ItemDTO {

	@NotEmpty
	private String name;
	private String details;
	private String link;
	private double price;
	private Room roomSelected;
	private MultipartFile image;
	private String statusSelected;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getStatusSelected() {
		return statusSelected;
	}

	public void setStatusSelected(String statusSelected) {
		this.statusSelected = statusSelected;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Room getRoomSelected() {
		return roomSelected;
	}

	public void setRoomSelected(Room roomSelected) {
		this.roomSelected = roomSelected;
	}
}
