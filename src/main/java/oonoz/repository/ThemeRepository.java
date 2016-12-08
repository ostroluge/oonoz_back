package oonoz.repository;



import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Theme;

/**
 * The Interface ThemeRepository.
 */
public interface ThemeRepository extends CrudRepository<Theme, Long> {
	Theme findByLabelLike(String label);
}
