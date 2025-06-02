package com.Quizer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Quizer.Entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
	
	List<Exam> findByUserId(Long UserId);

}
