package oonoz.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.Player;
import oonoz.domain.QCMPlay;
import oonoz.dto.model.PlayerDto;
import oonoz.manager.QCMPlayManager;
import oonoz.repository.QCMPlayRepository;

/**
 * The Class QCMPlayManagerImpl.
 */
@Component(value = "QCMPlayManager")
public class QCMPlayManagerImpl implements QCMPlayManager {

	/** The qcm play repositoty. */
	@Resource
	private QCMPlayRepository qcmPlayRepository;
	
	/**
	 * Gets the QCM winners.
	 *
	 * @param idQcm the id qcm
	 * @param minimumScore the minimum score
	 * @return the QCM play
	 */
	public List<PlayerDto> getQCMWinners(long idQcm, int minimumScore) {
		List<QCMPlay> allPlays = qcmPlayRepository.findByIdQcm(idQcm);
		List<PlayerDto> result = new ArrayList<>();
		
		for (QCMPlay play : allPlays) {
			if (play.isFinished()) {
				Integer score = play.getScore();
				if (score != null && score >= minimumScore) {
					Player player = play.getPlayer();
					PlayerDto winner = new PlayerDto();
					winner.setLastName(player.getLastName());
					winner.setFirstName(player.getFirstName());
					winner.setMail(player.getMail());
					winner.setUsername(player.getUsername());
					result.add(winner);
				}
			}
		}
		
		return result;
	}
	
	public void createPlayQCM(QCMPlay qcmPlay){
		qcmPlayRepository.save(qcmPlay);
	}
}
