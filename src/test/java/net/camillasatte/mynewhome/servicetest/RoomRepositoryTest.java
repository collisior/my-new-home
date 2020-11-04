package net.camillasatte.mynewhome.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.camillasatte.mynewhome.model.Room;
import net.camillasatte.mynewhome.repository.RoomRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTest {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Test
    public void testSaveRoom() {
        Room room = new Room("room name");
        roomRepository.save(room);
        Long id = room.getId();
        Optional<Room> room2 = roomRepository.findById(id);
        assertNotNull(room);
        assertEquals(room2.get().getName(), room.getName());
        assertEquals(room2.get().getDetails(), room.getDetails());
    }
	
	@Test
    public void testGetRoom() {
        Room room = new Room("room name");
        roomRepository.save(room);
        Long id = room.getId();
        Optional<Room> room2 = roomRepository.findById(id);
        assertNotNull(room);
        assertEquals(room2.get().getName(), room.getName());
        assertEquals(room2.get().getDetails(), room.getDetails());
    }
	
	@Test
    public void testDeleteRoom() {
		Room room = new Room("room name");
        roomRepository.save(room);
        roomRepository.delete(room);
    }
	
	@Test
    public void findAllRoom() {
		Room room = new Room("room name");
        roomRepository.save(room);
        assertNotNull(roomRepository.findAll());
    }

    @Test
    public void deletByRoomIdTest() {
    	Room room = new Room("room name");
    	Room roomCopy = roomRepository.save(room);
    	roomRepository.deleteById(roomCopy.getId());
    }
}
