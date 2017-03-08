package oonoz.service;

import java.util.List;

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
	 * Find one subtheme.
	 *
	 * @param idSubTheme the id sub theme
	 * @return the sub theme
	 */
	public SubTheme findOne(long idSubTheme) {
		return subThemeManager.findOne(idSubTheme);
	}
	
	/**
	 * Find all subthemes from a theme.
	 *
	 * @param idTheme the id theme
	 * @return the all sub themes from theme
	 */
	public List<SubTheme> getAllSubThemesFromTheme(long idTheme){
		return subThemeManager.findByIdTheme(idTheme);
	}
	
	/**
	 * Gets the sub themes by label.
	 *
	 * @param label the label
	 * @return the sub themes by label
	 * @throws ThemeDoesNotExistException the theme does not exist exception
	 */
	public List<SubTheme> getSubThemesByLabel(String label) throws ThemeDoesNotExistException{
		return subThemeManager.findByLabelIgnoreCaseStartingWith(label);
	}
	
	
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
	
	/**
	 * Validate sub theme.
	 *
	 * @param id the id
	 * @return the sub theme
	 * @throws SubThemeDoesNotExistException the sub theme does not exist exception
	 */
	public SubTheme validateSubTheme(long id) throws SubThemeDoesNotExistException {
		return subThemeManager.validate(id);
	}
}
