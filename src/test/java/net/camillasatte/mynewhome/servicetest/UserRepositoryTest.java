package net.camillasatte.mynewhome.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.camillasatte.mynewhome.model.User;
import net.camillasatte.mynewhome.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
    public void testSaveUser() {
        User user = new User("firstName", "lastName", "email@email.com", "password");
        userRepository.save(user);
        User user2 = userRepository.findByEmail("email@email.com");
        assertNotNull(user);
        assertEquals(user2.getFirstName(), user.getFirstName());
        assertEquals(user2.getLastName(), user.getLastName());
    }
	
	@Test
    public void testGetUser() {
        User user = new User("firstName", "lastName", "email@email.com", "password");
        userRepository.save(user);
        User user2 = userRepository.findByEmail("email@email.com");
        assertNotNull(user);
        assertEquals(user2.getFirstName(), user.getFirstName());
        assertEquals(user2.getLastName(), user.getLastName());
    }
	
	@Test
    public void testDeleteUser() {
		User user = new User("firstName", "lastName", "email@email.com", "password");
        userRepository.save(user);
        userRepository.delete(user);
    }
	
	@Test
    public void findAllUser() {
		User user = new User("firstName", "lastName", "email@email.com", "password");
        userRepository.save(user);
        assertNotNull(userRepository.findAll());
    }

    @Test
    public void deletByUserIdTest() {
    	User user = new User("firstName", "lastName", "email@email.com", "password");
    	User userCopy = userRepository.save(user);
    	userRepository.deleteById(userCopy.getId());
    }
}
