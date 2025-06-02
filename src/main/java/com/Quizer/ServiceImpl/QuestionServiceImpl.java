package com.Quizer.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Quizer.Entity.Question;
import com.Quizer.Repository.QuestionRepository;
import com.Quizer.Service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
    private QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

//    public Question getQuestionById(Long queId) {
//        return questionRepository.findById(queId)
//                .orElseThrow(() -> new RuntimeException("Question not found with id " + queId));
//    }

//    public Question updateQuestion(Long queId, Question question) {
//        Question existing = getQuestionById(queId);
//        existing.setLanguage(question.getLanguage());
//        existing.setTopic(question.getTopic());
//        existing.setLevel(question.getLevel());
//        existing.setQuestion(question.getQuestion());
//        existing.setAnswer(question.getAnswer());
//        return questionRepository.save(existing);
//    }

    
    @Override
    public Page<Question> searchQuestions(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return questionRepository.searchByQuestionText(keyword, pageable);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Question not found"));
    }

    @Override
    public Question updateQuestion(Long id, Question updatedQuestion) {
        Question existing = getQuestionById(id);
        existing.setQuestion(updatedQuestion.getQuestion());
        existing.setAnswer(updatedQuestion.getAnswer());
        existing.setLanguage(updatedQuestion.getLanguage());
        existing.setTopic(updatedQuestion.getTopic());
        existing.setLevel(updatedQuestion.getLevel());
        return questionRepository.save(existing);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
    
    
//    public void deleteQuestion(Long queId) {
//        questionRepository.deleteById(queId);
//    }
    
    public Page<Question> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }
    
    public Page<Question> filterQuestions(String language, String topic, String level, Pageable pageable) {
        return questionRepository.findByLanguageAndTopicAndLevel(language, topic, level, pageable);
    }
    

}
