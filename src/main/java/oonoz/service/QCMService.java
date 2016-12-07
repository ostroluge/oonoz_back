package oonoz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.QCM;
import oonoz.domain.Question;
import oonoz.domain.SubTheme;
import oonoz.domain.Theme;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.QuestionDoesNotExistException;
import oonoz.exception.SubThemeAlreadyAddedException;
import oonoz.exception.SubThemeDoesNotExistException;
import oonoz.exception.ThemeDoesNotExistException;
import oonoz.exception.TooManyQuestionsException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.QCMManagerImpl;
import oonoz.manager.impl.QuestionManagerImpl;
import oonoz.manager.impl.SubThemeManagerImpl;
import oonoz.util.CheckQCMInformation;
import oonoz.util.CheckQuestionInformation;

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

	/** The sub theme manager. */
	@Autowired
	private SubThemeManagerImpl subThemeManager;

	/** The check QCM information. */
	@Autowired
	private CheckQCMInformation checkQCMInformation;
	
	@Autowired
	private CheckQuestionInformation checkQuestionInformation;

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
	 * @throws WrongInformationException 
	 */
	public Question postQuestion(long idQCM, Question question) throws QCMDoesNotExistException,
	TooManyQuestionsException, WrongInformationException {
		QCM qcm = QCMManager.findOne(idQCM);

		if (qcm != null) {
			if (qcm.getQuestions().size() < 20) {
				question.setIdQCM(qcm.getId());
				
				checkQuestionInformation.checkTitle(question.getTitle());
				checkQuestionInformation.checkAnswer(question.getAnswer());
				checkQuestionInformation.checkProposition1(question.getProposition1());
				checkQuestionInformation.checkProposition2(question.getProposition2());
				checkQuestionInformation.checkProposition3(question.getProposition3());
				
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
	 * Delete question.
	 *
	 * @param idQCM the id QCM
	 * @param idQuestion the id question
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 * @throws QuestionDoesNotExistException the question does not exist exception
	 */
	public void deleteQuestion(long idQCM, long idQuestion) throws QCMDoesNotExistException,
	QuestionDoesNotExistException {
		QCM qcm = QCMManager.findOne(idQCM);
		if (qcm != null) {
			Question question = questionManager.getQuestion(idQuestion);
			if (question != null) {
				questionManager.deleteQuestion(question.getId());
			} else {
				throw new QuestionDoesNotExistException("The question with id " + idQuestion + " does not exist");
			}
		} else {
			throw new QCMDoesNotExistException("The QCM with id " + idQCM + " does not exist");
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

	/**
	 * Gets the qcm.
	 *
	 * @param id the id
	 * @return the qcm
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 */
	public QCM getQCM(long id) throws QCMDoesNotExistException {
		QCM qcm = QCMManager.findOne(id);
		if (qcm != null) {
			return qcm;
		} else {
			throw new QCMDoesNotExistException("The QCM with id " + id + " does not exist");
		}
	}

	/**
	 * Adds the sub theme.
	 *
	 * @param idQCM the id QCM
	 * @param idSubTheme the id sub theme
	 * @return the qcm
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 * @throws SubThemeDoesNotExistException the sub theme does not exist exception
	 * @throws SubThemeAlreadyAddedException the sub theme already added exception
	 */
	public QCM addSubTheme(long idQCM, long idSubTheme) throws QCMDoesNotExistException,
	SubThemeDoesNotExistException, SubThemeAlreadyAddedException {
		QCM qcm = QCMManager.findOne(idQCM);
		if (qcm != null) {
			SubTheme subtheme = subThemeManager.findOne(idSubTheme);
			if (subtheme != null) {
				if (!qcm.getSubThemes().contains(subtheme)) {
					qcm.getSubThemes().add(subtheme);
					return QCMManager.save(qcm);
				} else {
					throw new SubThemeAlreadyAddedException("The subtheme is already linked to the qcm");
				}
			} else {
				throw new SubThemeDoesNotExistException("The subtheme with id " + idSubTheme + " does not exist");
			}
		} else {
			throw new QCMDoesNotExistException("The QCM with id " + idQCM + " does not exist");
		}
	}

	/**
	 * Delete sub theme.
	 *
	 * @param idQCM the id QCM
	 * @param idSubTheme the id sub theme
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 * @throws SubThemeDoesNotExistException the sub theme does not exist exception
	 */
	public void deleteSubTheme(long idQCM, long idSubTheme) throws QCMDoesNotExistException,
	SubThemeDoesNotExistException {
		QCM qcm = QCMManager.findOne(idQCM);
		if (qcm != null) {
			SubTheme subtheme = subThemeManager.findOne(idSubTheme);
			if (subtheme != null) {
				if (qcm.getSubThemes().contains(subtheme)) {
					qcm.getSubThemes().remove(subtheme);
					QCMManager.save(qcm);
				}
			} else {
				throw new SubThemeDoesNotExistException("The subtheme with id " + idSubTheme + " does not exist");
			}
		} else {
			throw new QCMDoesNotExistException("The QCM with id " + idQCM + " does not exist");
		}
	}
	
	/**
	 * Update QCM.
	 *
	 * @param id the id
	 * @param qcm the qcm
	 * @return the qcm
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 */
	public QCM updateQCM(long id, QCM qcm) throws QCMDoesNotExistException {
		return QCMManager.update(id, qcm);
	}
}
