package org.idnt.udemy.mockitoapp.example.repository.impl;

import org.idnt.udemy.mockitoapp.example.model.Exam;
import org.idnt.udemy.mockitoapp.example.repository.ExamRepository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamRepositoryImpl implements ExamRepository {
    private List<Exam> exams;

    public ExamRepositoryImpl() {
        this.exams = Arrays.asList(
                new Exam( 1L, "Matemáticas"),
                new Exam( 2L, "Lengua"),
                new Exam( 3L, "Inglés"),
                new Exam( 4L, "Historia"),
                new Exam( 5L, "Geografía")
        );
    }

    @Override
    public List<Exam> findAll() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return this.exams;
    }
}
