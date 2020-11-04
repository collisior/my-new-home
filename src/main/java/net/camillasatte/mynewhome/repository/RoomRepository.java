package net.camillasatte.mynewhome.repository;

import org.springframework.data.domain.Sort;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.camillasatte.mynewhome.model.Room;
import net.camillasatte.mynewhome.model.User;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findByUser(User user, Sort sort);
}
