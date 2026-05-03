# Proyecto0006

Este repositorio quedó consolidado en un solo módulo activo: [BackEnd](BackEnd). Ahí están la lógica de acceso a datos, el modelo, la conexión a MySQL y la interfaz Swing.

## Estructura

- [BackEnd/src/com/backend/model/Docente.java](BackEnd/src/com/backend/model/Docente.java) — Modelo de docentes
- [BackEnd/src/com/backend/dao/DocenteDAO.java](BackEnd/src/com/backend/dao/DocenteDAO.java) — Contrato del DAO
- [BackEnd/src/com/backend/dao/DocenteDAOImpl.java](BackEnd/src/com/backend/dao/DocenteDAOImpl.java) — Implementación JDBC
- [BackEnd/src/com/backend/db/MySQLConnection.java](BackEnd/src/com/backend/db/MySQLConnection.java) — Conexión a MySQL
- [BackEnd/src/com/ui/App.java](BackEnd/src/com/ui/App.java) — Punto de entrada de la interfaz
- [BackEnd/src/com/ui/MainApp.java](BackEnd/src/com/ui/MainApp.java) — Ventana principal Swing
- [BackEnd/sql/schema.sql](BackEnd/sql/schema.sql) — Script de esquema
- [BackEnd/sql/seed.sql](BackEnd/sql/seed.sql) — Datos iniciales

## Ejecutar

```bash
cd BackEnd
mvn clean package
mvn exec:java
```

## Base de datos

La aplicación usa MySQL. Puedes configurar las credenciales con variables de entorno `MYSQL_HOST`, `MYSQL_PORT`, `MYSQL_DB`, `MYSQL_USER` y `MYSQL_PASS`, o con [BackEnd/config.properties](BackEnd/config.properties).
