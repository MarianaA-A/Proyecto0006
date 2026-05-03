package com.backend.dao;

import com.backend.model.Docente;
import com.backend.exception.DAOException;
import java.util.List;

public interface DocenteDAO {
    List<Docente> getAll() throws DAOException;
    Docente getById(String id) throws DAOException;
    void create(Docente d) throws DAOException;
    void update(Docente d) throws DAOException;
    void delete(String id) throws DAOException;
}
