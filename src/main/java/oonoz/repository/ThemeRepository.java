package oonoz.repository;



import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Theme;

/**
 * The Interface ThemeRepository.
 */
public interface ThemeRepository extends CrudRepository<Theme, Long> {
	
	/**
	 * Find by label like.
	 *
	 * @param label the label
	 * @return the theme
	 */
	Theme findByLabelLike(String label);
}
