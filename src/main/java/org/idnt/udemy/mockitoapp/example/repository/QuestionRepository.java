package org.idnt.udemy.mockitoapp.example.repository;

import java.util.List;

public interface QuestionRepository {
    List<String> findQuestionByExamId(Long id);

    void saveSeveral(Long id, List<String> questions);
}
