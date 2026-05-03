# FrontEnd - Interfaz Swing

Módulo frontend que proporciona una interfaz gráfica Swing para gestionar docentes. Requiere el módulo backend.

## Estructura

- `src/com/ui/App.java` — Lanzador de la aplicación
- `src/com/ui/MainApp.java` — Ventana principal Swing con CRUD de Docentes

## Compilación

```bash
cd FrontEnd
mvn clean package
```

## Ejecución

```bash
cd FrontEnd
mvn exec:java
```

La ventana se abrirá mostrando una tabla con los docentes de la base de datos y botones para Crear, Editar, Eliminar y Refrescar registros.

## Dependencias

- módulo backend (`com.backend:funcionarios-backend`)
- Swing (incluido en el JDK)
- Java 11+
