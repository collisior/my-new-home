package net.springbootapp.model;

import javax.persistence.*;

import net.springbootapp.ImageHandler;
import net.springbootapp.web.dto.ItemDTO;


@Entity
@Table(name = "item")
public class Item implements Comparable<Item>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String details;
    private String link;
    private double price;
    private String status;
    private Long user_id;
    private Long room_id;
    
    @Lob
    private byte[] image;
    
        
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false, insertable = false, updatable = false)
    private Room room;
    
    public Item() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getUserId() {
		return user_id;
	}

	public void setUserId(Long user_id) {
		this.user_id = user_id;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", room id='" + room_id + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Long getRoomId() {
		return room_id;
	}

	public void setRoomId(Long room_id) {
		this.room_id = room_id;
	}
	
	@Override
	public int compareTo(Item otherItem) {
		return Integer.compare(getId().intValue(), otherItem.getId().intValue());
	}

}
