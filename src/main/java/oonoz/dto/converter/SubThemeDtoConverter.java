package oonoz.dto.converter;

import org.springframework.stereotype.Component;

import oonoz.domain.SubTheme;
import oonoz.dto.model.SubThemeDto;

/**
 * The Class SubThemeDtoConverter.
 */
@Component
public class SubThemeDtoConverter {

	/**
	 * Convert to entity.
	 *
	 * @param subthemeDto the subtheme dto
	 * @return the sub theme
	 */
	public SubTheme convertToEntity(SubThemeDto subthemeDto) {
		SubTheme subTheme = new SubTheme();
		subTheme.setId(subthemeDto.getId());
		subTheme.setIdTheme(subthemeDto.getIdTheme());
		subTheme.setLabel(subthemeDto.getLabel());
		subTheme.setDescription(subthemeDto.getDescription());
		subTheme.setIconUrl(subthemeDto.getIconUrl());
		return subTheme;
	}

	/**
	 * Convert to dto.
	 *
	 * @param subtheme the subtheme
	 * @return the sub theme dto
	 */
	public SubThemeDto convertToDto(SubTheme subtheme) {
		SubThemeDto subthemeDto = new SubThemeDto();
		subthemeDto.setId(subtheme.getId());
		subthemeDto.setIdTheme(subtheme.getIdTheme());
		subthemeDto.setLabel(subtheme.getLabel());
		subthemeDto.setDescription(subtheme.getDescription());
		subthemeDto.setIconUrl(subtheme.getIconUrl());
		return subthemeDto;
	}
}
