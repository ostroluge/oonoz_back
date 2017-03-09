package oonoz.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.Comment;
import oonoz.domain.Feedback;
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

	/** The qcm play repository. */
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
	
	/**
	 * Creates the play QCM.
	 *
	 * @param qcmPlay the qcm play
	 */
	public void createPlayQCM(QCMPlay qcmPlay){
		qcmPlayRepository.save(qcmPlay);
	}

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the QCM play
	 */
	public QCMPlay findOne(long id) {
		return qcmPlayRepository.findOne(id);
	}

	/**
	 * Update.
	 *
	 * @param existingOne the existing one
	 * @param updatedOne the updated one
	 * @return the QCM play
	 */
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

		return qcmPlayRepository.save(updatedOne);
	}

	/**
	 * Gets the QCM feedback.
	 *
	 * @param idQcm the id qcm
	 * @return the QCM feedback
	 */
	public Feedback getQCMFeedback(long idQcm) {
		List<QCMPlay> allPlays = qcmPlayRepository.findByIdQcm(idQcm);

		List<Comment> comments = new ArrayList<>();
		List<Double> ratings = new ArrayList<>();

		for (QCMPlay play : allPlays) {
			if (play.getRating() != null && play.getComment() != null 
					&& !play.getComment().equals("")) {
				Comment comment = new Comment(play.getComment(),
						play.getPlayer().getUsername(),
						play.getRating());
				comments.add(comment);
				ratings.add(play.getRating());
			}
		}

		Feedback feedback = new Feedback();
		if (!comments.isEmpty()) {
			feedback.setComments(comments);
		}
		if (!ratings.isEmpty()) {
			feedback.setAverageRating(calculateAverageRating(ratings));
		}

		feedback.setTotalComments(comments.size());
		feedback.setTotalRatings(ratings.size());

		return feedback;
	}

	/**
	 * Calculate average rating.
	 *
	 * @param ratings the ratings
	 * @return the double
	 */
	private double calculateAverageRating(List<Double> ratings) {
		double result = 0.0;
		double sum = 0.0;
		for (Double rating : ratings) {
			sum += rating;
		}
		result = sum / ratings.size();

		return Math.round(result * 2) / 2.0;
	}

	public Long getQcmPlayed(long idPlayer) {
		return qcmPlayRepository.countByIdPlayer(idPlayer);
	}

	public Long getQcmWon(long idPlayer) {
		 List<QCMPlay> qcms = qcmPlayRepository.findByIdPlayer(idPlayer);
		 int result = 0;
		 for (QCMPlay c : qcms){
			 if (c.getScore() >= 10){
				 result++;
			 }
		 }
		 return (long) result;
		 
	}

	public Long getMean(long idPlayer) {
		 List<QCMPlay> qcms = qcmPlayRepository.findByIdPlayer(idPlayer);
		 int result = 0;
		 for (QCMPlay c : qcms){
			 result += c.getScore();
		 }
		 return (long) result/(qcms.size());
		 
	}
	

	public Long qcmComment(long idPlayer) {
		 List<QCMPlay> qcms = qcmPlayRepository.findByIdPlayer(idPlayer);
		 int result = 0;
		 for (QCMPlay c : qcms){
			 if ((!c.getComment().equals("") || c.getComment()!=null)){
				 result++;
			 }
		 }
		 return (long) result;
	}
}
