package com.Quizer.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Quizer.Entity.Question;

public interface QuestionService {

	Question createQuestion(Question question);

	List<Question> getAllQuestions();

//	Question getQuestionById(Long queId);
//
//	Question updateQuestion(Long queId, Question question);
//
//	void deleteQuestion(Long queId);
//	
	 Page<Question> getQuestions(Pageable pageable);
	 
Page<Question> filterQuestions(String language, String topic, String level, Pageable pageable);

Page<Question> searchQuestions(String keyword, int page, int size);

Question getQuestionById(Long id);

Question updateQuestion(Long id, Question updatedQuestion);

void deleteQuestion(Long id);

}
