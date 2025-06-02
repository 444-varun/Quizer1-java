package com.Quizer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Quizer.Entity.Question;
import com.Quizer.Repository.QuestionRepository;
import com.Quizer.Service.QuestionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/addQ")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question created = questionService.createQuestion(question);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
//        return ResponseEntity.ok(questionService.getQuestionById(id));
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
//        return ResponseEntity.ok(questionService.updateQuestion(id, question));
//    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(id, question));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
    	System.out.println("here id the id that is being deleted " + id);
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Question>> getPagedQuestions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(questionService.getQuestions(pageable));
    }
    
    @GetMapping("/getAllLanguages")
    public ResponseEntity<List<String>> getAllLanguages(){
    	List<String>languages = questionRepository.findDistinctLanguage();
    	return ResponseEntity.ok(languages);
    }
    
    @GetMapping("/getAllTopics")
    public ResponseEntity<List<String>> getAllTopics(){
    	List<String> topics = questionRepository.findDistinctTopic();
    	return ResponseEntity.ok(topics);
    }

    @GetMapping("/getTopics")
    public ResponseEntity<List<String>> getTopics(@RequestParam String language){
    	List<String> topics = questionRepository.findTopicByLanguage(language);
    	return ResponseEntity.ok(topics);
    }
    
    
    @GetMapping("/filter")
    public ResponseEntity<Page<Question>> filterQuestions(
            @RequestParam String language,
            @RequestParam(required = false) String topic,
            @RequestParam(required = false)String level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(questionService.filterQuestions(language, topic, level, pageable));
    }
    
    @GetMapping("/search")
    public ResponseEntity<Page<Question>> searchQuestions(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Question> questions = questionRepository.searchByQuestionText(query, pageable);
        return ResponseEntity.ok(questions);
    }


    
}
