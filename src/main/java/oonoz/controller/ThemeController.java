package oonoz.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.Theme;
import oonoz.dto.converter.ThemeDtoConverter;
import oonoz.dto.model.ThemeDto;
import oonoz.service.ThemeService;

/**
 * The Class ThemeController.
 */
@RestController
public class ThemeController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ThemeController.class);

	/** The theme service. */
	@Autowired
	ThemeService themeService;
	
	/** The theme dto converter. */
	@Autowired
	private ThemeDtoConverter themeDtoConverter;
	
	/**
	 * Gets the all themes.
	 *
	 * @return the all themes
	 */
	@RequestMapping(value="/themes", method = RequestMethod.GET)
	public ResponseEntity<List<Theme>> getAllThemes() {
		List<Theme> themes = themeService.findAll();
		return ResponseEntity.status(HttpStatus.OK)
				.body(themes);
	}
	
	@RequestMapping(value="/themes", method = RequestMethod.POST)
	public ResponseEntity<Theme> postTheme(@RequestBody ThemeDto theme) {
		Theme themeToPost = themeDtoConverter.convertToEntity(theme);
		
		Theme result = themeService.postTheme(themeToPost);

		if (result == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(result);
	}
}
