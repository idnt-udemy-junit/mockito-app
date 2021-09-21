package org.idnt.udemy.mockitoapp.example.repository.impl;

import org.idnt.udemy.mockitoapp.example.model.Exam;
import org.idnt.udemy.mockitoapp.example.repository.ExamRepository;

import java.util.Arrays;
import java.util.List;

public class ExamRepositoryImpl implements ExamRepository {
    @Override
    public List<Exam> findAll() {
        return Arrays.asList(
                new Exam( 1L, "Matemáticas"),
                new Exam( 2L, "Lengua"),
                new Exam( 3L, "Inglés"),
                new Exam( 4L, "Historia"),
                new Exam( 5L, "Geografía")
        );
    }
}
