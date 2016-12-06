package oonoz.dto.converter;

import org.springframework.stereotype.Component;

import oonoz.domain.QCM;
import oonoz.dto.model.QCMDto;

/**
 * The Class QCMDtoConverter.
 */
@Component
public class QCMDtoConverter {

	/**
	 * Convert to entity.
	 *
	 * @param qcmDto the qcm dto
	 * @return the qcm
	 */
	public QCM convertToEntity(QCMDto qcmDto) {
		QCM qcm = new QCM();

		qcm.setId(qcmDto.getId());
		qcm.setIdTheme(qcmDto.getIdTheme());
		qcm.setIdSupplier(qcmDto.getIdSupplier());
		qcm.setSubThemes(qcmDto.getSubThemes());
		qcm.setName(qcmDto.getName());
		qcm.setDescription(qcmDto.getDescription());
		qcm.setValidated(qcmDto.isValidated());
		qcm.setFree(qcmDto.isFree());
		qcm.setIcon(qcmDto.getIcon());
		qcm.setPrizeName(qcmDto.getPrizeName());
		qcm.setPrizeDescription(qcmDto.getPrizeDescription());
		qcm.setMinimalScore(qcmDto.getMinimalScore());
		qcm.setCategory(qcmDto.getCategory());
		qcm.setQuestions(qcmDto.getQuestions());

		return qcm;
	}

	/**
	 * Convert to dto.
	 *
	 * @param qcm the qcm
	 * @return the QCM dto
	 */
	public QCMDto convertToDto(QCM qcm) {
		QCMDto qcmDto = new QCMDto();

		qcmDto.setId(qcm.getId());
		qcmDto.setIdTheme(qcm.getIdTheme());
		qcmDto.setThemeName(qcm.getTheme().getLabel());
		qcmDto.setIdSupplier(qcm.getIdSupplier());
		qcmDto.setSupplierName(qcm.getSupplier().getLastName());
		qcmDto.setSubThemes(qcm.getSubThemes());
		qcmDto.setName(qcm.getName());
		qcmDto.setDescription(qcm.getDescription());
		qcmDto.setValidated(qcm.isValidated());
		qcmDto.setFree(qcm.isFree());
		qcmDto.setIcon(qcm.getIcon());
		qcmDto.setPrizeName(qcm.getPrizeName());
		qcmDto.setPrizeDescription(qcm.getPrizeDescription());
		qcmDto.setMinimalScore(qcm.getMinimalScore());
		qcmDto.setCategory(qcm.getCategory());
		qcmDto.setQuestions(qcm.getQuestions());

		return qcmDto;
	}
}
