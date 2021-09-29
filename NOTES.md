# NOTES

## ¿Qué es Mockito?
Es un framework de pruebas que nos permite crear objetos simulados (mock) en un entorno controlado y determinado. Esta herramienta nos permite simular el comportamiento de una clase de forma dinámica. De esta manera nos aislamos de las dependencias con otras clases y sólo testeamos la funcionalidad concreta que queremos.  

---

## Glosario

### + `mock()`
Este método nos devuelve un objeto mockeado del tipo de clase que le pasemos. Ej.:  
```java
Repository repository = mock(Repository.class);
```

**Packaje:**  
`org.mockito.Mockito.mock`

### + `when()`
Este método nos permite establecer un comportamiento para el objeto mock.   
Se puede establecer que va a devolver una llamada a un método de mock con el método `thenReturn` de la respuesta del `when`. Ej.:
```java
Repository repository = mock(Repository.class);
        when(repository.findAll()).thenReturn(Collections.emptyList());
```
También se puede utilizar el métod `then` de ka respuesta del `when`, el cual se le pasa un objeto `Answer`. Ej.:  
```java
when(repository.save(any(Object.class))).then(new Answer<Object>(){
            private Long secuencial = 1L;

            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object obj = invocationOnMock.getArgument(0); //Obtiene el parámetro de la llamada del mock
                obj.setId(this.secuencial++); //Lo modificamos
                return obj; //Y lo devolvemos
            }
        });
```

**Packaje:**  
`org.mockito.Mockito.when`  
`org.mockito.stubbing.Answer`  

### + `verify()`
Este método nos permite comprobar si se realiza una llamada a un determinado método del objeto mock. Ej.:
```java
verify(repository).findAll();
```

**Packaje:**  
`org.mockito.Mockito.verify`

### + Habilitar las anotaciones de Mockito
Para habilitar las anotaciones lo haremos a través de esta instrucción de Mockito `MockitoAnnotations.openMocks(this)` en un `@BeforeEach` o también podemos anotar la clase con la anotación de JUnit `@ExtendWith(MockitoExtension.class)`.

**Packaje:**  
`org.mockito.MockitoAnnotations`  
`org.mockito.junit.jupiter.MockitoExtension`  
`org.junit.jupiter.api.extension.ExtendWith`  
`org.junit.jupiter.api.BeforeEach`

### + `@Mock`
Esta anotación marca una propiedad para mockearla. Ej.:

**Packaje:**  
`org.mockito.Mock`

### + `@InjectMocks`
Esta anotación marca una propiedad para inyectarle los mocks a través del constructor. Ej.:

**Packaje:**  
`org.mockito.InjectMocks`

### + `Assertions.assertThrows`
Este método comprueba que se lanza una excepción y la devuelve. Ej.:  
```java
Exception exception = assertThrows(IllegalArgumentException.class, () ->
                service.findExamByName(name));
```

**Packaje:**  
`org.junit.jupiter.api.Assertions.assertThrows`