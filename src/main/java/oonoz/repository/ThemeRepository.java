package oonoz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Theme;

/**
 * The Interface ThemeRepository.
 */
public interface ThemeRepository extends CrudRepository<Theme, Long> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Theme> findAll();
	
	/**
	 * Find by label.
	 *
	 * @param label the label
	 * @return the theme
	 */
	Theme findByLabel(String label);
}
