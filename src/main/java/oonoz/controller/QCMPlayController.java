package oonoz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.Feedback;
import oonoz.domain.QCMPlay;
import oonoz.dto.converter.QCMPlayDtoConverter;
import oonoz.dto.model.PlayerDto;
import oonoz.dto.model.QCMPlayDto;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.QCMPlayDoesNotExistException;
import oonoz.service.QCMPlayService;

/**
 * The Class QCMPlayController.
 */
@RestController
public class QCMPlayController {

	/** The qcm play service. */
	@Autowired
	QCMPlayService qcmPlayService;

	/** The qcm play dto converter. */
	@Autowired
	QCMPlayDtoConverter qcmPlayDtoConverter;
	
	/**
	 * Gets the winners.
	 *
	 * @param idQcm the id qcm
	 * @return the winners
	 */
	@RequestMapping(value = "/qcms/{idQcm}/winners", method = RequestMethod.GET)
	public ResponseEntity<List<PlayerDto>> getWinners(@PathVariable("idQcm") long idQcm) {
		List<PlayerDto> result = new ArrayList<>();

		try {
			result = qcmPlayService.findWinners(idQcm, 10);
		} catch(QCMDoesNotExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/**
	 * Update QCM play.
	 *
	 * @param idQcm the id qcm
	 * @param qcmPlay the qcm play
	 * @return the response entity
	 */
	@RequestMapping(value = "/qcms/{idQcm}/plays", method = RequestMethod.PUT) 
	public ResponseEntity<QCMPlay> updateQCMPlay(@PathVariable("idQcm") long idQcm, @RequestBody QCMPlayDto qcmPlay) {
		QCMPlay qcmPlayToUpdate = qcmPlayDtoConverter.convertToEntity(qcmPlay);
		
		if (qcmPlayToUpdate != null) {
			try {
				QCMPlay result = qcmPlayService.updateQCMPlay(qcmPlayToUpdate);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} catch (QCMPlayDoesNotExistException e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	/**
	 * Gets the feedback.
	 *
	 * @param idQcm the id qcm
	 * @return the feedback
	 */
	@RequestMapping(value = "/qcms/{idQcm}/feedback", method = RequestMethod.GET)
	public ResponseEntity<Feedback> getFeedback(@PathVariable("idQcm") long idQcm) {
		try {
			Feedback result = qcmPlayService.getQCMFeedback(idQcm);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (QCMDoesNotExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
