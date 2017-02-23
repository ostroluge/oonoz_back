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
	private QCMPlayRepository qcmPlayRepositoty;
	
	/**
	 * Gets the QCM winners.
	 *
	 * @param idQcm the id qcm
	 * @param minimumScore the minimum score
	 * @return the QCM play
	 */
	public List<PlayerDto> getQCMWinners(long idQcm, int minimumScore) {
		List<QCMPlay> allPlays = qcmPlayRepositoty.findByIdQcm(idQcm);
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
	
	public QCMPlay findOne(long id) {
		return qcmPlayRepositoty.findOne(id);
	}
	
	public QCMPlay update(QCMPlay existingOne, QCMPlay updatedOne) {
		
		updatedOne.setIdQcm(existingOne.getIdQcm());
		updatedOne.setIdPlayer(existingOne.getIdPlayer());
		updatedOne.setPlayer(existingOne.getPlayer());
		updatedOne.setFinished(existingOne.isFinished());
		updatedOne.setScore(existingOne.getScore());
		
		updatedOne.setQuestion1(existingOne.isQuestion1());
		updatedOne.setQuestion2(existingOne.isQuestion2());
		updatedOne.setQuestion3(existingOne.isQuestion3());
		updatedOne.setQuestion4(existingOne.isQuestion4());
		updatedOne.setQuestion5(existingOne.isQuestion5());
		updatedOne.setQuestion6(existingOne.isQuestion6());
		updatedOne.setQuestion7(existingOne.isQuestion7());
		updatedOne.setQuestion8(existingOne.isQuestion8());
		updatedOne.setQuestion9(existingOne.isQuestion9());
		updatedOne.setQuestion10(existingOne.isQuestion10());
		updatedOne.setQuestion11(existingOne.isQuestion11());
		updatedOne.setQuestion12(existingOne.isQuestion12());
		updatedOne.setQuestion13(existingOne.isQuestion13());
		updatedOne.setQuestion14(existingOne.isQuestion14());
		updatedOne.setQuestion15(existingOne.isQuestion15());
		updatedOne.setQuestion16(existingOne.isQuestion16());
		updatedOne.setQuestion17(existingOne.isQuestion17());
		updatedOne.setQuestion18(existingOne.isQuestion18());
		updatedOne.setQuestion19(existingOne.isQuestion19());
		updatedOne.setQuestion20(existingOne.isQuestion20());
		
		return qcmPlayRepositoty.save(updatedOne);
	}
}
