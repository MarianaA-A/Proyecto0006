-- Schema for proyecto0006 (MySQL)
CREATE DATABASE IF NOT EXISTS proyecto0006 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE proyecto0006;

CREATE TABLE IF NOT EXISTS docentes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  codigoInstitucion VARCHAR(50),
  nombreIES VARCHAR(255),
  generoDocente VARCHAR(50),
  tipoDocumento VARCHAR(50),
  nivelFormacion VARCHAR(100),
  tiempoDedicacion VARCHAR(100),
  tipoContrato VARCHAR(100),
  departamento VARCHAR(100),
  municipio VARCHAR(100),
  conteo2013 INT
);
