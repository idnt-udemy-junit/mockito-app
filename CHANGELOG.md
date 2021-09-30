# CHANGELOG
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## UNRELEASED

### ADD
- **`feature` `#03 - Mockito` // `#049` - Implementando espías con Spy y doReturn**
  - _The `QuestionRepositoryImpl` class has been updated._
  - _A new test has been added to the `ExamServiceImplTest` class to test the `spy`._
  - _A new test class called `ExamServiceImplSpyTest` has been added to test spies with annotations._
  - _The NOTES file has been updated._  
   

- **`feature` `#03 - Mockito` // `#048` - Usando doCallRealMethod para la llamada real a un método mock**
  - _Repository implementations have been retrieved._
  - _A new test has been added to the `ExamServiceImplTest` class to test `doCallRealMethod`._
  - _The NOTES file has been updated._  
    

- **`feature` `#03 - Mockito` // `#047` - Usando doAnswer**
  - _Two new tests have been added in the `ExamServiceImplTest` class to test `doAnswer`._
  - _The NOTES file has been updated._
  

- **`feature` `#03 - Mockito` // `#046` - Usando doThrow para comprobar excepciones en métodos void**
  - _A new test has been added in the `ExamServiceImplTest` class to test `doThrow`._  
  - _The NOTES file has been updated._


- **`feature` `#03 - Mockito` // `#045` - Capturando argumentos de método con Argument capture**
  - _Some error has been fixed in `ExamServiceImplTest` class._
  - _A new test has been added to the `ExamServiceImplTest` class to test "Argument Capture"._  
  

- **`feature` `#03 - Mockito` // `#044` - Argument matchers parte 2**
  - _The `ExamServiceImplTestData` class has been updated._
  - _The last test that was added to test more of the "Argument Matcher" has been edited._  
  

- **`feature` `#03 - Mockito` // `#043` - Argument matchers**
  - _The ExamServiceImplTestData class has been updated._
  - _A new test has been added to the `ExamServiceImplTest` class to test some "Argument Matchers"._  
  - _The NOTES file has been updated._  
  

- **`feature` `#03 - Mockito` // `#042` - Comprobaciones de excepciones usando when y thenThrow**
  - _A list of tests with null ids has been added to the class containing the test data._
  - _A test has been created to check that an Exception is thrown when a null is passed to the `findQuestionByExamId` method of the `QuestionRepository` class._  
  - _The NOTES file has been updated._  
  

- **`feature` `#03 - Mockito` // `#041` - Test del id incremental en el método guardar usando Invocation Argument**
  - _The `ExamServiceImplTestData` class has been updated._
  - _The `givenExamWithQuestions_whenSaveIsCalled_thenReturnSavedExam` test of `ExamServiceImplTest` has been updated to test the incremental id._
  - _The NOTES file has been updated._  


- **`feature` `#03 - Mockito` // `#040` - Realizando más pruebas del repositorio con el método guardar**
  - _Method `save` has been added in class `ExamRepository`._
  - _Method `saveSeveral` has been added in class `QuestionRepository`._
  - _Method `save` has been added in class `ExamService`._
  - _Method `save` of `ExamService` class has been implemented in `ExamServiceImpl` class._
  - _An exam has been added to the test data class._
  - _A test has been added to the `ExamServiceImplT` class to test the `save` method of the `ExamServiceImpl` class._  
  

- **`feature` `#03 - Mockito` // `#039` - Inyección de dependecia y anotaciones @Mock, @InjectMocks y @ExtendWith**
  - _The mocks of the `ExamServiceImplTest` class have been set through Mockito annotations._  
  - _The NOTES file has been updated._  


- **`feature` `#03 - Mockito` // `#038` - Probando con verify**
  - _A test has been added to test `verify` method._
  - _The NOTES file has been updated._  
   

- **`feature` `#03 - Mockito` // `#037` - Probando nuevas dependencias mock**
  - _Repository implementations have been removed._
  - _A new class has been created to store the example data._
  - _A new test has been added to test the new `findExamByNameWithQuestions` method of the `ExamServiceImpl` class._  
  
  
- **`feature` `#03 - Mockito` // `#036` - Agregando nuevas dependencias mock**
  - The implementation of the `ExamRepository` has been updated.
  - The `QuestionRepository` has been created and implemented.
  - New functionality has been added to the `ExamService` class and has been implemented.
  - The test class `ExamServiceImplTest` has been updated to mock the repositories before each test.
   

- **`feature` `#03 - Mockito` // `#035` - Realizando primeras pruebas con mockito**
  - _A timeout has been added to simulate a working time in the `ExamRepositoryImpl` class._
  - _The `findExamByName` method of the `ExamServiceImpl` class has been edited to return an `Optional`._
  - _A test has been added in the `ExamServiceImplTest` class with the repository mocked so that the `findAll` method returns data and finds an exam._
  - _A test has been added in the `ExamServiceImplTest` class with the repository mocked so that the `findAll` method doesn't return data and retrieves an empty `Optional`._  
  - _The NOTES file has been updated._
  

- **`feature` `#03 - Mockito` // `#034` - Implementando la clase Service**
  - _The implementation of the `ExamService` class has been created._
  - _The implementation of the `ExamRepository` class has been created._
  - _The test class `ExamServiceImplTest` has been created for the class `ExamServiceImpl`._  
  

- **`feature` `#03 - Mockito` // `#033` - Creando y configurando el proyecto con JUnit 5 y Mockito**
  - _The dependencies that we will need have been added to the project._
  - _Some classes have been created to the project (`Exam`, `ExamService` and `ExamRepository`)._
  - _The NOTES file has been updated._
---