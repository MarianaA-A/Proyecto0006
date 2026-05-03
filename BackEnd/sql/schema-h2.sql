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
