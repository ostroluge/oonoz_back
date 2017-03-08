package oonoz.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.Question;
import oonoz.exception.QuestionDoesNotExistException;
import oonoz.manager.QuestionManager;
import oonoz.repository.QuestionRepository;

/**
 * The Class QuestionManagerImpl.
 */
@Component(value = "questionManager")
public class QuestionManagerImpl implements QuestionManager {

	/** The question repository. */
	@Resource
	private QuestionRepository questionRepository;
	
	/**
	 * Post question.
	 *
	 * @param question the question
	 * @return the question
	 */
	public Question postQuestion(Question question) {
		return questionRepository.save(question);
	}

	/**
	 * Gets the question.
	 *
	 * @param idQuestion the id question
	 * @return the question
	 */
	public Question getQuestion(long idQuestion) {
		return questionRepository.findOne(idQuestion);
	}
	
	/**
	 * Find by question number.
	 *
	 * @param idQCM the id QCM
	 * @param questionNumber the question number
	 * @return the question
	 */
	public Question findByQuestionNumber(long idQCM,int questionNumber){
		return questionRepository.findByQuestionNumber(idQCM, questionNumber);
	}
	
	/**
	 * Delete question.
	 *
	 * @param idQuestion the id question
	 */
	public void deleteQuestion(long idQuestion) {
		questionRepository.delete(idQuestion);
	}
	
	/**
	 * Update the question.
	 *
	 * @param id the id
	 * @param question the question
	 * @return the question
	 * @throws QuestionDoesNotExistException the question does not exist exception
	 */
	public Question update(long id, Question question) throws QuestionDoesNotExistException {
		Question existingQuestion = questionRepository.findOne(id);
		if (existingQuestion != null) {
			question.setId(existingQuestion.getId());
			return questionRepository.save(question);
		} else {
			throw new QuestionDoesNotExistException("The question with id " + id + " does not exist");
		}
	}
}
