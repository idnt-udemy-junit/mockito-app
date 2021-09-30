package org.idnt.udemy.mockitoapp.example.service.impl;

import lombok.Data;
import org.idnt.udemy.mockitoapp.example.model.Exam;
import org.idnt.udemy.mockitoapp.example.repository.ExamRepository;
import org.idnt.udemy.mockitoapp.example.repository.QuestionRepository;
import org.idnt.udemy.mockitoapp.example.service.ExamService;

import java.util.List;
import java.util.Optional;

@Data
public class ExamServiceImpl implements ExamService {
    private ExamRepository examRepository;
    private QuestionRepository questionRepository;

    public ExamServiceImpl(ExamRepository examRepository, QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Optional<Exam> findExamByName(String name) {
        return this.examRepository.findAll()
                .stream()
                .filter(exam -> exam.getName().contains(name))
                .findFirst();
    }

    @Override
    public Optional<Exam> findExamByNameWithQuestions(String name) {
        final Optional<Exam> examOptional = this.findExamByName(name);

        if(examOptional.isPresent()){
            final Long id = examOptional.get().getId();
            List<String> questions = this.questionRepository.findQuestionByExamId(id);
            examOptional.get().setQuestions(questions);
        }

        return examOptional;
    }

    @Override
    public Exam save(Exam exam) {
        if(!exam.getQuestions().isEmpty()){
            this.questionRepository.saveSeveral(exam.getId(), exam.getQuestions());
        }
        return this.examRepository.save(exam);
    }
}
