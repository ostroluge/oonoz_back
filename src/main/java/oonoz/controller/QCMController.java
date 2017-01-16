package oonoz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.Player;
import oonoz.domain.QCM;
import oonoz.domain.Question;
import oonoz.domain.Supplier;
import oonoz.dto.converter.QCMDtoConverter;
import oonoz.dto.converter.QuestionDtoConverter;
import oonoz.dto.model.QCMDto;
import oonoz.dto.model.QuestionDto;
import oonoz.exception.PlayerNotExistException;
import oonoz.exception.QCMAlreadyExistException;
import oonoz.exception.QCMCreationException;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.QuestionDoesNotExistException;
import oonoz.exception.SubThemeAlreadyAddedException;
import oonoz.exception.SubThemeDoesNotExistException;
import oonoz.exception.ThemeDoesNotExistException;
import oonoz.exception.TooManyQuestionsException;
import oonoz.exception.WrongInformationException;
import oonoz.service.PlayerService;
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

	/** The player service. */
	@Autowired
	PlayerService playerService;

	/** The qcm dto converter. */
	@Autowired
	QCMDtoConverter qcmDtoConverter;

	/** The question dto converter. */
	@Autowired
	QuestionDtoConverter questionDtoConverter;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(QCMController.class);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@RequestMapping(value = "/qcms", method = RequestMethod.GET)
	public ResponseEntity<List<QCMDto>> getAll() {
		List<QCM> qcms = qcmService.findAll();
		List<QCMDto> result = new ArrayList<>();
		for (QCM qcm : qcms) {
			result.add(qcmDtoConverter.convertToDto(qcm));
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
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
	@RequestMapping(value = "/qcms", method = RequestMethod.POST)
	public ResponseEntity<QCM> postQCM(Authentication authentication, @RequestBody QCMDto qcmDto) {

		Supplier supplier = (Supplier) getUserFromAuthentication(authentication);
		qcmDto.setIdSupplier(supplier.getIdPlayer());

		QCM qcmToPost = qcmDtoConverter.convertToEntity(qcmDto);
		QCM result = null;
		try {
			result = qcmService.postQCM(qcmToPost);
		} catch (WrongInformationException e) {
			logger.error("Wrong information", e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		} catch (QCMCreationException | ThemeDoesNotExistException e) {
			logger.error("Error creation", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (QCMAlreadyExistException e) {
			logger.error("Error creation", e);
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * Post question.
	 *
	 * @param idQCM
	 *            the id QCM
	 * @param questionDto
	 *            the question dto
	 * @return the response entity
	 */
	@RequestMapping(value = "/qcms/{idQCM}/questions", method = RequestMethod.POST)
	public ResponseEntity<Question> postQuestion(@PathVariable("idQCM") long idQCM,
			@RequestBody QuestionDto questionDto) {
		Question questionToPost = questionDtoConverter.convertToEntity(questionDto);

		if (questionToPost != null) {
			try {
				Question result = qcmService.postQuestion(idQCM, questionToPost);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} catch (QCMDoesNotExistException e) {
				logger.error("The QCM does not exist !", e);
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
			} catch (TooManyQuestionsException e) {
				logger.error("They are too many questions in QCM!", e);
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			} catch (WrongInformationException e) {
				logger.error("The QCM does not exist !", e);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * Delete QCM.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@RequestMapping(value = "/qcms/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<StringResponse> deleteQCM(@PathVariable long id) {
		StringResponse response = new StringResponse();

		try {
			qcmService.deleteQCM(id);
		} catch (QCMDoesNotExistException e) {
			response.setResponse("The QCM does not exist");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
		}

		response.setResponse("Deletion successful");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Gets the question.
	 *
	 * @param idQCM
	 *            the id QCM
	 * @param idQuestion
	 *            the id question
	 * @return the question
	 * @throws QCMDoesNotExistException
	 *             the QCM does not exist exception
	 * @throws QuestionDoesNotExistException
	 *             the question does not exist exception
	 */
	@RequestMapping(value = "/qcms/{idQCM}/questions/{idQuestion}", method = RequestMethod.GET)
	public ResponseEntity<Question> getQuestion(@PathVariable("idQCM") long idQCM,
			@PathVariable("idQuestion") long idQuestion)
			throws QCMDoesNotExistException, QuestionDoesNotExistException {

		Question question = qcmService.getQuestion(idQCM, idQuestion);

		if (question != null) {
			return ResponseEntity.status(HttpStatus.OK).body(question);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

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
	@RequestMapping(value = "/qcms/{id}", method = RequestMethod.GET)
	public ResponseEntity<QCMDto> getQCM(@PathVariable("id") long id) throws QCMDoesNotExistException {
		QCM qcm = qcmService.getQCM(id);
		// TODO only supplier can get the QCM
		if (qcm != null) {
			return ResponseEntity.status(HttpStatus.OK).body(qcmDtoConverter.convertToDto(qcm));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Delete question.
	 *
	 * @param idQCM
	 *            the id QCM
	 * @param idQuestion
	 *            the id question
	 * @return the response entity
	 */
	@RequestMapping(value = "/qcms/{idQCM}/questions/{idQuestion}", method = RequestMethod.DELETE)
	public ResponseEntity<StringResponse> deleteQuestion(@PathVariable("idQCM") long idQCM,
			@PathVariable("idQuestion") long idQuestion) {
		StringResponse response = new StringResponse();
		try {
			qcmService.deleteQuestion(idQCM, idQuestion);
			response.setResponse("Deletion successful");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (QCMDoesNotExistException e) {
			response.setResponse("The QCM does not exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (QuestionDoesNotExistException e) {
			response.setResponse("The question does not exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Adds the sub theme.
	 *
	 * @param idQCM
	 *            the id QCM
	 * @param idSubTheme
	 *            the id sub theme
	 * @return the response entity
	 */
	@RequestMapping(value = "/qcms/{idQCM}/subthemes/{idSubTheme}", method = RequestMethod.POST)
	public ResponseEntity<QCMDto> addSubTheme(@PathVariable("idQCM") long idQCM,
			@PathVariable("idSubTheme") long idSubTheme) {
		try {
			QCM result = qcmService.addSubTheme(idQCM, idSubTheme);
			return ResponseEntity.status(HttpStatus.OK).body(qcmDtoConverter.convertToDto(result));
		} catch (QCMDoesNotExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (SubThemeDoesNotExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (SubThemeAlreadyAddedException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
	}

	/**
	 * Delete sub theme.
	 *
	 * @param idQCM
	 *            the id QCM
	 * @param idSubTheme
	 *            the id sub theme
	 * @return the response entity
	 */
	@RequestMapping(value = "/qcms/{idQCM}/subthemes/{idSubTheme}", method = RequestMethod.DELETE)
	public ResponseEntity<StringResponse> deleteSubTheme(@PathVariable("idQCM") long idQCM,
			@PathVariable("idSubTheme") long idSubTheme) {
		StringResponse response = new StringResponse();

		try {
			qcmService.deleteSubTheme(idQCM, idSubTheme);
			response.setResponse("Deletion successful");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (QCMDoesNotExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} catch (SubThemeDoesNotExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	/**
	 * Edits the QCM.
	 *
	 * @param id
	 *            the id
	 * @param qcm
	 *            the qcm
	 * @return the response entity
	 * @throws WrongInformationException
	 */
	@RequestMapping(value = "/qcms/{id}", method = RequestMethod.PUT)
	public ResponseEntity<QCMDto> editQCM(Authentication authentication, @PathVariable("id") long id,
			@RequestBody QCMDto qcm) throws WrongInformationException {
		Supplier supplier = (Supplier) getUserFromAuthentication(authentication);
		qcm.setIdSupplier(supplier.getIdPlayer());
		QCM qcmToUpdate = qcmDtoConverter.convertToEntity(qcm);

		if (qcmToUpdate != null) {
			try {
				QCM result = qcmService.updateQCM(id, qcmToUpdate);
				return ResponseEntity.status(HttpStatus.OK).body(qcmDtoConverter.convertToDto(result));
			} catch (QCMDoesNotExistException | QCMCreationException e) {
				logger.error("Error in QCM update", e);
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * Edits the question.
	 *
	 * @param idQCM
	 *            the id QCM
	 * @param idQuestion
	 *            the id question
	 * @param question
	 *            the question
	 * @return the response entity
	 */
	@RequestMapping(value = "/qcms/{idQCM}/questions/{idQuestion}", method = RequestMethod.PUT)
	public ResponseEntity<Question> editQuestion(@PathVariable("idQCM") long idQCM,
			@PathVariable("idQuestion") long idQuestion, @RequestBody QuestionDto question) {
		Question questionToUpdate = questionDtoConverter.convertToEntity(question);

		if (questionToUpdate != null) {
			try {
				Question result = qcmService.updateQuestion(idQuestion, questionToUpdate);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} catch (QuestionDoesNotExistException e) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * Gets the question by number.
	 *
	 * @param idQCM
	 *            the id QCM
	 * @param questionNumber
	 *            the question number
	 * @return the qcm
	 * @throws QCMDoesNotExistException
	 *             the QCM does not exist exception
	 */
	@RequestMapping(value = "/qcms/{idQCM}/questions/questionNumber/{questionNumber}", method = RequestMethod.GET)
	public ResponseEntity<QuestionDto> getQuestionByNumber(@PathVariable("idQCM") long idQCM,
			@PathVariable("questionNumber") int questionNumber) {
		Question question;
		try {
			question = qcmService.getQuestionByNumber(idQCM, questionNumber);
		} catch (QCMDoesNotExistException e) {
			logger.error("The QCM does not exist !", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		/** The question has not yet been created **/
		if (question == null) {
			return ResponseEntity.status(HttpStatus.OK).body(questionDtoConverter.convertToDto(new Question()));

		}
		return ResponseEntity.status(HttpStatus.OK).body(questionDtoConverter.convertToDto(question));
	}

	/**
	 * Search supplier QCM.
	 *
	 * @param authentication
	 *            the authentication
	 * @param requestParams
	 *            the request params
	 * @return the response entity
	 * @throws ThemeDoesNotExistException
	 *             the theme does not exist exception
	 * @throws QCMDoesNotExistException
	 *             the QCM does not exist exception
	 */

	@RequestMapping(value = "/searchSupplierQCM", method = RequestMethod.GET)
	public ResponseEntity<List<QCMDto>> searchSupplierQCM(Authentication authentication,
			@RequestParam Map<String, String> requestParams)
			throws ThemeDoesNotExistException, QCMDoesNotExistException {

		Supplier supplier = (Supplier) getUserFromAuthentication(authentication);

		List<QCM> QcmList = new ArrayList<QCM>();
		String theme = requestParams.get("theme");
		String subTheme = requestParams.get("subTheme");

		if ((theme == null || theme.equals(""))  && (subTheme == null || subTheme.equals("")) ) {
			QcmList = qcmService.getSupplierQCM(supplier.getIdPlayer());
		} else if (theme != null && subTheme == null || subTheme.equals("") ){
			QcmList = qcmService.searchSupplierQCMByTheme(theme, supplier.getIdPlayer());
		}else if (subTheme != null){
			QcmList = qcmService.searchSupplierQCMBySubTheme(subTheme, supplier.getIdPlayer());
		}
		List<QCMDto> result = new ArrayList<>();
		for (QCM qcm : QcmList) {
			result.add(qcmDtoConverter.convertToDto(qcm));
		}

		return ResponseEntity.status(HttpStatus.OK).body(result);
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
