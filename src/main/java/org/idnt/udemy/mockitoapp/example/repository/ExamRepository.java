package org.idnt.udemy.mockitoapp.example.repository;

import org.idnt.udemy.mockitoapp.example.model.Exam;

import java.util.List;

public interface ExamRepository {
    List<Exam> findAll();
}
