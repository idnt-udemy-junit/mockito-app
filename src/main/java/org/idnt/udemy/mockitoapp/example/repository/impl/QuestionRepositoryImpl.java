package org.idnt.udemy.mockitoapp.example.repository.impl;

import org.idnt.udemy.mockitoapp.example.repository.QuestionRepository;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class QuestionRepositoryImpl implements QuestionRepository {
    private Map<Long, List<String>> questions = Data.DATA_LIST_EXAM_QUESTION;

    public QuestionRepositoryImpl() {}

    @Override
    public List<String> findQuestionByExamId(Long id) {
        System.out.println("QuestionRepositoryImpl.findQuestionByExamId(Long id)");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return this.questions.get(id);
    }

    @Override
    public void saveSeveral(Long id, List<String> questions) {

    }
}
