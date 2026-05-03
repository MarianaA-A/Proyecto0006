package com.backend.dao;

import com.backend.db.MySQLConnection;
import com.backend.exception.DAOException;
import com.backend.model.Docente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAOImpl implements DocenteDAO {

    @Override
    public List<Docente> getAll() throws DAOException {
        String sql = "SELECT id, codigoInstitucion, nombreIES, generoDocente, tipoDocumento, nivelFormacion, tiempoDedicacion, tipoContrato, departamento, municipio, conteo2013 FROM docentes";
        try (Connection c = MySQLConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            List<Docente> lista = new ArrayList<>();
            while (rs.next()) {
                Docente d = new Docente();
                d.setId(String.valueOf(rs.getInt("id")));
                d.setCodigoInstitucion(rs.getString("codigoInstitucion"));
                d.setNombreIES(rs.getString("nombreIES"));
                d.setGeneroDocente(rs.getString("generoDocente"));
                d.setTipoDocumento(rs.getString("tipoDocumento"));
                d.setNivelFormacion(rs.getString("nivelFormacion"));
                d.setTiempoDedicacion(rs.getString("tiempoDedicacion"));
                d.setTipoContrato(rs.getString("tipoContrato"));
                d.setDepartamento(rs.getString("departamento"));
                d.setMunicipio(rs.getString("municipio"));
                d.setConteo2013(rs.getInt("conteo2013"));
                lista.add(d);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Error leyendo docentes", e);
        }
    }

    @Override
    public Docente getById(String id) throws DAOException {
        String sql = "SELECT id, codigoInstitucion, nombreIES, generoDocente, tipoDocumento, nivelFormacion, tiempoDedicacion, tipoContrato, departamento, municipio, conteo2013 FROM docentes WHERE id = ?";
        try (Connection c = MySQLConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Docente d = new Docente();
                    d.setId(String.valueOf(rs.getInt("id")));
                    d.setCodigoInstitucion(rs.getString("codigoInstitucion"));
                    d.setNombreIES(rs.getString("nombreIES"));
                    d.setGeneroDocente(rs.getString("generoDocente"));
                    d.setTipoDocumento(rs.getString("tipoDocumento"));
                    d.setNivelFormacion(rs.getString("nivelFormacion"));
                    d.setTiempoDedicacion(rs.getString("tiempoDedicacion"));
                    d.setTipoContrato(rs.getString("tipoContrato"));
                    d.setDepartamento(rs.getString("departamento"));
                    d.setMunicipio(rs.getString("municipio"));
                    d.setConteo2013(rs.getInt("conteo2013"));
                    return d;
                }
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener docente por id", e);
        }
    }

    @Override
    public void create(Docente d) throws DAOException {
        String sql = "INSERT INTO docentes (codigoInstitucion, nombreIES, generoDocente, tipoDocumento, nivelFormacion, tiempoDedicacion, tipoContrato, departamento, municipio, conteo2013) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = MySQLConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, d.getCodigoInstitucion());
            ps.setString(2, d.getNombreIES());
            ps.setString(3, d.getGeneroDocente());
            ps.setString(4, d.getTipoDocumento());
            ps.setString(5, d.getNivelFormacion());
            ps.setString(6, d.getTiempoDedicacion());
            ps.setString(7, d.getTipoContrato());
            ps.setString(8, d.getDepartamento());
            ps.setString(9, d.getMunicipio());
            ps.setInt(10, d.getConteo2013() == null ? 0 : d.getConteo2013());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    d.setId(String.valueOf(keys.getInt(1)));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error creando docente", e);
        }
    }

    @Override
    public void update(Docente d) throws DAOException {
        if (d.getId() == null) throw new DAOException("Docente sin id para actualizar");
        String sql = "UPDATE docentes SET codigoInstitucion=?, nombreIES=?, generoDocente=?, tipoDocumento=?, nivelFormacion=?, tiempoDedicacion=?, tipoContrato=?, departamento=?, municipio=?, conteo2013=? WHERE id = ?";
        try (Connection c = MySQLConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, d.getCodigoInstitucion());
            ps.setString(2, d.getNombreIES());
            ps.setString(3, d.getGeneroDocente());
            ps.setString(4, d.getTipoDocumento());
            ps.setString(5, d.getNivelFormacion());
            ps.setString(6, d.getTiempoDedicacion());
            ps.setString(7, d.getTipoContrato());
            ps.setString(8, d.getDepartamento());
            ps.setString(9, d.getMunicipio());
            ps.setInt(10, d.getConteo2013() == null ? 0 : d.getConteo2013());
            ps.setInt(11, Integer.parseInt(d.getId()));
            int updated = ps.executeUpdate();
            if (updated == 0) throw new DAOException("No se encontró docente para actualizar");
        } catch (SQLException e) {
            throw new DAOException("Error actualizando docente", e);
        }
    }

    @Override
    public void delete(String id) throws DAOException {
        String sql = "DELETE FROM docentes WHERE id = ?";
        try (Connection c = MySQLConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            int del = ps.executeUpdate();
            if (del == 0) throw new DAOException("No se encontró docente para eliminar");
        } catch (SQLException e) {
            throw new DAOException("Error eliminando docente", e);
        }
    }
}
