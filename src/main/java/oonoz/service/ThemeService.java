package oonoz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oonoz.domain.Theme;
import oonoz.exception.ThemeAlreadyExistException;
import oonoz.exception.ThemeDoesNotExistException;
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
	public Iterable<Theme> findAll() {
		return themeManager.findAll();
	}
	
	/**
	 * Find only validated themes
	 * @return
	 */
	public List<Theme> findValidated(){
		return themeManager.findValidated();
	}
	

	/**
	 * Find one theme.
	 *
	 * @param id
	 *            the id
	 * @return the theme
	 * @throws ThemeDoesNotExistException 
	 */
	public Theme findOne(long id) throws ThemeDoesNotExistException {
		return themeManager.findOne(id);
	}

	/**
	 * Find by label.
	 *
	 * @param label the label
	 * @return the theme
	 * @throws ThemeDoesNotExistException the theme does not exist exception
	 */
	public List<Theme> findByLabel(String label) throws ThemeDoesNotExistException{
		return themeManager.findByLabel(label);
	}

	/**
	 * Post theme.
	 *
	 * @param theme
	 *            the theme
	 * @return the theme
	 * @throws WrongInformationException
	 *             the wrong information exception
	 * @throws ThemeAlreadyExistException
	 *             the theme already exist exception
	 */
	public Theme postTheme(Theme theme) throws WrongInformationException, ThemeAlreadyExistException {

		checkThemeInformation.checkLabel(theme.getLabel());
		checkThemeInformation.checkDescription(theme.getDescription());

		return themeManager.create(theme);
	}

	/**
	 * Removes the theme.
	 *
	 * @param id
	 *            the id
	 * @throws ThemeDoesNotExistException
	 *             the theme doesnt exist exception
	 */
	public void removeTheme(long id) throws ThemeDoesNotExistException {
		Theme theme = themeManager.findOne(id);
		if (theme != null) {
			themeManager.remove(theme);
		}
	}

	/**
	 * Update theme.
	 *
	 * @param id the id
	 * @param theme            the theme
	 * @return the theme
	 * @throws ThemeDoesNotExistException             the theme doesnt exist exception
	 */
	public Theme updateTheme(long id, Theme theme) throws ThemeDoesNotExistException {
		return themeManager.update(id, theme);
	}

	/**
	 * Validate theme.
	 *
	 * @param id
	 *            the id
	 * @return the theme
	 * @throws ThemeDoesNotExistException
	 *             the theme does not exist exception
	 */
	public Theme validateTheme(long id) throws ThemeDoesNotExistException {
		return themeManager.validate(id);
	}
	
	
	
	
}
