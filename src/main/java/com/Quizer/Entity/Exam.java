package com.Quizer.Entity;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    private LocalDateTime examDate;

    private int totalQuestions;
    private int attempted;
    private int correct;
    private int wrong;
    private int skipped;
    private double score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Reference to the user who gave the exam
    
}