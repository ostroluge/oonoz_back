package oonoz.repository;

import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Question;

/**
 * The Interface QuestionRepository.
 */
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
