# BackEnd - Gestión de Docentes (2007-2013)

Este módulo contiene la lógica de acceso a datos (DAO), modelo de Docentes y conexión a MySQL.

## Estructura

- `src/com/backend/model/Docente.java` — Modelo de Docentes
- `src/com/backend/dao/DocenteDAO.java` — Interfaz del DAO
- `src/com/backend/dao/DocenteDAOImpl.java` — Implementación JDBC del DAO (MySQL)
- `src/com/backend/db/MySQLConnection.java` — Conexión a MySQL (JDBC)
- `src/com/backend/exception/DAOException.java` — Excepción personalizada
- `sql/schema.sql` — Script de creación de la base de datos y tabla `docentes`
- `sql/seed.sql` — Script de inserción con 5 registros de ejemplo

## Base de Datos

La aplicación usa MySQL. Configure la conexión mediante variables de entorno `MYSQL_HOST`, `MYSQL_PORT`, `MYSQL_DB`, `MYSQL_USER`, `MYSQL_PASS` o en `BackEnd/config.properties`.

Ejemplo de archivos incluidos:

- `sql/schema.sql` — crea la base `proyecto0006` y la tabla `docentes`.
- `sql/seed.sql` — inserta 5 registros de ejemplo.

## Datos

Los 5 registros iniciales contienen información de docentes del Sistema Nacional de Información de la Educación Superior (2007-2013). Los campos son:

- `codigoInstitucion`, `nombreIES`, `generoDocente`, `tipoDocumento`, `nivelFormacion`, `tiempoDedicacion`, `tipoContrato`, `departamento`, `municipio`, `conteo2013`

## Crear la base y cargar datos (local)

Si deseas que el sistema cree la base de datos y cargue los datos, asegúrate de que MySQL esté ejecutándose y que `BackEnd/config.properties` o las variables de entorno contengan las credenciales. Luego ejecuta:

```bash
mysql -u root -p < BackEnd/sql/schema.sql
mysql -u root -p < BackEnd/sql/seed.sql
```

O desde Java con una conexión JDBC (las clases del módulo realizan la conexión si las variables están configuradas).

## Compilación

```bash
cd BackEnd
mvn clean package
```

## Dependencias

- MySQL Connector/J (`mysql-connector-java` 8.x)
- Java 11+
