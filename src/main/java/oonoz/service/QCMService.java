package oonoz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.QCM;
import oonoz.domain.Question;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.QuestionDoesNotExistException;
import oonoz.exception.TooManyQuestionsException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.QCMManagerImpl;
import oonoz.manager.impl.QuestionManagerImpl;
import oonoz.util.CheckQCMInformation;

/**
 * The Class QCMService.
 */
@Service
public class QCMService {

	/** The QCM manager. */
	@Autowired
	private QCMManagerImpl QCMManager;

	/** The question manager. */
	@Autowired
	private QuestionManagerImpl questionManager;

	/** The check QCM information. */
	@Autowired
	private CheckQCMInformation checkQCMInformation;

	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public List<QCM> findAll() {
		return QCMManager.getAllQCMs();
	}

	/**
	 * Post QCM.
	 *
	 * @param qcm the qcm
	 * @return the qcm
	 * @throws WrongInformationException the wrong information exception
	 */
	public QCM postQCM(QCM qcm) throws WrongInformationException {

		checkQCMInformation.checkName(qcm.getName());
		checkQCMInformation.checkDescription(qcm.getDescription());
		checkQCMInformation.checkCategory(qcm.getCategory());

		return QCMManager.postQCM(qcm);
	}

	/**
	 * Post question.
	 *
	 * @param idQCM the id QCM
	 * @param question the question
	 * @return the question
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 * @throws TooManyQuestionsException the too many questions exception
	 */
	public Question postQuestion(long idQCM, Question question) throws QCMDoesNotExistException, TooManyQuestionsException {
		QCM qcm = QCMManager.findOne(idQCM);

		if (qcm != null) {
			if (qcm.getQuestions().size() < 20) {
				question.setIdQCM(qcm.getId());
				return questionManager.postQuestion(question);
			} else {
				throw new TooManyQuestionsException("Too many questions for QCM with id " + idQCM);
			}
		} else {
			throw new QCMDoesNotExistException("The QCM with id " + idQCM + " does not exist");
		}
	}

	/**
	 * Delete QCM.
	 *
	 * @param id the id
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 */
	public void deleteQCM(long id) throws QCMDoesNotExistException {
		QCM qcm = QCMManager.findOne(id);
		if (qcm != null) {
			QCMManager.delete(id);
		} else {
			throw new QCMDoesNotExistException("The QCM with id " + id + " does not exist");
		}
	}

	/**
	 * Gets the question.
	 *
	 * @param idQCM the id QCM
	 * @param idQuestion the id question
	 * @return the question
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 * @throws QuestionDoesNotExistException the question does not exist exception
	 */
	public Question getQuestion(long idQCM, long idQuestion) throws QCMDoesNotExistException, 
	QuestionDoesNotExistException {
		QCM qcm = QCMManager.findOne(idQCM);
		if (qcm != null) {
			Question question = questionManager.getQuestion(idQuestion);
			if (question != null) {
				return question;
			} else {
				throw new QuestionDoesNotExistException("The question with id " + idQuestion + " does not exist");
			}
		} else {
			throw new QCMDoesNotExistException("The QCM with id " + idQCM + " does not exist");
		}
	}
	
	public QCM getQCM(long id) {
		return QCMManager.findOne(id);
	}
}
