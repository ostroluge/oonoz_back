package oonoz.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import oonoz.domain.QCM;
import oonoz.domain.Question;
import oonoz.dto.model.QCMEncryptDto;
import oonoz.dto.model.QuestionEncryptDto;

@Component
public class QCMEncryptDtoConverter {
	
	
	private QuestionEncryptDtoConverter questionConvert;
	
	/**
	 * Convert to dto.
	 *
	 * @param qcm the qcm
	 * @return the QCM dto
	 */
	public QCMEncryptDto convertToDto(QCM qcm) {
		QCMEncryptDto qcmDto = new QCMEncryptDto();
		questionConvert= new QuestionEncryptDtoConverter();
		qcmDto.setId(qcm.getId());
		qcmDto.setIdTheme(qcm.getIdTheme());
		qcmDto.setThemeName(qcm.getTheme().getLabel());
		qcmDto.setIdSupplier(qcm.getIdSupplier());
		qcmDto.setSupplierName(qcm.getSupplier().getLastName());
		//qcmDto.setSubThemes(qcm.getSubThemes());
		qcmDto.setName(qcm.getName());
		qcmDto.setDescription(qcm.getDescription());
		qcmDto.setIcon(qcm.getIcon());
		qcmDto.setPrizeName(qcm.getPrizeName());
		qcmDto.setPrizeDescription(qcm.getPrizeDescription());
		qcmDto.setMinimalScore(qcm.getMinimalScore());
		qcmDto.setCategory(qcm.getCategory());
		
		List<QuestionEncryptDto> questions= new ArrayList<QuestionEncryptDto>();
		for(Question question: qcm.getQuestions()){
			questions.add(questionConvert.convertToDto(question));
		}
		qcmDto.setQuestions(questions);
		return qcmDto;
	}

}
