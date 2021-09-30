package org.idnt.udemy.mockitoapp.example.service;

import org.idnt.udemy.mockitoapp.example.model.Exam;

import java.util.Optional;

public interface ExamService {
    Optional<Exam> findExamByName(String name);
    Optional<Exam> findExamByNameWithQuestions(String name);
    Exam save(Exam exam);
}
