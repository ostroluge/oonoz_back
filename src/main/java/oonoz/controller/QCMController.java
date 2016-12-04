package oonoz.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.QCM;
import oonoz.domain.Question;
import oonoz.dto.converter.QCMDtoConverter;
import oonoz.dto.converter.QuestionDtoConverter;
import oonoz.dto.model.QCMDto;
import oonoz.dto.model.QuestionDto;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.service.QCMService;
import oonoz.util.StringResponse;

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
	
	/** The question dto converter. */
	@Autowired
	QuestionDtoConverter questionDtoConverter;
	
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
	 * @throws WrongInformationException the wrong information exception
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
	
	/**
	 * Post question.
	 *
	 * @param idQCM the id QCM
	 * @param questionDto the question dto
	 * @return the response entity
	 */
	@RequestMapping(value="/qcms/{idQCM}/questions", method = RequestMethod.POST)
	public ResponseEntity<Question> postQuestion(@PathVariable("idQCM") long idQCM,
			@RequestBody QuestionDto questionDto) {
		Question questionToPost = questionDtoConverter.convertToEntity(questionDto);
		
		if (questionToPost != null) {
			try {
				Question result = qcmService.postQuestion(idQCM, questionToPost);
				return ResponseEntity.status(HttpStatus.OK)
						.body(result);
			} catch (QCMDoesNotExistException e) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body(null);
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
	
	/**
	 * Delete QCM.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(value="/qcms/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<StringResponse> deleteQCM(@PathVariable long id) {
		StringResponse response = new StringResponse();
		
		try {
			qcmService.deleteQCM(id);
		} catch (QCMDoesNotExistException e) {
			response.setResponse("The QCM does not exist");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(response);
		}
		
		response.setResponse("Deletion successful");
		return ResponseEntity.status(HttpStatus.OK)
				.body(response);
	}
}
