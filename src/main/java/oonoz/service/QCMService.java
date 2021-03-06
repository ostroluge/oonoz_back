package oonoz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.QCM;
import oonoz.domain.Question;
import oonoz.domain.SubTheme;
import oonoz.domain.Theme;
import oonoz.exception.QCMAlreadyExistException;
import oonoz.exception.QCMCreationException;
import oonoz.exception.QCMDoesNotExistException;
import oonoz.exception.QCMInvalidationException;
import oonoz.exception.QCMValidationException;
import oonoz.exception.QuestionDoesNotExistException;
import oonoz.exception.SubThemeAlreadyAddedException;
import oonoz.exception.SubThemeDoesNotExistException;
import oonoz.exception.ThemeDoesNotExistException;
import oonoz.exception.TooManyQuestionsException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.QCMManagerImpl;
import oonoz.manager.impl.QuestionManagerImpl;
import oonoz.manager.impl.SubThemeManagerImpl;
import oonoz.manager.impl.ThemeManagerImpl;
import oonoz.util.CheckQCMInformation;
import oonoz.util.CheckQuestionInformation;
import oonoz.util.QCMFilteredSearch;

/**
 * The Class QCMService.
 */
@Service
public class QCMService {

	/** The QCM manager. */
	@Autowired
	private QCMManagerImpl qcmManager;

	/** The question manager. */
	@Autowired
	private QuestionManagerImpl questionManager;
	
	/** The theme manager. */
	@Autowired
	private ThemeManagerImpl themeManager;

	/** The sub theme manager. */
	@Autowired
	private SubThemeManagerImpl subThemeManager;

	/** The check QCM information. */
	@Autowired
	private CheckQCMInformation checkQCMInformation;
	
	/** The check question information. */
	@Autowired
	private CheckQuestionInformation checkQuestionInformation;

	/**
	 * Find all.
	 *
	 * @return the iterable
	 */
	public List<QCM> findAll() {
		return qcmManager.getAllQCMs();
	}

	/**
	 * Post QCM.
	 *
	 * @param qcm the qcm
	 * @return the qcm
	 * @throws WrongInformationException the wrong information exception
	 * @throws QCMCreationException the QCM creation exception
	 * @throws QCMAlreadyExistException the QCM already exist exception
	 * @throws ThemeDoesNotExistException the theme does not exist exception
	 */
	public QCM postQCM(QCM qcm) throws WrongInformationException, QCMCreationException, QCMAlreadyExistException, ThemeDoesNotExistException {
		
		checkQCMInformation.checkName(qcm.getName());
		checkQCMInformation.checkDescription(qcm.getDescription());
		checkQCMInformation.checkCategory(qcm.getCategory());
		checkQCMInformation.checkIdTheme(qcm.getIdTheme());
		checkQCMInformation.checkMinimalScore(qcm.getMinimalScore());
		if(!qcm.isFree()){
			checkQCMInformation.checkPrice(qcm.getPrice());
		}
		if(qcm.getPrizeName()!=null){
			checkQCMInformation.checkPrizeName(qcm.getPrizeName());
			checkQCMInformation.checkPrizeDescription(qcm.getPrizeDescription());
		} 
		qcm.setValidated(false);
		qcm.setIsComplete(false);
		return qcmManager.postQCM(qcm);
	}

	/**
	 * Post question.
	 *
	 * @param idQCM the id QCM
	 * @param question the question
	 * @return the question
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 * @throws TooManyQuestionsException the too many questions exception
	 * @throws WrongInformationException the wrong information exception
	 */
	public Question postQuestion(long idQCM, Question question) throws QCMDoesNotExistException,
	TooManyQuestionsException, WrongInformationException {
		QCM qcm = qcmManager.findOne(idQCM);

		if (qcm != null) {
			if (qcm.getQuestions().size() < 20) {
				question.setIdQCM(qcm.getId());
				
				checkQuestionInformation.checkTitle(question.getTitle());
				checkQuestionInformation.checkAnswer(question.getAnswer());
				checkQuestionInformation.checkProposition1(question.getProposition1());
				checkQuestionInformation.checkProposition2(question.getProposition2());
				checkQuestionInformation.checkProposition3(question.getProposition3());
				
				Question postedQuestion = questionManager.postQuestion(question, qcm);
				int questionNumber = qcm.getQuestions().size();
				
				if (questionNumber == 19) {
					qcm.setIsComplete(Boolean.TRUE);
				} else {
					qcm.setIsComplete(Boolean.FALSE);
				}
				
				qcmManager.save(qcm);
				
				return postedQuestion;
			} else {
				throw new TooManyQuestionsException("Too many questions for QCM with id " + idQCM);
			}
		} else {
			throw new QCMDoesNotExistException(idQCM);
		}
	}

