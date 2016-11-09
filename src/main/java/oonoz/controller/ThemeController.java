package oonoz.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import oonoz.domain.Theme;
import oonoz.dto.converter.ThemeDtoConverter;
import oonoz.dto.model.ThemeDto;
import oonoz.exception.ThemeAlreadyExistException;
import oonoz.exception.ThemeDoesntExistException;
import oonoz.exception.WrongInformationException;
import oonoz.service.ThemeService;
import oonoz.util.StringResponse;

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
	
	/**
	 * Post theme.
	 *
	 * @param theme the theme
	 * @return the response entity
	 * @throws WrongInformationException the wrong information exception
	 * @throws ThemeAlreadyExistException the theme already exist exception
	 */
	@RequestMapping(value="/themes", method = RequestMethod.POST)
	public ResponseEntity<Theme> postTheme(@RequestBody ThemeDto theme) throws WrongInformationException, ThemeAlreadyExistException {
		Theme themeToPost = themeDtoConverter.convertToEntity(theme);
		
		Theme result = themeService.postTheme(themeToPost);

		if (result == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(result);
	}
	
	/**
	 * Removes the theme.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(value = "/themes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<StringResponse> removeTheme(@PathVariable("id") Long id) {
		StringResponse response = new StringResponse();
		
		try {
			themeService.removeTheme(id);
		} catch (ThemeDoesntExistException e) {
			response.setResponse("The theme does not exist");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(response);
		}
		
		response.setResponse("Deletion successful");
		return ResponseEntity.status(HttpStatus.OK)
				.body(response);
	}
}
