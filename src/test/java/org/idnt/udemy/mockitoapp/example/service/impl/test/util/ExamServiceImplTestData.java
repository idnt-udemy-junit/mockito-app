package org.idnt.udemy.mockitoapp.example.service.impl.test.util;

import org.idnt.udemy.mockitoapp.example.model.Exam;

import java.util.*;

public class ExamServiceImplTestData {
    public static final Exam EXAM;
    public static final List<Exam> DATA_LIST_EXAM;
    public static final Map<Long, List<String>> DATA_LIST_EXAM_QUESTION;
    
    static{
        //EXAM
        EXAM = new Exam(6L, "Física");

        //LIST OF EXAMS
        DATA_LIST_EXAM = Arrays.asList(
                new Exam( 1L, "Matemáticas"),
                new Exam( 2L, "Lengua"),
                new Exam( 3L, "Inglés"),
                new Exam( 4L, "Historia"),
                new Exam( 5L, "Geografía")
        );

        //LIST OF EXAM QUESTIONS
        DATA_LIST_EXAM_QUESTION = new HashMap<>();
        DATA_LIST_EXAM_QUESTION.put(1L, Arrays.asList(
                "Question 1",
                "Question 2"
        ));

        DATA_LIST_EXAM_QUESTION.put(2L, Arrays.asList(
                "Question 1",
                "Question 2",
                "Question 3"
        ));

        DATA_LIST_EXAM_QUESTION.put(3L, Collections.emptyList());
        DATA_LIST_EXAM_QUESTION.put(4L, Collections.emptyList());
        DATA_LIST_EXAM_QUESTION.put(5L, Collections.emptyList());
    }
}
