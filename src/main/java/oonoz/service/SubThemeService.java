package oonoz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.SubTheme;
import oonoz.domain.Theme;
import oonoz.exception.SubThemeDoesNotExistException;
import oonoz.exception.ThemeDoesNotExistException;
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
	 * @throws ThemeDoesNotExistException the theme doesnt exist exception
	 */
	public SubTheme postSubTheme(long idTheme, SubTheme subTheme) throws ThemeDoesNotExistException {
		Theme theme = themeManager.findOne(idTheme);
		
		if (theme != null) {
			return subThemeManager.create(subTheme);
		} else {
			throw new ThemeDoesNotExistException("The theme with id " + idTheme + " does not exist");
		}
	}

	/**
	 * Update sub theme.
	 *
	 * @param idSubTheme the id sub theme
	 * @param subTheme the sub theme
	 * @return the sub theme
	 * @throws SubThemeDoesNotExistException the sub theme does not exist exception
	 */
	public SubTheme updateSubTheme(long idSubTheme, SubTheme subTheme) 
			throws SubThemeDoesNotExistException {
		return subThemeManager.update(idSubTheme, subTheme);
	}

	/**
	 * Removes the sub theme.
	 *
	 * @param idSubTheme the id sub theme
	 * @throws SubThemeDoesNotExistException the sub theme does not exist exception
	 */
	public void removeSubTheme(long idSubTheme) throws SubThemeDoesNotExistException {
		SubTheme subtheme = subThemeManager.findOne(idSubTheme);
		if (subtheme != null) {
			subThemeManager.remove(subtheme);
		} else {
			throw new SubThemeDoesNotExistException("The subTheme with id " + idSubTheme + " does not exist");
		}
	}
}
