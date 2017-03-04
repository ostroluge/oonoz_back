package oonoz.dto.converter;

import org.springframework.stereotype.Component;
import oonoz.domain.QCMPlay;
import oonoz.dto.model.QCMPlayDto;

/**
 * The Class QCMPlayDtoConverter.
 */
@Component
public class QCMPlayDtoConverter {

	/**
	 * Convert to entity.
	 *
	 * @param qcmPlayDto the qcm play dto
	 * @return the QCM play
	 */
	public QCMPlay convertToEntity(QCMPlayDto qcmPlayDto) {
		QCMPlay qcmPlay = new QCMPlay();

		qcmPlay.setId(qcmPlayDto.getId());
		qcmPlay.setIdQcm(qcmPlayDto.getIdQcm());
		qcmPlay.setIdPlayer(qcmPlayDto.getIdPlayer());
		qcmPlay.setQuestion1(qcmPlayDto.isQuestion1());
		qcmPlay.setQuestion2(qcmPlayDto.isQuestion2());
		qcmPlay.setQuestion3(qcmPlayDto.isQuestion3());
		qcmPlay.setQuestion4(qcmPlayDto.isQuestion4());
		qcmPlay.setQuestion5(qcmPlayDto.isQuestion5());
		qcmPlay.setQuestion6(qcmPlayDto.isQuestion6());
		qcmPlay.setQuestion7(qcmPlayDto.isQuestion7());
		qcmPlay.setQuestion8(qcmPlayDto.isQuestion8());
		qcmPlay.setQuestion9(qcmPlayDto.isQuestion9());
		qcmPlay.setQuestion10(qcmPlayDto.isQuestion10());
		qcmPlay.setQuestion11(qcmPlayDto.isQuestion11());
		qcmPlay.setQuestion12(qcmPlayDto.isQuestion12());
		qcmPlay.setQuestion13(qcmPlayDto.isQuestion13());
		qcmPlay.setQuestion14(qcmPlayDto.isQuestion14());
		qcmPlay.setQuestion15(qcmPlayDto.isQuestion15());
		qcmPlay.setQuestion16(qcmPlayDto.isQuestion16());
		qcmPlay.setQuestion17(qcmPlayDto.isQuestion17());
		qcmPlay.setQuestion18(qcmPlayDto.isQuestion18());
		qcmPlay.setQuestion19(qcmPlayDto.isQuestion19());
		qcmPlay.setQuestion20(qcmPlayDto.isQuestion20());
		qcmPlay.setScore(qcmPlayDto.getScore());
		qcmPlay.setComment(qcmPlayDto.getComment());
		qcmPlay.setFinished(qcmPlayDto.isFinished());
		qcmPlay.setNote(qcmPlayDto.getNote());
		
		return qcmPlay;
	}
	
	/**
	 * Convert to dto.
	 *
	 * @param qcmPlay the qcm play
	 * @return the QCM play dto
	 */
	public QCMPlayDto convertToDto(QCMPlay qcmPlay) {
		QCMPlayDto qcmPlayDto = new QCMPlayDto();
		
		qcmPlayDto.setId(qcmPlay.getId());
		qcmPlayDto.setIdQcm(qcmPlay.getIdQcm());
		qcmPlayDto.setIdPlayer(qcmPlay.getIdPlayer());
		qcmPlayDto.setQuestion1(qcmPlay.isQuestion1());
		qcmPlayDto.setQuestion2(qcmPlay.isQuestion2());
		qcmPlayDto.setQuestion3(qcmPlay.isQuestion3());
		qcmPlayDto.setQuestion4(qcmPlay.isQuestion4());
		qcmPlayDto.setQuestion5(qcmPlay.isQuestion5());
		qcmPlayDto.setQuestion6(qcmPlay.isQuestion6());
		qcmPlayDto.setQuestion7(qcmPlay.isQuestion7());
		qcmPlayDto.setQuestion8(qcmPlay.isQuestion8());
		qcmPlayDto.setQuestion9(qcmPlay.isQuestion9());
		qcmPlayDto.setQuestion10(qcmPlay.isQuestion10());
		qcmPlayDto.setQuestion11(qcmPlay.isQuestion11());
		qcmPlayDto.setQuestion12(qcmPlay.isQuestion12());
		qcmPlayDto.setQuestion13(qcmPlay.isQuestion13());
		qcmPlayDto.setQuestion14(qcmPlay.isQuestion14());
		qcmPlayDto.setQuestion15(qcmPlay.isQuestion15());
		qcmPlayDto.setQuestion16(qcmPlay.isQuestion16());
		qcmPlayDto.setQuestion17(qcmPlay.isQuestion17());
		qcmPlayDto.setQuestion18(qcmPlay.isQuestion18());
		qcmPlayDto.setQuestion19(qcmPlay.isQuestion19());
		qcmPlayDto.setQuestion20(qcmPlay.isQuestion20());
		qcmPlayDto.setScore(qcmPlay.getScore());
		qcmPlayDto.setComment(qcmPlay.getComment());
		qcmPlayDto.setFinished(qcmPlay.isFinished());
		qcmPlayDto.setNote(qcmPlay.getNote());
		return qcmPlayDto;
	}
}
