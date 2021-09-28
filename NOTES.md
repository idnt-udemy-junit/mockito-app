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
Este método nos permite establecer un comportamiento para el objeto mock. Ej.:  
```java
Repository repository = mock(Repository.class);
when(repository.findAll()).thenReturn(Collections.emptyList());
```

**Packaje:**  
`org.mockito.Mockito.when`