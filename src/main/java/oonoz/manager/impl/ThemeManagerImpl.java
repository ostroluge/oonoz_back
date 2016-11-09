package oonoz.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.Theme;
import oonoz.exception.ThemeAlreadyExistException;
import oonoz.exception.ThemeDoesntExistException;
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
	 * @throws ThemeDoesntExistException the theme doesnt exist exception
	 */
	public void remove(Long id) throws ThemeDoesntExistException {
		
		Theme existingTheme = themeRepository.findOne(id);
		if (existingTheme != null) {
			themeRepository.delete(id);
		} else {
			throw new ThemeDoesntExistException("The theme with id " + id + "does not exist");
		}
	}

	/**
	 * Update theme.
	 *
	 * @param theme the theme
	 * @return the theme
	 * @throws ThemeDoesntExistException the theme doesnt exist exception
	 */
	public Theme update(Theme theme) throws ThemeDoesntExistException {
		
		Theme existingTheme = themeRepository.findOne(theme.getIdTheme());
		if (existingTheme != null) {
			return themeRepository.save(theme);
		} else {
			throw new ThemeDoesntExistException("The theme does not exist");
		}
	}
}
