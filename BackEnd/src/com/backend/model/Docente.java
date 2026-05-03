package com.backend.model;

public class Docente {
    private String id;
    private String codigoInstitucion;
    private String nombreIES;
    private String generoDocente;
    private String tipoDocumento;
    private String nivelFormacion;
    private String tiempoDedicacion;
    private String tipoContrato;
    private String departamento;
    private String municipio;
    private Integer conteo2013;

    public Docente() {}

    public Docente(String id, String codigoInstitucion, String nombreIES, String generoDocente,
                   String tipoDocumento, String nivelFormacion, String tiempoDedicacion,
                   String tipoContrato, String departamento, String municipio, Integer conteo2013) {
        this.id = id;
        this.codigoInstitucion = codigoInstitucion;
        this.nombreIES = nombreIES;
        this.generoDocente = generoDocente;
        this.tipoDocumento = tipoDocumento;
        this.nivelFormacion = nivelFormacion;
        this.tiempoDedicacion = tiempoDedicacion;
        this.tipoContrato = tipoContrato;
        this.departamento = departamento;
        this.municipio = municipio;
        this.conteo2013 = conteo2013;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCodigoInstitucion() { return codigoInstitucion; }
    public void setCodigoInstitucion(String codigoInstitucion) { this.codigoInstitucion = codigoInstitucion; }
    public String getNombreIES() { return nombreIES; }
    public void setNombreIES(String nombreIES) { this.nombreIES = nombreIES; }
    public String getGeneroDocente() { return generoDocente; }
    public void setGeneroDocente(String generoDocente) { this.generoDocente = generoDocente; }
    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
    public String getNivelFormacion() { return nivelFormacion; }
    public void setNivelFormacion(String nivelFormacion) { this.nivelFormacion = nivelFormacion; }
    public String getTiempoDedicacion() { return tiempoDedicacion; }
    public void setTiempoDedicacion(String tiempoDedicacion) { this.tiempoDedicacion = tiempoDedicacion; }
    public String getTipoContrato() { return tipoContrato; }
    public void setTipoContrato(String tipoContrato) { this.tipoContrato = tipoContrato; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }
    public Integer getConteo2013() { return conteo2013; }
    public void setConteo2013(Integer conteo2013) { this.conteo2013 = conteo2013; }
}
