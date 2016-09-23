package oonoz.back.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import oonoz.back.domain.Player;

public interface PlayerRepository extends CrudRepository<Player, Long>{

	List<Player> findAll();

}
