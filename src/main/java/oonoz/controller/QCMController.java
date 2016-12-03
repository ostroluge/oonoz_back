package oonoz.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.QCM;
import oonoz.dto.converter.QCMDtoConverter;
import oonoz.dto.model.QCMDto;
import oonoz.exception.WrongInformationException;
import oonoz.service.QCMService;

/**
 * The Class QCMController.
 */
@RestController
public class QCMController {

	/** The qcm service. */
	@Autowired
	QCMService qcmService;
	
	/** The qcm dto converter. */
	@Autowired
	QCMDtoConverter qcmDtoConverter;
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@RequestMapping(value="/qcms", method = RequestMethod.GET)
	public ResponseEntity<Collection<QCM>> getAll() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(qcmService.findAll());
	}

	/**
	 * Post QCM.
	 *
	 * @param qcmDto the qcm dto
	 * @return the response entity
	 */
	@RequestMapping(value="/qcms", method = RequestMethod.POST)
	public ResponseEntity<QCM> postQCM(@RequestBody QCMDto qcmDto) throws WrongInformationException {
		QCM qcmToPost = qcmDtoConverter.convertToEntity(qcmDto);
		
		QCM result = qcmService.postQCM(qcmToPost);
		
		if (result == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(result);
	}
}
