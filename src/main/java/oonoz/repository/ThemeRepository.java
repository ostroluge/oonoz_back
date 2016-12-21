package oonoz.repository;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Theme;

/**
 * The Interface ThemeRepository.
 */
public interface ThemeRepository extends CrudRepository<Theme, Long> {
	Theme findByLabelLike(String label);
	
	/**
	 * Get only validated themes
	 * @return
	 */
	List<Theme> findByIsValidatedTrue();
}
