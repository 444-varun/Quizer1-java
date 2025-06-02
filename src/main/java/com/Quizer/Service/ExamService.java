package com.Quizer.Service;

import java.util.List;

import com.Quizer.Entity.Exam;

public interface ExamService {

	Exam saveExam(Exam exam);
    List<Exam> getAllExams();
    Exam getExamById(Long id);
    void deleteExam(Long id);
    Exam updateExam(Long id, Exam exam);
}
