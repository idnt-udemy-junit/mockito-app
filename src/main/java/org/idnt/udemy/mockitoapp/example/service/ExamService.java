package org.idnt.udemy.mockitoapp.example.service;

import org.idnt.udemy.mockitoapp.example.model.Exam;

public interface ExamService {
    Exam findExamByName(String name);
}
