package org.idnt.udemy.mockitoapp.example.repository.impl;

import org.idnt.udemy.mockitoapp.example.model.Exam;
import org.idnt.udemy.mockitoapp.example.repository.ExamRepository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamRepositoryImpl implements ExamRepository {
    private List<Exam> exams = Data.DATA_LIST_EXAM;

    public ExamRepositoryImpl() {}

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

    @Override
    public Exam save(Exam exam) {
        return null;
    }
}
