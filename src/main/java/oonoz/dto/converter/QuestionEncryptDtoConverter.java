package oonoz.dto.converter;

import oonoz.domain.Question;
import oonoz.dto.model.QuestionEncryptDto;
import oonoz.util.CheckUserInformation;

public class QuestionEncryptDtoConverter {
	
	private CheckUserInformation checkUserInformation;
	
	public QuestionEncryptDto convertToDto(Question question) {
		checkUserInformation= new CheckUserInformation();
		QuestionEncryptDto qpd= new QuestionEncryptDto();
		qpd.setMedia(question.getMedia());
		qpd.setMediaType(question.getMediaType());
		qpd.setQuestionNumber(question.getQuestionNumber());
		qpd.setTitle(question.getTitle());
		qpd.setId(question.getId());
		String encryptAnswer=checkUserInformation.hashPassword(question.getAnswer());
		qpd.setEncryptAnswer(encryptAnswer);
		qpd.setProposition1(question.getProposition1());
		qpd.setProposition2(question.getProposition2());
		qpd.setProposition3(question.getProposition3());
		qpd.setProposition4(question.getAnswer());
		qpd.setTime(question.getTime());
		
		return qpd;
		
	}

}
