package org.idnt.udemy.mockitoapp.example.service.impl.test.util;

import org.idnt.udemy.mockitoapp.example.model.Exam;
import org.idnt.udemy.mockitoapp.example.repository.impl.Data;

import java.util.*;

public class ExamServiceImplTestData {
    public static final Exam EXAM;
    public static final List<Exam> DATA_LIST_EXAM;
    public static final List<Exam> DATA_LIST_EXAM_WITH_ID_AS_NULL;
    public static final List<Exam> DATA_LIST_EXAM_WITH_ID_NEGATIVES;
    public static final Map<Long, List<String>> DATA_LIST_EXAM_QUESTION;
    
    static{
        //EXAM
        EXAM = new Exam(null, "Física");

        //LIST OF EXAMS
        DATA_LIST_EXAM = Data.DATA_LIST_EXAM;

        //LIST OF EXAMS WITH ID AS NULL
        DATA_LIST_EXAM_WITH_ID_AS_NULL = new ArrayList<>();
        for(Exam exam : DATA_LIST_EXAM){
            DATA_LIST_EXAM_WITH_ID_AS_NULL.add(new Exam(null, exam.getName()));
        }

        //LIST OF EXAMS WITH IDS NEGATIVES
        DATA_LIST_EXAM_WITH_ID_NEGATIVES = new ArrayList<>();
        for(Exam exam : DATA_LIST_EXAM){
            DATA_LIST_EXAM_WITH_ID_NEGATIVES.add(new Exam(exam.getId()*-1, exam.getName()));
        }

        //LIST OF EXAM QUESTIONS
        DATA_LIST_EXAM_QUESTION = Data.DATA_LIST_EXAM_QUESTION;
    }
}
