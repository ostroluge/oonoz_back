package oonoz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.Theme;
import oonoz.exception.ThemeAlreadyExistException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.ThemeManagerImpl;
import oonoz.util.CheckThemeInformation;

/**
 * The Class ThemeService.
 */
@Service
public class ThemeService {

	/** The theme manager. */
	@Autowired
	private ThemeManagerImpl themeManager;
	
	/** The check theme information. */
	@Autowired
	private CheckThemeInformation checkThemeInformation;
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Theme> findAll() {
		return themeManager.findAll();
	}

	/**
	 * Post theme.
	 *
	 * @param theme the theme
	 * @return the theme
	 * @throws WrongInformationException the wrong information exception
	 * @throws ThemeAlreadyExistException the theme already exist exception
	 */
	public Theme postTheme(Theme theme) throws WrongInformationException, ThemeAlreadyExistException {
		
		checkThemeInformation.checkLabel(theme.getLabel());
		checkThemeInformation.checkDescription(theme.getDescription());
	
		return themeManager.create(theme);
	}
}
