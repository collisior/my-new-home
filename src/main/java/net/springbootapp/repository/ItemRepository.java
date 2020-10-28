package net.springbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springbootapp.model.Item;
import net.springbootapp.model.Room;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByRoom(Room room, Sort sort);
	Optional<Item> findById(Long id);
}
