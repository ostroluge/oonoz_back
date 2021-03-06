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

import oonoz.domain.SubTheme;
import oonoz.domain.Theme;
import oonoz.dto.converter.SubThemeDtoConverter;
import oonoz.dto.converter.ThemeDtoConverter;
import oonoz.dto.model.SubThemeDto;
import oonoz.dto.model.ThemeDto;
import oonoz.exception.SubThemeDoesNotExistException;
import oonoz.exception.ThemeAlreadyExistException;
import oonoz.exception.ThemeDoesNotExistException;
import oonoz.exception.WrongInformationException;
import oonoz.service.SubThemeService;
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

	/** The sub theme service. */
	@Autowired
	SubThemeService subThemeService;

	/** The theme dto converter. */
	@Autowired
	private ThemeDtoConverter themeDtoConverter;

	/** The sub theme dto converter. */
	@Autowired
	private SubThemeDtoConverter subThemeDtoConverter;

	/**
	 * Gets the all themes.
	 *
	 * @return the all themes
	 */
	@RequestMapping(value = "/themes", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Theme>> getAllThemes() {
		Iterable<Theme> themes = themeService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(themes);
	}

	/**
	 * Get all validated themes.
	 *
	 * @return all validated themes
	 */
	@RequestMapping(value = "/validatedThemes", method = RequestMethod.GET)
	public ResponseEntity<List<Theme>> getAllValidatedThemes() {
		List<Theme> themes = themeService.findValidated();
		return ResponseEntity.status(HttpStatus.OK).body(themes);
	}

	/**
	 * Gets the theme by id.
	 *
	 * @param id
	 *            the id
	 * @return the theme by id
	 */
	@RequestMapping(value = "/themes/{id}", method = RequestMethod.GET)
	public ResponseEntity<Theme> getThemeById(@PathVariable("id") long id) {
		Theme theme;
		try {
			theme = themeService.findOne(id);
		} catch (ThemeDoesNotExistException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(theme);

	}

	/**
	 * Post theme.
	 *
	 * @param theme
	 *            the theme
	 * @return the response entity
	 * @throws WrongInformationException
	 *             the wrong information exception
	 * @throws ThemeAlreadyExistException
	 *             the theme already exist exception
	 */
	@RequestMapping(value = "/themes", method = RequestMethod.POST)
	public ResponseEntity<Theme> postTheme(@RequestBody ThemeDto theme)
			throws WrongInformationException, ThemeAlreadyExistException {
		Theme themeToPost = themeDtoConverter.convertToEntity(theme);

		Theme result = themeService.postTheme(themeToPost);

		if (result == null) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * Removes the theme.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@RequestMapping(value = "/themes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<StringResponse> removeTheme(@PathVariable("id") Long id) {
		StringResponse response = new StringResponse();

		try {
			themeService.removeTheme(id);
		} catch (ThemeDoesNotExistException e) {
			logger.error(e.getMessage(), e);
			response.setResponse("The theme does not exist");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
		}

		response.setResponse("Deletion successful");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Update theme.
	 *
	 * @param id the id
	 * @param theme the theme
	 * @return the updated theme
	 */
	@RequestMapping(value = "/themes/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Theme> updateTheme(@PathVariable("id") long id, @RequestBody ThemeDto theme) {
		Theme themeToUpdate = themeDtoConverter.convertToEntity(theme);

		if (themeToUpdate != null) {
			try {
				Theme result = themeService.updateTheme(id, themeToUpdate);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} catch (ThemeDoesNotExistException e) {
				logger.error(e.getMessage(), e);
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * Gets all sub theme by theme id.
	 *
	 * @param idTheme
	 *            the id theme
	 * @param idSubTheme
	 *            the id sub theme
	 * @return the sub theme by id
	 */
	@RequestMapping(value = "/themes/{idTheme}/subthemes", method = RequestMethod.GET)
	public ResponseEntity<List<SubTheme>> getAllSubThemeByThemeId(@PathVariable("idTheme") long idTheme) {
		List<SubTheme> subThemes = subThemeService.getAllSubThemesFromTheme(idTheme);
		if (subThemes != null) {
			return ResponseEntity.status(HttpStatus.OK).body(subThemes);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Post sub theme.
	 *
	 * @param idTheme
	 *            the id theme
	 * @param subTheme
	 *            the sub theme
	 * @return the response entity
	 */
	@RequestMapping(value = "/themes/{id}/subthemes", method = RequestMethod.POST)
	public ResponseEntity<SubTheme> postSubTheme(@PathVariable("id") long idTheme, @RequestBody SubThemeDto subTheme) {
		SubTheme subThemeToPost = subThemeDtoConverter.convertToEntity(subTheme);

		if (subThemeToPost != null) {
			try {
				SubTheme result = subThemeService.postSubTheme(idTheme, subThemeToPost);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} catch (ThemeDoesNotExistException e) {
				logger.error(e.getMessage(), e);
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body(null);
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * Gets the sub theme by id.
	 *
	 * @param idTheme
	 *            the id theme
	 * @param idSubTheme
	 *            the id sub theme
	 * @return the sub theme by id
	 */
	@RequestMapping(value = "/themes/{idTheme}/subthemes/{idSubTheme}", method = RequestMethod.GET)
	public ResponseEntity<SubTheme> getSubThemeById(@PathVariable("idTheme") long idTheme,
			@PathVariable("idSubTheme") long idSubTheme) {
		SubTheme subTheme = subThemeService.findOne(idSubTheme);
		if (subTheme != null) {
			return ResponseEntity.status(HttpStatus.OK).body(subTheme);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/**
	 * Update sub theme.
	 *
	 * @param idTheme
	 *            the id theme
	 * @param idSubTheme
	 *            the id sub theme
	 * @param subTheme
	 *            the sub theme
	 * @return the response entity
	 */
	@RequestMapping(value = "/themes/{idTheme}/subthemes/{idSubTheme}", method = RequestMethod.PUT)
	public ResponseEntity<SubTheme> updateSubTheme(@PathVariable("idTheme") long idTheme,
			@PathVariable("idSubTheme") long idSubTheme, @RequestBody SubThemeDto subTheme) {
		SubTheme subThemeToUpdate = subThemeDtoConverter.convertToEntity(subTheme);

		if (subThemeToUpdate != null) {
			try {
				SubTheme result = subThemeService.updateSubTheme(idSubTheme, subThemeToUpdate);
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} catch (SubThemeDoesNotExistException e) {
				logger.error(e.getMessage(), e);
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body(null);
			}
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * Delete sub theme.
	 *
	 * @param idTheme
	 *            the id theme
	 * @param idSubTheme
	 *            the id sub theme
	 * @return the response entity
	 */
	@RequestMapping(value = "/themes/{idTheme}/subthemes/{idSubTheme}", method = RequestMethod.DELETE)
	public ResponseEntity<StringResponse> deleteSubTheme(@PathVariable("idTheme") long idTheme,
			@PathVariable("idSubTheme") long idSubTheme) {
		StringResponse response = new StringResponse();

		try {
			subThemeService.removeSubTheme(idSubTheme);
		} catch (SubThemeDoesNotExistException e) {
			logger.error(e.getMessage(), e);
			response.setResponse("The subtheme does not exist");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
		}

		response.setResponse("Deletion successful");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * Validate the theme.
	 *
	 * @param idTheme
	 *            the id theme
	 * @return the response entity
	 */
	@RequestMapping(value = "/themes/{idTheme}/validation", method = RequestMethod.PUT)
	public ResponseEntity<Theme> validateTheme(@PathVariable("idTheme") long idTheme) {
		try {
			Theme result = themeService.validateTheme(idTheme);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (ThemeDoesNotExistException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(null);
		}
	}

	/**
	 * Validate sub theme.
	 *
	 * @param idTheme
	 *            the id theme
	 * @param idSubTheme
	 *            the id sub theme
	 * @return the response entity
	 */
	@RequestMapping(value = "/themes/{idTheme}/subthemes/{idSubTheme}/validation", method = RequestMethod.PUT)
	public ResponseEntity<SubTheme> validateSubTheme(@PathVariable("idTheme") long idTheme,
			@PathVariable("idSubTheme") long idSubTheme) {
		try {
			SubTheme result = subThemeService.validateSubTheme(idSubTheme);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (SubThemeDoesNotExistException e) {
			logger.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(null);
		}

	}

}
