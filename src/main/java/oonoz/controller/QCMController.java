package oonoz.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.QCM;
import oonoz.service.QCMService;

/**
 * The Class QCMController.
 */
@RestController
public class QCMController {

	/** The qcm service. */
	@Autowired
	QCMService qcmService;
	
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
}
