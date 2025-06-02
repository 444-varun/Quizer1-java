package com.Quizer.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Quizer.Entity.Exam;
import com.Quizer.Repository.ExamRepository;
import com.Quizer.Service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
    private ExamRepository examRepository;

    @Override
    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam getExamById(Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        return exam.orElse(null);
    }

    @Override
    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }

    @Override
    public Exam updateExam(Long id, Exam examDetails) {
        Exam exam = getExamById(id);
        if (exam == null) return null;

        exam.setExamDate(examDetails.getExamDate());
        exam.setUser(examDetails.getUser());
        exam.setTotalQuestions(examDetails.getTotalQuestions());
        exam.setAttempted(examDetails.getAttempted());
        exam.setCorrect(examDetails.getCorrect());
        exam.setWrong(examDetails.getWrong());
        exam.setSkipped(examDetails.getSkipped());
        exam.setScore(examDetails.getScore());

        return examRepository.save(exam);
    }
}
