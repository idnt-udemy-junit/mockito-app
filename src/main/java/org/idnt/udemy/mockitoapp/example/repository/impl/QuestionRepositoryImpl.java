package org.idnt.udemy.mockitoapp.example.repository.impl;

import org.idnt.udemy.mockitoapp.example.repository.QuestionRepository;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class QuestionRepositoryImpl implements QuestionRepository {
    private Map<Long, List<String>> questions;

    public QuestionRepositoryImpl() {
        this.questions = new HashMap<>();
        this.questions.put(1L, Arrays.asList(
                "Question 1",
                "Question 2"
        ));

        this.questions.put(2L, Arrays.asList(
                "Question 1",
                "Question 2",
                "Question 3"
        ));

        this.questions.put(3L, Collections.emptyList());
        this.questions.put(4L, Collections.emptyList());
        this.questions.put(5L, Collections.emptyList());
    }

    @Override
    public List<String> findQuestionByExamId(Long id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return this.questions.get(id);
    }
}
