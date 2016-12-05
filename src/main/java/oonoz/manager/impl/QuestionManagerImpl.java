package oonoz.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.QCM;
import oonoz.domain.Question;
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
	 * Delete question.
	 *
	 * @param idQuestion the id question
	 */
	public void deleteQuestion(long idQuestion) {
		questionRepository.delete(idQuestion);
	}
}
