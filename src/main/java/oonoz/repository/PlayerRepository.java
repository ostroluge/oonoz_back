package oonoz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Player;

public interface PlayerRepository extends CrudRepository<Player, Long>{

	List<Player> findAll();

}
