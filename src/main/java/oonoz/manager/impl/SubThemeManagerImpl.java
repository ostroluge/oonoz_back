package oonoz.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.SubTheme;
import oonoz.domain.Theme;
import oonoz.exception.SubThemeDoesNotExistException;
import oonoz.exception.ThemeDoesNotExistException;
import oonoz.manager.SubThemeManager;
import oonoz.repository.SubThemeRepository;

/**
 * The Class SubThemeManagerImpl.
 */
@Component(value = "subThemeManager")
public class SubThemeManagerImpl implements SubThemeManager {
	
	/** The sub theme repository. */
	@Resource
	private SubThemeRepository subThemeRepository;
	
	/**
	 * Find one subtheme.
	 *
	 * @param id the id
	 * @return the sub theme
	 */
	public SubTheme findOne(long id) {
		return subThemeRepository.findOne(id);
	}
	 
	/**
	 * Creates the subtheme.
	 *
	 * @param the subtheme
	 * @return the sub theme
	 */
	public SubTheme create(SubTheme subtheme) {
		return subThemeRepository.save(subtheme);
	}

	/**
	 * Update the sub theme.
	 *
	 * @param idSubTheme the id sub theme
	 * @param subTheme the sub theme
	 * @return the sub theme
	 * @throws SubThemeDoesNotExistException the sub theme does not exist exception
	 */
	public SubTheme update(long idSubTheme, SubTheme subTheme) 
		throws SubThemeDoesNotExistException {
		
		SubTheme existingSubTheme = subThemeRepository.findOne(idSubTheme);
		if (existingSubTheme != null) {
			subTheme.setId(existingSubTheme.getId());
			return subThemeRepository.save(subTheme);
		} else {
			throw new SubThemeDoesNotExistException("The subtheme with id " + idSubTheme + " does not exist");
		}
	}
	
	/**
	 * Removes the subtheme.
	 *
	 * @param subtheme the subtheme
	 * @throws SubThemeDoesNotExistException the sub theme does not exist exception
	 */
	public void remove(SubTheme subtheme) throws SubThemeDoesNotExistException {

		SubTheme existingSubTheme = subThemeRepository.findOne(subtheme.getId());
		if (existingSubTheme != null) {
			subThemeRepository.delete(existingSubTheme);
		} else {
			throw new SubThemeDoesNotExistException("The subTheme with id " + subtheme.getIdTheme() + "does not exist");
		}
	}

	/**
	 * Validate the subtheme.
	 *
	 * @param id the id
	 * @return the sub theme
	 * @throws SubThemeDoesNotExistException the sub theme does not exist exception
	 */
	public SubTheme validate(long id) throws SubThemeDoesNotExistException {
		SubTheme existingSubTheme = subThemeRepository.findOne(id);
		if (existingSubTheme != null) {
			existingSubTheme.setValidated(true);
			return subThemeRepository.save(existingSubTheme);
		} else {
			throw new SubThemeDoesNotExistException("The subtheme with id "+ id + " does not exist");
		}
	}
}
