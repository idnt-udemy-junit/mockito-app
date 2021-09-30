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
class ExamServiceImplTest {
    @Mock
    private ExamRepositoryImpl examRepository;

    @Mock
    private QuestionRepositoryImpl questionRepository;

    @InjectMocks
    private ExamServiceImpl examService;

    private Exam exam;
    private List<Exam> dataListExam;
    private List<Exam> dataListExamWithIdAsNull;
    private List<Exam> dataListExamWithIdsNegatives;
    private Map<Long, List<String>> dataListExamQuestions;

    @BeforeEach
    void setUp() {
        this.exam = new Exam(EXAM.getId(), EXAM.getName());
        this.dataListExam = new ArrayList<>(DATA_LIST_EXAM);
        this.dataListExamWithIdAsNull = new ArrayList<>(DATA_LIST_EXAM_WITH_ID_AS_NULL);
        this.dataListExamWithIdsNegatives = new ArrayList<>(DATA_LIST_EXAM_WITH_ID_NEGATIVES);
        this.dataListExamQuestions = new HashMap<>(DATA_LIST_EXAM_QUESTION);
    }

    @ParameterizedTest(name="{index}-> args = [{argumentsWithNames}]")
    @CsvSource({"1,Matemáticas", "2,Lengua", "3,Inglés", "4,Historia", "5,Geografía"})
    @DisplayName("Search and find an exam by name in exams repository with data")
    void givenNameThatExistsInExamRepository_whenFindExamByNameIsCalled_thenReturnOptionalWithCorrectExam(
            final Long id, final String name) {
        //Given
        final String EXAM_NAME = name;
        final Long EXPECT_ID = id;
        final String EXPECT_NAME = name;

        when(this.examRepository.findAll()).thenReturn(this.dataListExam);

        //When
        Optional<Exam> examOptional = this.examService.findExamByName(EXAM_NAME);

        //Then
        assertTrue(examOptional.isPresent(), () -> "The resulting exam can't be void.");
        assertEquals(EXPECT_ID, examOptional.get().getId(), () -> String.format("The id of resulting exam isn't %d", EXPECT_ID));
        assertEquals(EXPECT_NAME, examOptional.get().getName(), () -> String.format("The name of resulting exam isn't %s", EXPECT_NAME));
    }

    @Test
    @DisplayName("Search a exam by name in exams repository whithout data")
    void givenExamRepositoryHasNotData_whenFindExamByNameIsCalled_thenReturnEmptyOptional() {
        final String EXAM_NAME = "Matemáticas";
        //Given
        when(this.examRepository.findAll()).thenReturn(Collections.emptyList());

        //When
        Optional<Exam> examOptional = this.examService.findExamByName(EXAM_NAME);

        //Then
        assertFalse(examOptional.isPresent(), () -> "The resulting exam must be void.");
    }

    @ParameterizedTest(name="{index}-> args = [{argumentsWithNames}]")
    @CsvSource({"1,Matemáticas,2", "2,Lengua,3", "3,Inglés,0", "4,Historia,0", "5,Geografía,0"})
    @DisplayName("Search and find an exam with questions by name in questions repository with data")
    void givenNameThatExistsInExamRepository_whenFindExamByNameWithQuestionsIsCalled_thenReturnOptionalWithCorrectExamWithQuestions(
            final Long id, final String name, final int totalQuestions) {
        //Given
        when(this.examRepository.findAll()).thenReturn(this.dataListExam);
        when(this.questionRepository.findQuestionByExamId(id)).thenReturn(this.dataListExamQuestions.get(id));

        //When
        Optional<Exam> examWithQuestionsOptional = this.examService.findExamByNameWithQuestions(name);

        //Then
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
        //Given
        when(this.examRepository.findAll()).thenReturn(this.dataListExam);

        //When
        Optional<Exam> examWithQuestionsOptional = this.examService.findExamByNameWithQuestions(name);

        //Then
        verify(this.examRepository).findAll();
        verify(this.questionRepository).findQuestionByExamId(anyLong());
    }

    @Test
    @DisplayName("Check that an exam with questions is saved and the saved exam is returned")
    void givenExamWithQuestions_whenSaveIsCalled_thenReturnSavedExam() {
        //Given
        this.exam.setQuestions(Arrays.asList("Question 1"));

        when(this.examRepository.save(any(Exam.class))).then(new Answer<Exam>(){
            private Long secuencial = 6L;

            @Override
            public Exam answer(InvocationOnMock invocationOnMock) throws Throwable {
                Exam exam = invocationOnMock.getArgument(0);
                exam.setId(this.secuencial++);
                return exam;
            }
        });

        //When
        Exam examSaved = this.examService.save(this.exam);

        //Then
        assertNotNull(examSaved, () -> "The saved exam can't be null");
        assertEquals(6L, examSaved.getId(), () -> "The id of the saved exam doesn't match with the expected id");
        assertEquals("Física", examSaved.getName(), () -> "The name of the saved exam doesn't match with the expected name");
        verify(this.examRepository).save(any(Exam.class));
        verify(this.questionRepository).saveSeveral(any(), anyList());
        assertEquals(this.exam.getQuestions().size(), examSaved.getQuestions().size(), () ->
                String.format("The size of the questions of resulting exam must be %s", this.exam.getQuestions().size()));
    }

