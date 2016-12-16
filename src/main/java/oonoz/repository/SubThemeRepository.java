package oonoz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.SubTheme;

/**
 * The Interface SubThemeRepository.
 */
public interface SubThemeRepository extends CrudRepository<SubTheme, Long>{
	
	
	/**
	 * Find by id theme.
	 *
	 * @param idTheme the id theme
	 * @return the list
	 */
	List<SubTheme> findByIdTheme(long idTheme);

}
