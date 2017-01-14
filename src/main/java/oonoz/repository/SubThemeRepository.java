package oonoz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.SubTheme;
import oonoz.domain.Theme;

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

	
	/**
	 * Find by label
	 *
	 * @param label the subtheme's label
	 * @return the list of subtheme
	 */
	List <SubTheme> findByLabelIgnoreCaseStartingWith(String label);
	
	
	
}
