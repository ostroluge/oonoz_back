package oonoz.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.Player;
import oonoz.domain.QCM;
import oonoz.domain.Supplier;
import oonoz.dto.converter.QCMEncryptDtoConverter;
import oonoz.dto.converter.QCMPlayDtoConverter;
import oonoz.dto.model.PlayerDto;
import oonoz.dto.model.QCMDto;
import oonoz.dto.model.QCMEncryptDto;
import oonoz.dto.model.QCMPlayDto;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.QCMAlreadyExistException;
import oonoz.exception.QCMCreationException;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.ThemeDoesNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.service.PlayerService;
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
	
	/** The player service. */
	@Autowired
	PlayerService playerService;
	
	@Autowired
	QCMEncryptDtoConverter qcmEncryptConverter;
	
	@Autowired
	QCMPlayDtoConverter qcmPlayConverter;
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(QCMPlayController.class);

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
	
	/**
	 * Post QCM.
	 *
	 * @param qcmDto
	 *            the qcm dto
	 * @return the response entity <<<<<<< HEAD =======
	 * @throws WrongInformationException
	 *             the wrong information exception >>>>>>> feature/QCMManagement
	 */
	@RequestMapping(value = "/qcms/finishQCM", method = RequestMethod.POST)
	public ResponseEntity<String> postFinishQCM(Authentication authentication, @RequestBody QCMPlayDto qcmPlayDto) {

		
		Supplier supplier = (Supplier) getUserFromAuthentication(authentication);
		qcmPlayDto.setIdPlayer(supplier.getIdPlayer());
		
		qcmPlayService.createQCMPlay(qcmPlayConverter.convertToEntity(qcmPlayDto));
		
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	/**
	 * Get player from Authentication
	 * 
	 * @param authentication
	 * @return
	 * @throws PlayerNotExistException
	 */
	public Player getUserFromAuthentication(Authentication authentication) {
		try {
			return playerService.getPlayerByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
		} catch (PlayerNotExistException e) {
			logger.error("Player does not exist !", e);
			return null;
		}

	}
}
