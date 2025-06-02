package com.Quizer.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Quizer.Entity.Question;

public interface QuestionRepository extends  JpaRepository<Question, Long> {
	
	Page<Question> findByLanguageAndTopicAndLevel(String language , String topic , String level, Pageable pageable);

	@Query("Select DISTINCT q.language FROM Question q")
	List<String>findDistinctLanguage();
	
	@Query("Select DISTINCT q.topic FROM Question q")
	List<String>findDistinctTopic();
	
	@Query("Select DISTINCT q.topic FROM Question q WHERE q.language = :language ")
	List<String>findTopicByLanguage(@Param("language")String language);
	
//	@Query("select q.question FROM Question q WHERE q.question LIKE %:question% ")
//	List<String>findQuestions(@Param("question")String question);
	
	@Query("SELECT q FROM Question q WHERE LOWER(q.question) LIKE LOWER(CONCAT('%', :question, '%'))")
	Page<Question> searchByQuestionText(@Param("question") String question, Pageable pageable);

	
}
