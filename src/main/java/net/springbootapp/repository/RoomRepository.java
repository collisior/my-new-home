package net.springbootapp.repository;

import org.springframework.data.domain.Sort;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springbootapp.model.User;
import net.springbootapp.model.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findByUser(User user, Sort sort);
}
