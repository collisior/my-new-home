package net.camillasatte.mynewhome.web.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class RoomDTO {

	@NotEmpty
	private String name;
	private String details;
	private Long user_id;
	private MultipartFile image;

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

	public Long getUserId() {
		return user_id;
	}

	public void setUserId(Long user_id) {
		this.user_id = user_id;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
