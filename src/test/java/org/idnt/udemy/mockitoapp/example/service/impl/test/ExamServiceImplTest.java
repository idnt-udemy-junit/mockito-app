package org.idnt.udemy.mockitoapp.example.service.impl.test;

import org.idnt.udemy.mockitoapp.example.model.Exam;
import org.idnt.udemy.mockitoapp.example.repository.ExamRepository;
import org.idnt.udemy.mockitoapp.example.repository.impl.ExamRepositoryImpl;
import org.idnt.udemy.mockitoapp.example.service.ExamService;
import org.idnt.udemy.mockitoapp.example.service.impl.ExamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExamServiceImplTest {
    private List<Exam> examList;

    @BeforeEach
    void setUp() {
        this.examList = Arrays.asList(
                new Exam( 1L, "Matemáticas"),
                new Exam( 2L, "Lengua"),
                new Exam( 3L, "Inglés"),
                new Exam( 4L, "Historia"),
                new Exam( 5L, "Geografía")
            );
    }

    @ParameterizedTest(name="{index}-> args = [{argumentsWithNames}]")
    @CsvSource({"1,Matemáticas", "2,Lengua", "3,Inglés", "4,Historia", "5,Geografía",})
    @DisplayName("Search and find an exam by name in repository with data")
    void givenNameThatExistsInRepository_whenFindExamByNameIsCalled_thenReturnOptionalWithCorrectExam(final Long id, final String name) {
        final String EXAM_NAME = name;
        final Long EXPECT_ID = id;
        final String EXPECT_NAME = name;

        ExamRepository repository = mock(ExamRepositoryImpl.class);
        when(repository.findAll()).thenReturn(this.examList);

        ExamService service = new ExamServiceImpl(repository);
        Optional<Exam> examOptional = service.findExamByName(EXAM_NAME);

        assertTrue(examOptional.isPresent(), () -> "The resulting exam can't be void.");
        assertEquals(EXPECT_ID, examOptional.get().getId(), () -> String.format("The id of resulting exam isn't %d", EXPECT_ID));
        assertEquals(EXPECT_NAME, examOptional.get().getName(), () -> String.format("The name of resulting exam isn't %s", EXPECT_NAME));
    }

    @Test
    @DisplayName("Search a exam by name in repository whithout data")
    void givenRepositoryHasNotData_whenFindExamByNameIsCalled_thenReturnEmptyOptional() {
        final String EXAM_NAME = "Matemáticas";

        ExamRepository repository = mock(ExamRepositoryImpl.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());

        ExamService service = new ExamServiceImpl(repository);
        Optional<Exam> examOptional = service.findExamByName(EXAM_NAME);

        assertFalse(examOptional.isPresent(), () -> "The resulting exam must be void.");
    }
}