package oonoz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import oonoz.domain.Question;

/**
 * The Interface QuestionRepository.
 */
public interface QuestionRepository extends CrudRepository<Question, Long> {
	
	
	@Query(value = "select * from question q, qcm where q.id_qcm=?1 and q.question_number=?2",nativeQuery = true)
	Question findByQuestionNumber(long idQCM,int questionNumber);

}
