package org.idnt.udemy.mockitoapp.example.service.impl.test;

import org.idnt.udemy.mockitoapp.example.model.Exam;
import org.idnt.udemy.mockitoapp.example.repository.ExamRepository;
import org.idnt.udemy.mockitoapp.example.repository.QuestionRepository;
import org.idnt.udemy.mockitoapp.example.repository.impl.ExamRepositoryImpl;
import org.idnt.udemy.mockitoapp.example.repository.impl.QuestionRepositoryImpl;
import org.idnt.udemy.mockitoapp.example.service.ExamService;
import org.idnt.udemy.mockitoapp.example.service.impl.ExamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.*;

import static org.idnt.udemy.mockitoapp.example.service.impl.test.util.ExamServiceImplTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplSpyTest {
    @Spy
    private ExamRepositoryImpl examRepository;

    @Spy
    private QuestionRepositoryImpl questionRepository;

    @InjectMocks
    private ExamServiceImpl examService;

    @Test
    @DisplayName("Test \"spy()\"")
    void testSpy() {
        //Given
        /**
         * In the "when" method is being called because it is a real method.
         * Better to use the "doReturn" method
         */
        //when(questionRepository.findQuestionByExamId(anyLong())).thenReturn(Collections.emptyList());
        doReturn(Collections.emptyList()).when(questionRepository).findQuestionByExamId(anyLong());

        //When
        Optional<Exam> examWithQuestionsOptional = examService.findExamByNameWithQuestions("Matemáticas");

        //Then
        assertTrue(examWithQuestionsOptional.isPresent(), () -> "The resulting exam can't be void.");
        Exam exam = examWithQuestionsOptional.get();
        assertEquals("Matemáticas", exam.getName(), () -> "The name of the exam doesn't match with the expected name.");
        assertNotNull(exam.getQuestions(), () -> "The questions of resulting exam can't be null.");
        assertEquals(0, exam.getQuestions().size(), () ->
                String.format("The size of the questions of resu lting exam must be %s", 0));
    }
}