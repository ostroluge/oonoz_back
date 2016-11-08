package oonoz.dto.converter;

import org.springframework.stereotype.Component;

import oonoz.domain.Theme;
import oonoz.dto.model.ThemeDto;

/**
 * The Class ThemeDtoConverter.
 */
@Component
public class ThemeDtoConverter {

	/**
	 * Convert to entity.
	 *
	 * @param themeDto the theme dto
	 * @return the theme
	 */
	public Theme convertToEntity(ThemeDto themeDto) {
		Theme theme = new Theme();
		theme.setLabel(themeDto.getLabel());
		theme.setDescription(themeDto.getDescription());
		theme.setIconUrl(themeDto.getIconUrl());
		return theme;
	}

	/**
	 * Convert to dto.
	 *
	 * @param theme the theme
	 * @return the theme dto
	 */
	public ThemeDto convertToDto(Theme theme) {
		ThemeDto themeDto = new ThemeDto();
		themeDto.setLabel(theme.getLabel());
		themeDto.setDescription(theme.getDescription());
		themeDto.setIconUrl(theme.getIconUrl());
		return themeDto;
	}
}
