package oonoz.manager.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import oonoz.domain.Player;
import oonoz.manager.PlayerManager;
import oonoz.repository.PlayerRepository;

@Service(value = "playerManager")
public class PlayerManagerImpl implements PlayerManager{

	@Resource
	private PlayerRepository playerRepository;
	
	@Override
	public Collection<Player> getAllPlayer() {
		return this.playerRepository.findAll();
	}

	
}
