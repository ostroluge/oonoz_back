package oonoz.back.services.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import oonoz.back.domain.Player;
import oonoz.back.repositories.PlayerRepository;
import oonoz.back.services.PlayerService;

@Service(value = "playerService")
public class PlayerServiceImpl implements PlayerService{

	@Resource
	private PlayerRepository playerRepository;
	
	@Override
	public Collection<Player> getAllPlayer() {
		return this.playerRepository.findAll();
	}

	
}
