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

import oonoz.domain.QCM;
import oonoz.dto.converter.QCMEncryptDtoConverter;
import oonoz.dto.model.PlayerDto;
import oonoz.dto.model.QCMDto;
import oonoz.dto.model.QCMEncryptDto;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.service.QCMPlayService;
import oonoz.service.QCMService;

/**
 * The Class QCMPlayController.
 */
@RestController
public class QCMPlayController {
	
	
	/** The qcm service. */
	@Autowired
	QCMService qcmService;

	/** The qcm play service. */
	@Autowired
	QCMPlayService qcmPlayService;
	
	@Autowired
	QCMEncryptDtoConverter qcmEncryptConverter;

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
	 * Gets the qcm.
	 *
	 * @param id
	 *            the id
	 * @return the qcm
	 * @throws QCMDoesNotExistException
	 *             the QCM does not exist exception
	 */
	@RequestMapping(value = "/qcms/validated/{id}", method = RequestMethod.GET)
	public ResponseEntity<QCMEncryptDto> getValidatedQCM(@PathVariable("id") long id) throws QCMDoesNotExistException {
		QCM qcm = qcmService.getValidatedQCM(id);
		// TODO only supplier can get the QCM
		if (qcm != null) {
			return ResponseEntity.status(HttpStatus.OK).body(qcmEncryptConverter.convertToDto(qcm));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
