package oonoz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oonoz.dto.model.PlayerDto;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.service.QCMPlayService;

/**
 * The Class QCMPlayController.
 */
@RestController
public class QCMPlayController {

	/** The qcm play service. */
	@Autowired
	QCMPlayService qcmPlayService;

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
}
