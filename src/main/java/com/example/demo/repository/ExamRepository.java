package com.example.demo.repository;

import com.example.demo.model.Exam;

import java.util.Optional;

public interface ExamRepository {

    Optional<Exam> findById(long examId);

    void save(Exam exam);
}
