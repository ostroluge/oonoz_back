package oonoz.dto.converter;

import org.springframework.stereotype.Component;

import oonoz.domain.Question;
import oonoz.dto.model.QuestionDto;

/**
 * The Class QuestionDtoConverter.
 */
@Component
public class QuestionDtoConverter {

	/**
	 * Convert to entity.
	 *
	 * @param questionDto the question dto
	 * @return the question
	 */
	public Question convertToEntity(QuestionDto questionDto) {
		Question question = new Question();
		
		question.setId(questionDto.getId());
		question.setIdQCM(questionDto.getIdQCM());
		question.setTitle(questionDto.getTitle());
		question.setMediaType(questionDto.getMediaType());
		question.setMedia(questionDto.getMedia());
		question.setAnswer(questionDto.getAnswer());
		question.setProposition1(questionDto.getProposition1());
		question.setProposition2(questionDto.getProposition2());
		question.setProposition3(questionDto.getProposition3());
		question.setQuestionNumber(questionDto.getQuestionNumber());
		
		return question;
	}

	/**
	 * Convert to dto.
	 *
	 * @param question the question
	 * @return the question dto
	 */
	public QuestionDto convertToDto(Question question) {
		QuestionDto questionDto = new QuestionDto();
		
		questionDto.setId(question.getId());
		questionDto.setIdQCM(question.getIdQCM());
		questionDto.setTitle(question.getTitle());
		questionDto.setMediaType(question.getMediaType());
		questionDto.setMedia(question.getMedia());
		questionDto.setAnswer(question.getAnswer());
		questionDto.setProposition1(question.getProposition1());
		questionDto.setProposition2(question.getProposition2());
		questionDto.setProposition3(question.getProposition3());
		questionDto.setQuestionNumber(question.getQuestionNumber());
		
		return questionDto;
	}
}
