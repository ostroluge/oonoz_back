package oonoz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.SubTheme;
import oonoz.domain.Theme;
import oonoz.exception.ThemeDoesntExistException;
import oonoz.manager.impl.SubThemeManagerImpl;
import oonoz.manager.impl.ThemeManagerImpl;

/**
 * The Class SubThemeService.
 */
@Service
public class SubThemeService {

	/** The sub theme manager. */
	@Autowired
	private SubThemeManagerImpl subThemeManager;

	/** The theme manager. */
	@Autowired
	private ThemeManagerImpl themeManager;
	
	/**
	 * Post sub theme.
	 *
	 * @param idTheme the id theme
	 * @param subTheme the sub theme
	 * @return the sub theme
	 * @throws ThemeDoesntExistException the theme doesnt exist exception
	 */
	public SubTheme postSubTheme(long idTheme, SubTheme subTheme) throws ThemeDoesntExistException {
		Theme theme = themeManager.findOne(idTheme);
		
		if (theme != null) {
			return subThemeManager.create(subTheme);
		} else {
			throw new ThemeDoesntExistException("The theme with id " + idTheme + " does not exist");
		}
	}
}
