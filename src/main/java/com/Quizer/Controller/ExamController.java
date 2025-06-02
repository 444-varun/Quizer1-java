package com.Quizer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Quizer.Entity.Exam;
import com.Quizer.Service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {


	    @Autowired
	    private ExamService examService;

	    // Create new exam
	    @PostMapping("/")
	    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
	        Exam savedExam = examService.saveExam(exam);
	        return ResponseEntity.ok(savedExam);
	    }

	    // Get all exams
	    @GetMapping("/")
	    public ResponseEntity<List<Exam>> getAllExams() {
	        return ResponseEntity.ok(examService.getAllExams());
	    }

	    // Get exam by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
	        Exam exam = examService.getExamById(id);
	        if (exam == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(exam);
	    }

	    // Update exam
	    @PutMapping("/{id}")
	    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam exam) {
	        Exam updatedExam = examService.updateExam(id, exam);
	        if (updatedExam == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(updatedExam);
	    }

	    // Delete exam
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
	        examService.deleteExam(id);
	        return ResponseEntity.noContent().build();
	    }
	
}
