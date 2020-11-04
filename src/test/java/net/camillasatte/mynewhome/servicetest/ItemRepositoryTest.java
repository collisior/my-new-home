package net.camillasatte.mynewhome.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.camillasatte.mynewhome.model.Item;
import net.camillasatte.mynewhome.repository.ItemRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Test
    public void testSaveItem() {
        Item item = new Item("item name", 100.0);
        itemRepository.save(item);
        Long id = item.getId();
        Optional<Item> item2 = itemRepository.findById(id);
        assertNotNull(item);
        assertEquals(item2.get().getName(), item.getName());
        assertEquals(item2.get().getDetails(), item.getDetails());
    }
	
	@Test
    public void testGetItem() {
        Item item = new Item("item name", 100.0);
        itemRepository.save(item);
        Long id = item.getId();
        Optional<Item> item2 = itemRepository.findById(id);
        assertNotNull(item);
        assertEquals(item2.get().getName(), item.getName());
        assertEquals(item2.get().getDetails(), item.getDetails());
    }
	
	@Test
    public void testDeleteItem() {
        Item item = new Item("item name", 100.0);
        itemRepository.save(item);
        itemRepository.delete(item);
    }
	
	@Test
    public void findAllItem() {
        Item item = new Item("item name", 100.0);
        itemRepository.save(item);
        assertNotNull(itemRepository.findAll());
    }

    @Test
    public void deletByItemIdTest() {
        Item item = new Item("item name", 100.0);
    	Item itemCopy = itemRepository.save(item);
    	itemRepository.deleteById(itemCopy.getId());
    }
}
