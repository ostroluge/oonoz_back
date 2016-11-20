package oonoz.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.SubTheme;
import oonoz.domain.Theme;
import oonoz.exception.ThemeAlreadyExistException;
import oonoz.exception.ThemeDoesNotExistException;
import oonoz.manager.ThemeManager;
import oonoz.repository.ThemeRepository;

/**
 * The Class ThemeManagerImpl.
 */
@Component(value="themeManager")
public class ThemeManagerImpl implements ThemeManager {

	/** The theme repository. */
	@Resource
	private ThemeRepository themeRepository;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public Iterable<Theme> findAll() {
		return themeRepository.findAll();
	}

	/**
	 * Find one theme.
	 *
	 * @param id the id
	 * @return the theme
	 */
	public Theme findOne(long id) {
		return themeRepository.findOne(id);
	}
	
	/**
	 * Creates the theme.
	 *
	 * @param theme the theme
	 */
	public Theme create(Theme theme) throws ThemeAlreadyExistException {
		
		Theme existingTheme = themeRepository.findOne(theme.getIdTheme());
		if (existingTheme == null) {
			return themeRepository.save(theme);
		} else {
			throw new ThemeAlreadyExistException("The theme with label " +
					theme.getLabel() + "already exists");
		}	
	}

	/**
	 * Removes the theme.
	 *
	 * @param id the id
	 * @throws ThemeDoesNotExistException the theme doesnt exist exception
	 */
	public void remove(Theme theme) throws ThemeDoesNotExistException {
		
		Theme existingTheme = themeRepository.findOne(theme.getIdTheme());
		if (existingTheme != null) {
			existingTheme.setSubThemes(theme.getSubThemes());
			System.out.println(existingTheme.toString());
			themeRepository.delete(existingTheme);
		} else {
			throw new ThemeDoesNotExistException("The theme with id " + theme.getIdTheme() + "does not exist");
		}
	}

	/**
	 * Update theme.
	 *
	 * @param theme the theme
	 * @return the theme
	 * @throws ThemeDoesNotExistException the theme doesnt exist exception
	 */
	public Theme update(long id, Theme theme) throws ThemeDoesNotExistException {
		
		Theme existingTheme = themeRepository.findOne(id);
		if (existingTheme != null) {
			theme.setSubThemes(existingTheme.getSubThemes());
			return themeRepository.save(theme);
		} else {
			throw new ThemeDoesNotExistException("The theme does not exist");
		}
	}
}