    @ParameterizedTest(name="{index}-> args = [{argumentsWithNames}]")
    @ValueSource(strings = {"Matemáticas", "Lengua", "Inglés", "Historia", "Geografía"})
    @DisplayName("Check that an exam with questions and no id, when searching for its questions throws an IllegalArgumentException")
    void givenSearchedExamWithIdAsNull_whenFindExamByNameWithQuestionsIsCalled_thenThrowsIllegalArgumentException(
            final String name ) {
        //Given
        when(this.examRepository.findAll()).thenReturn(this.dataListExamWithIdAsNull);
        when(this.questionRepository.findQuestionByExamId(isNull())).thenThrow(IllegalArgumentException.class);

        //When and Then
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                this.examService.findExamByNameWithQuestions(name));
        assertEquals(IllegalArgumentException.class, exception.getClass());
        verify(this.examRepository).findAll();
        verify(this.questionRepository).findQuestionByExamId(isNull());
    }

    @ParameterizedTest(name="{index}-> args = [{argumentsWithNames}]")
    @ValueSource(strings = {"Matemáticas", "Lengua", "Inglés", "Historia", "Geografía"})
    @DisplayName("Argument Mathcers")
    void givenNameThatExistsInExamRepositoryWithIdsNegatives_whenFindExamByNameWithQuestionsIsCalled_thenTestArgumentMatchers(
            final String name ) {
        //Given
        when(this.examRepository.findAll()).thenReturn(this.dataListExamWithIdsNegatives);
        when(this.questionRepository.findQuestionByExamId(anyLong())).thenReturn(Collections.emptyList());

        //When
        this.examService.findExamByNameWithQuestions(name);

        //Then
        verify(this.examRepository).findAll();
        verify(this.questionRepository).findQuestionByExamId(argThat(new MyArgsMatchers()));
    }

    //My Argunment Matcher
    public static class MyArgsMatchers implements ArgumentMatcher<Long>{
        private Long argument;

        @Override
        public boolean matches(Long aLong) {
            this.argument = aLong;
            return aLong != null && aLong > 0;
        }

        @Override
        public String toString() {
            return "This is a custom error message that Mockito will display in case the test fails !"+
                    String.format("\n\tArgument must be greater than 0 and mustn't be void. Argument: %d", this.argument);
        }
    }

    @ParameterizedTest(name="{index}-> args = [{argumentsWithNames}]")
    @ValueSource(strings = {"Matemáticas", "Lengua", "Inglés", "Historia", "Geografía"})
    @DisplayName("Argument Capture")
    void givenNameThatExistsInExamRepositoryWithIdsNegatives_whenFindExamByNameWithQuestionsIsCalled_thenCheckTestArgumentCapture(
            final String name ) {
        //Given
        when(this.examRepository.findAll()).thenReturn(this.dataListExamWithIdsNegatives);
        when(this.questionRepository.findQuestionByExamId(anyLong())).thenReturn(Collections.emptyList());

        //When
        this.examService.findExamByNameWithQuestions(name);

        //Then
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(this.questionRepository).findQuestionByExamId(argumentCaptor.capture());
        assertNotNull(argumentCaptor.getValue(), () -> "The argument in \"findQuestionByExamId\" method of \"QuestionRepository\" can't be null.");
        assertTrue(argumentCaptor.getValue() > 0, () ->
                String.format("The argument in \"findQuestionByExamId\" method of \"QuestionRepository\" must be greater than 0. Actual: %s", argumentCaptor.getValue()));
    }

    @Test
    void testDoThrow() {
        //Given
        //Can't return something in a method that has no return.
        //when(this.questionRepository.saveSeveral()).thenThrow(IllegalArgumentException.class)
        doThrow(IllegalArgumentException.class).when(this.questionRepository).saveSeveral(anyLong(), anyList());
        this.exam.setId(6L);
        this.exam.setQuestions(Arrays.asList("Question 1"));

        //When // Then
        assertThrows(IllegalArgumentException.class, () -> this.examService.save(this.exam));
    }

    @Test
    void testDoAnswer() {
        //Given
        when(this.examRepository.findAll()).thenReturn(this.dataListExam);
//        when(this.questionRepository.findQuestionByExamId(anyLong())).thenReturn(Collections.emptyList());
        doAnswer(invocation -> {
           Long id = invocation.getArgument(0);
           return id == 1L ? this.dataListExamQuestions.get(id) : Collections.emptyList();
        }).when(this.questionRepository).findQuestionByExamId(anyLong());

        //When
        Optional<Exam> examWithQuestionsOptional = this.examService.findExamByNameWithQuestions("Matemáticas");

        //Then
        assertTrue(examWithQuestionsOptional.isPresent(), () -> "The resulting exam can't be void.");
        Exam exam = examWithQuestionsOptional.get();
        assertEquals("Matemáticas", exam.getName(), () -> "The name of the exam doesn't match with the expected name.");
        assertNotNull(exam.getQuestions(), () -> "The questions of resulting exam can't be null.");
        assertEquals(2, exam.getQuestions().size(), () ->
                String.format("The size of the questions of resulting exam must be %s", 2));
        verify(this.questionRepository).findQuestionByExamId(anyLong());
    }

    @Test
    @DisplayName("Check that an exam with questions is saved and the saved exam is returned - doAnswer")
    void givenExamWithQuestions_whenSaveIsCalled_thenReturnSavedExam_doAnswer() {
        //Given
        this.exam.setQuestions(Arrays.asList("Question 1"));

        doAnswer(new Answer<Exam>() {
            private Long secuencial = 6L;

            @Override
            public Exam answer(InvocationOnMock invocationOnMock) throws Throwable {
                Exam exam = invocationOnMock.getArgument(0);
                exam.setId(this.secuencial++);
                return exam;
            }
        }).when(this.examRepository).save(any(Exam.class));

        //When
        Exam examSaved = this.examService.save(this.exam);

        //Then
        assertNotNull(examSaved, () -> "The saved exam can't be null");
        assertEquals(6L, examSaved.getId(), () -> "The id of the saved exam doesn't match with the expected id");
        assertEquals("Física", examSaved.getName(), () -> "The name of the saved exam doesn't match with the expected name");
        verify(this.examRepository).save(any(Exam.class));
        verify(this.questionRepository).saveSeveral(any(), anyList());
        assertEquals(this.exam.getQuestions().size(), examSaved.getQuestions().size(), () ->
                String.format("The size of the questions of resulting exam must be %s", this.exam.getQuestions().size()));
    }

    @Test
    @DisplayName("Test \"doCallRealMethod()\"")
    void testDoCallRealMethod() {
        //Given
        when(this.examRepository.findAll()).thenReturn(this.dataListExam);
        //when(this.questionRepository.findQuestionByExamId(anyLong())).thenReturn(Collections.emptyList());
        /**
         * The class constructor and initialized properties do not work.
         * Itsn't clear to me why.
         */
        doCallRealMethod().when(this.questionRepository).findQuestionByExamId(anyLong());

        //When
        Optional<Exam> examWithQuestionsOptional = this.examService.findExamByNameWithQuestions("Matemáticas");

        //Then
        assertTrue(examWithQuestionsOptional.isPresent(), () -> "The resulting exam can't be void.");
        Exam exam = examWithQuestionsOptional.get();
        assertEquals("Matemáticas", exam.getName(), () -> "The name of the exam doesn't match with the expected name.");
        assertNotNull(exam.getQuestions(), () -> "The questions of resulting exam can't be null.");
        assertEquals(0, exam.getQuestions().size(), () ->
                String.format("The size of the questions of resu lting exam must be %s", 0));
        verify(this.questionRepository).findQuestionByExamId(anyLong());
    }

    @Test
    @DisplayName("Test \"spy()\"")
    void testSpy() {
        //Given
        ExamRepository examRepository = spy(ExamRepositoryImpl.class);
        QuestionRepository questionRepository = spy(QuestionRepositoryImpl.class);
        ExamService examService = new ExamServiceImpl(examRepository, questionRepository);

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

    @Test
    void testInvocationOrder1() {
        //Given
        when(this.examRepository.findAll()).thenReturn(this.dataListExam);

        //When
        this.examService.findExamByNameWithQuestions("Matemáticas");
        this.examService.findExamByNameWithQuestions("Lengua");

        //Then
        InOrder inOrder = inOrder(this.examRepository, this.questionRepository);
        inOrder.verify(this.examRepository).findAll();
        inOrder.verify(this.questionRepository).findQuestionByExamId(1L);
        inOrder.verify(this.examRepository).findAll();
        inOrder.verify(this.questionRepository).findQuestionByExamId(2L);
    }
}