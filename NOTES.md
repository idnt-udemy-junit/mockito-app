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

### + `spy()`
Este método nos devuelve un objeto mockeado del tipo de clase que le pasemos, pero a diferencia de "mock", se necesita una clase concreta y siempre que se llamen a sus métodos seran los reales a no ser que le indiquemos un comportamiento deseado. Ej.:  
```java
Repository repository = spy(RepositoryImpl.class);
```

**Packaje:**  
`org.mockito.Mockito.spy`

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

### + `doAnswer()`
Este método nos permite hacer algunos cálculos/acciones adicionales cuando se llama al método simulado. Por ejemplo, podríamos calcular el valor de retorno en función de los parámetros de entrada. Ej.:
```java
doAnswer(invocation -> {
        Long id = invocation.getArgument(0);
        return id == 1L ? "Juan" : "Pepe";
        }).when(this.repository).findNameById(anyLong());
```

**Packaje:**  
`org.mockito.Mockito.doAnswer`    

### + `doReturn()`
Este método es como el "thenReturn" del método "when". Pero se utiliza sobre todo con los "spy". Ej.:
```java
doReturn(Collections.emptyList()).when(repository).findQuestionByExamId(anyLong());
```

**Packaje:**  
`org.mockito.Mockito.doReturn`  

### + `doThrow()`
Este método nos permite establecer un comportamiento para el objeto mock.   
Se puede establecer que va a cuando se realice una llamada a un método vacío del mock. Ej.:
```java
doThrow(IllegalArgumentException.class).when(repository).save(any());
```

**Packaje:**  
`org.mockito.Mockito.doThrow`  

### + `doCallRealMethod()`
Este método nos permite realizar una llamada al método real en vez al del mock. Ej.:
```java
doThrow(IllegalArgumentException.class).when(repository).save(any());
```

**Packaje:**  
`org.mockito.Mockito.doCallRealMethod`  

### + `verify()`
Este método nos permite comprobar si se realiza una llamada a un determinado método del objeto mock. Ej.:
```java
verify(repository).findAll();
```

**Packaje:**  
`org.mockito.Mockito.verify`

### + `argThat()`
Este método nos permite comprobar de manera más específica si un argumento cumple una condición. Ej.:
```java
verify(repository).findQuestionByExamId(argThat(arg -> arg.equals(6L)));
```

**Packaje:**  
`org.mockito.Mockito.argThat`

### + `eq()`
Este método nos permite comprobar un argumento es igual a un valor. Ej.:
```java
verify(repository).findQuestionByExamId(eq(6L));
```

**Packaje:**  
`org.mockito.Mockito.eq`

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

### + `ArgumentMatcher`
Nos permite establecer conciciones a parámetros que se le pasan a un método.

**Packaje:**  
`org.mockito.ArgumentMatcher`

### + `ArgumentCaptor`
ArgumentCaptor nos permite capturar un argumento pasado a un método para inspeccionarlo. Esto es especialmente útil cuando no podemos acceder al argumento fuera del método que nos gustaría probar.

**Packaje:**  
`org.mockito.ArgumentCaptor`