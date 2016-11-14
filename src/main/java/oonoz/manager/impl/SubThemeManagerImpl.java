package oonoz.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import oonoz.domain.SubTheme;
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
	 * Creates the subtheme.
	 *
	 * @param the subtheme
	 * @return the sub theme
	 */
	public SubTheme create(SubTheme subtheme) {
		return subThemeRepository.save(subtheme);
	}
}
