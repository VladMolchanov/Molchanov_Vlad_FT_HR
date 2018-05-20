package by.molchanov.humanresources.dao;

import by.molchanov.humanresources.exception.CustomDAOException;

import java.util.List;

public interface OverallDAO <T> {
    T findById(int id) throws CustomDAOException;
    T persist(T object) throws CustomDAOException;
    void delete(T object) throws CustomDAOException;
    void update(T object) throws CustomDAOException;
    List<T> findAll() throws CustomDAOException;
}
