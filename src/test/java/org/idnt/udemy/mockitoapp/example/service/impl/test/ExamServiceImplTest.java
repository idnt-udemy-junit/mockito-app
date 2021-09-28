package org.idnt.udemy.mockitoapp.example.service.impl.test;

import org.idnt.udemy.mockitoapp.example.model.Exam;
import org.idnt.udemy.mockitoapp.example.repository.ExamRepository;
import org.idnt.udemy.mockitoapp.example.repository.QuestionRepository;
import org.idnt.udemy.mockitoapp.example.service.ExamService;
import org.idnt.udemy.mockitoapp.example.service.impl.ExamServiceImpl;
import static org.idnt.udemy.mockitoapp.example.service.impl.test.util.ExamServiceImplTestData.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {
    @Mock
    private ExamRepository examRepository;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private ExamServiceImpl examService;

    private List<Exam> dataListExam;
    private Map<Long, List<String>> dataListExamQuestions;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);

        this.dataListExam = new ArrayList<>(DATA_LIST_EXAM);
        this.dataListExamQuestions = new HashMap<>(DATA_LIST_EXAM_QUESTION);
    }

    @ParameterizedTest(name="{index}-> args = [{argumentsWithNames}]")
    @CsvSource({"1,Matemáticas", "2,Lengua", "3,Inglés", "4,Historia", "5,Geografía"})
    @DisplayName("Search and find an exam by name in exams repository with data")
    void givenNameThatExistsInExamRepository_whenFindExamByNameIsCalled_thenReturnOptionalWithCorrectExam(
            final Long id, final String name) {
        final String EXAM_NAME = name;
        final Long EXPECT_ID = id;
        final String EXPECT_NAME = name;

        when(this.examRepository.findAll()).thenReturn(this.dataListExam);
        Optional<Exam> examOptional = this.examService.findExamByName(EXAM_NAME);

        assertTrue(examOptional.isPresent(), () -> "The resulting exam can't be void.");
        assertEquals(EXPECT_ID, examOptional.get().getId(), () -> String.format("The id of resulting exam isn't %d", EXPECT_ID));
        assertEquals(EXPECT_NAME, examOptional.get().getName(), () -> String.format("The name of resulting exam isn't %s", EXPECT_NAME));
    }

    @Test
    @DisplayName("Search a exam by name in exams repository whithout data")
    void givenExamRepositoryHasNotData_whenFindExamByNameIsCalled_thenReturnEmptyOptional() {
        final String EXAM_NAME = "Matemáticas";

        when(this.examRepository.findAll()).thenReturn(Collections.emptyList());
        Optional<Exam> examOptional = this.examService.findExamByName(EXAM_NAME);

        assertFalse(examOptional.isPresent(), () -> "The resulting exam must be void.");
    }

    @ParameterizedTest(name="{index}-> args = [{argumentsWithNames}]")
    @CsvSource({"1,Matemáticas,2", "2,Lengua,3", "3,Inglés,0", "4,Historia,0", "5,Geografía,0"})
    @DisplayName("Search and find an exam with questions by name in questions repository with data")
    void givenNameThatExistsInExamRepository_whenFindExamByNameWithQuestionsIsCalled_thenReturnOptionalWithCorrectExamWithQuestions(
            final Long id, final String name, final int totalQuestions) {
        when(this.examRepository.findAll()).thenReturn(this.dataListExam);
        when(this.questionRepository.findQuestionByExamId(id)).thenReturn(this.dataListExamQuestions.get(id));
        Optional<Exam> examWithQuestionsOptional = this.examService.findExamByNameWithQuestions(name);

        assertTrue(examWithQuestionsOptional.isPresent(), () -> "The resulting exam can't be void.");
        Exam exam = examWithQuestionsOptional.get();
        assertEquals(name, exam.getName(), () -> "The name of the exam doesn't match with the expected name.");
        assertNotNull(exam.getQuestions(), () -> "The questions of resulting exam can't be null.");
        assertEquals(totalQuestions, exam.getQuestions().size(), () ->
                String.format("The size of the questions of resulting exam must be %s", totalQuestions));
    }

    @ParameterizedTest(name="{index}-> args = [{argumentsWithNames}]")
    @ValueSource(strings = {"Matemáticas", "Lengua", "Inglés", "Historia", "Geografía"})
    @DisplayName("Search and find an exam with questions by name in questions repository with data - Method call verification test")
    void givenNameThatExistsInExamRepository_whenFindExamByNameWithQuestionsIsCalled_thenReturnOptionalWithCorrectExamWithQuestions_testVerifyMethodCall(
            final String name) {
        when(this.examRepository.findAll()).thenReturn(this.dataListExam);
        Optional<Exam> examWithQuestionsOptional = this.examService.findExamByNameWithQuestions(name);

        verify(this.examRepository).findAll();
        verify(this.questionRepository).findQuestionByExamId(anyLong());
    }
}