	/**
	 * Delete QCM.
	 *
	 * @param id the id
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 */
	public void deleteQCM(long id) throws QCMDoesNotExistException {
		QCM qcm = qcmManager.findOne(id);
		if (qcm != null) {
			qcmManager.delete(id);
		} else {
			throw new QCMDoesNotExistException(id);
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
		QCM qcm = qcmManager.findOne(idQCM);
		if (qcm != null) {
			Question question = questionManager.getQuestion(idQuestion);
			if (question != null) {
				questionManager.deleteQuestion(question.getId());
				int questionNumber = qcm.getQuestions().size();
				if (questionNumber < 20) {
					qcm.setIsComplete(Boolean.FALSE);
					qcmManager.save(qcm);
				}
			} else {
				throw new QuestionDoesNotExistException(idQuestion);
			}
		} else {
			throw new QCMDoesNotExistException(idQCM);
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
		QCM qcm = qcmManager.findOne(idQCM);
		if (qcm != null) {
			Question question = questionManager.getQuestion(idQuestion);
			if (question != null) {
				return question;
			} else {
				throw new QuestionDoesNotExistException(idQuestion);
			}
		} else {
			throw new QCMDoesNotExistException(idQCM);
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
		QCM qcm = qcmManager.findOne(id);
		if (qcm != null) {
			return qcm;
		} else {
			throw new QCMDoesNotExistException(id);
		}
	}
	
	/**
	 * Gets the qcm.
	 *
	 * @param id the id
	 * @return the qcm
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 */
	public QCM getValidatedQCM(long id) throws QCMDoesNotExistException {
		return qcmManager.findByIdAndIsValidatedTrueAndIsCompleteTrue(id);		
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
		QCM qcm = qcmManager.findOne(idQCM);
		if (qcm != null) {
			SubTheme subtheme = subThemeManager.findOne(idSubTheme);
			if (subtheme != null) {
				if (!qcm.getSubThemes().contains(subtheme)) {
					qcm.getSubThemes().add(subtheme);
					return qcmManager.save(qcm);
				} else {
					throw new SubThemeAlreadyAddedException("The subtheme is already linked to the qcm");
				}
			} else {
				throw new SubThemeDoesNotExistException(idSubTheme);
			}
		} else {
			throw new QCMDoesNotExistException(idQCM);
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
		QCM qcm = qcmManager.findOne(idQCM);
		if (qcm != null) {
			SubTheme subtheme = subThemeManager.findOne(idSubTheme);
			if (subtheme != null) {
				if (qcm.getSubThemes().contains(subtheme)) {
					qcm.getSubThemes().remove(subtheme);
					qcmManager.save(qcm);
				}
			} else {
				throw new SubThemeDoesNotExistException(idSubTheme);
			}
		} else {
			throw new QCMDoesNotExistException(idQCM);
		}
	}
	
	/**
	 * Update QCM.
	 *
	 * @param id the id
	 * @param qcm the qcm
	 * @return the qcm
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 * @throws QCMCreationException the QCM creation exception
	 * @throws WrongInformationException the wrong information exception
	 */

	public QCM updateQCM(long id, QCM qcm) throws QCMDoesNotExistException, QCMCreationException, WrongInformationException {
		checkQCMInformation.checkName(qcm.getName());
		checkQCMInformation.checkDescription(qcm.getDescription());
		checkQCMInformation.checkCategory(qcm.getCategory());
		return qcmManager.update(id, qcm);
	}
	
	/**
	 * Update question.
	 *
	 * @param idQuestion the id question
	 * @param question the question
	 * @return the question
	 * @throws QuestionDoesNotExistException the question does not exist exception
	 */
	public Question updateQuestion(long idQuestion, Question question) throws QuestionDoesNotExistException {
		return questionManager.update(idQuestion, question);
	}
	
	/**
	 * Gets the question by number.
	 *
	 * @param idQCM the id QCM
	 * @param questionNumber the question number
	 * @return the question by number
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 */
	public Question getQuestionByNumber(long idQCM,int questionNumber) throws QCMDoesNotExistException{
		QCM qcm=qcmManager.findOne(idQCM);
		return questionManager.findByQuestionNumber(qcm.getId(),questionNumber);
	}
	
	/**
	 * Gets the supplier QCM.
	 *
	 * @param idSupplier the id supplier
	 * @return the supplier QCM
	 */
	public List<QCM> getSupplierQCM(long idSupplier){
		return qcmManager.findSupplierQuestions(idSupplier);
	}


	/**
	 * Search supplier QCM.
	 *
	 * @param subTheme the sub theme
	 * @param idSupplier the id supplier
	 * @return the list
	 */
	public List<QCM> searchSupplierQCMBySubTheme(String subTheme, long idSupplier) {
		
		List<SubTheme> subThemes = subThemeManager.findByLabelIgnoreCaseStartingWith(subTheme);
		List<QCM> qcmList = getSupplierQCM(idSupplier);
		List<QCM> result = new ArrayList<>();
		
		for (QCM q : qcmList){
			for (SubTheme sb : subThemes){
				if (q.getIdTheme() == sb.getIdTheme()){
					result.add(q);
				}
			}
		}
		return result;
		
	}
	
	/**
	 * Search supplier QCM.
	 *
	 * @param themeLabel the theme label
	 * @param idSupplier the id supplier
	 * @return the list
	 * @throws ThemeDoesNotExistException the theme does not exist exception
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 */
	public List<QCM>searchSupplierQCMByTheme(String themeLabel,long idSupplier) throws ThemeDoesNotExistException, QCMDoesNotExistException{
		
		List<Theme>  themes = themeManager.findByLabel(themeLabel);
		List<QCM> listQCM = new ArrayList<>();
		
		if (themes.size() != 0){
			for (Theme t : themes ){
				listQCM.addAll(qcmManager.findByIdThemeAndIdSupplier(t.getIdTheme(),idSupplier));
			}
		}
		
		return listQCM;
		
	}
	


	/**
	 * Filtered search.
	 *
	 * @param filteredSearch the filtered search
	 * @return the list
	 */
	public List<QCM>filteredSearch(QCMFilteredSearch filteredSearch){
		//TODO check QCMFilteredSearch
		return qcmManager.findsWithFilter(filteredSearch);
	}
	
	
	
	/**
	 * Validate QCM.
	 *
	 * @param idQCM the id QCM
	 * @return true, if successful
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 * @throws QCMValidationException the QCM validation exception
	 */
	public boolean validateQCM(long idQCM) throws QCMDoesNotExistException, QCMValidationException {
		QCM qcm = qcmManager.findOne(idQCM);
		if (qcm != null) {
			QCM updatedQCM = qcmManager.validateQCM(idQCM);
			return updatedQCM.isValidated();
		} else {
			throw new QCMDoesNotExistException("The QCM with id " + idQCM + " does not exist");
		}
	}

	/**
	 * Invalidate QCM.
	 *
	 * @param idQCM the id QCM
	 * @return true, if successful
	 * @throws QCMDoesNotExistException the QCM does not exist exception
	 * @throws QCMInvalidationException the QCM invalidation exception
	 */
	public boolean invalidateQCM(long idQCM) throws QCMDoesNotExistException, QCMInvalidationException {
		QCM qcm = qcmManager.findOne(idQCM);
		if (qcm != null) {
			QCM updatedQCM = qcmManager.invalidateQCM(idQCM);
			return !updatedQCM.isValidated();
		} else {
			throw new QCMDoesNotExistException("The QCM with id " + idQCM + " does not exist");
		}
	}
}
