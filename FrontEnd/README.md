# FrontEnd - UI Swing para Gestión de Docentes

Este módulo contiene la interfaz gráfica de usuario (Swing) para ver, crear, editar y eliminar registros de docentes.

## Estructura

- `src/com/ui/MainApp.java` — Aplicación principal Swing con CRUD de Docentes
- `src/App.java` — Archivo base

## Funcionalidades

La aplicación permite:
- **Listar** todos los docentes registrados
- **Crear** nuevos docentes (código IES, nombre IES, género, tipo documento, nivel formación, dedicación, contrato, departamento, municipio, conteo 2013)
- **Editar** datos de docentes existentes
- **Eliminar** docentes seleccionados
- **Refrescar** la lista

## Campos de Docente

La tabla muestra los siguientes campos:
1. ID (clave primaria autoincremental en MySQL)
2. IES (Institución de Educación Superior)
3. Género (FEMENINO/MASCULINO)
4. Tipo Documento (NACIONAL/EXTRANJERO)
5. Nivel Formación (ej: DOCTORADO, TECNICO PROFESIONAL)
6. Dedicación (ej: CATEDRA, TIEMPO COMPLETO)
7. Contrato (ej: TERMINO INDEFINIDO, TERMINO FIJO)
8. Departamento
9. Municipio
10. Conteo 2013 (número de docentes registrados)

## Compilación y Ejecución

```bash
cd FrontEnd
mvn clean package
mvn exec:java
```

La ventana se abrirá mostrando los 5 docentes registrados inicialmente.

## Dependencias

- MySQL Connector/J (cuando correspondan las dependencias)
- Java 11+
- Swing (incluido en JDK)
