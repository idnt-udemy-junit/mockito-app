# CHANGELOG
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## UNRELEASED

### ADD
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