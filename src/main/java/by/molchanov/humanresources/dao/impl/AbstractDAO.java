package by.molchanov.humanresources.dao.impl;

import by.molchanov.humanresources.dao.OverallDAO;
import by.molchanov.humanresources.database.ConnectionPool;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T> implements OverallDAO<T> {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    abstract String getSelectQueryBase();

    abstract String getUpdateQueryBase();

    abstract String getDeleteQueryBase();

    abstract String getCreateQueryBase();

    abstract void preparedStatementForUpdate(PreparedStatement statement, T object) throws CustomDAOException;

    abstract void preparedStatementForDelete(PreparedStatement statement, T object) throws CustomDAOException;

    abstract void preparedStatementForInsert(PreparedStatement statement, T object) throws CustomDAOException;

    abstract String getPKName();

    abstract List<T> parseResultSet(ResultSet set) throws CustomDAOException;

    abstract boolean objectHasId(T object);

    @Override
    public T findById(int id) throws CustomDAOException {
        List<T> selection;
        String sqlScript = getSelectQueryBase();
        sqlScript = sqlScript.concat(String.format("WHERE %1$s = ?", getPKName()));
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
                statement.setInt(1, id);
                try (ResultSet set = statement.executeQuery()) {
                    selection = parseResultSet(set);
                }
            } catch (SQLException e) {
                throw new CustomDAOException(e);
            }
            if (selection == null || selection.size() != 1) {
                throw new CustomDAOException("Selection error while getting element by id!");
            } else if (selection.size() > 1) {
                throw new CustomDAOException("Received more than one element! ");
            }
        } finally {
            if (connection != null) {
                connectionPool.returnConnection(connection);
            }
        }
        return selection.iterator().next();
    }

    @Override
    public T persist(T object) throws CustomDAOException {
        if (objectHasId(object)) {
            throw new CustomDAOException("Object already created!");
        }
        T createdElement;
        List<T> selection;
        String sqlScript = getCreateQueryBase();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
                preparedStatementForInsert(statement, object);
                int numberOfInsertingElements = statement.executeUpdate();
                if (numberOfInsertingElements > 1) {
                    throw new CustomDAOException("Was created more than one element!");
                }
            } catch (SQLException e) {
                throw new CustomDAOException(e);
            }
            sqlScript = getSelectQueryBase();
            sqlScript = sqlScript.concat(String.format(" WHERE %1$s = last_insert_id()", getPKName()));
            try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
                try (ResultSet set = statement.executeQuery()) {
                    selection = parseResultSet(set);
                }
            } catch (SQLException e) {
                throw new CustomDAOException(e);
            }
            if (selection == null || selection.size() != 1) {
                throw new CustomDAOException("Selection error while getting element by id!");
            }
            createdElement = selection.iterator().next();
        } finally {
            if (connection != null) {
                connectionPool.returnConnection(connection);
            }
        }
        return createdElement;
    }

    @Override
    public void delete(T object) throws CustomDAOException {
        String sqlScript = getDeleteQueryBase();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
                preparedStatementForDelete(statement, object);
                int numberOfUpdatedElements = statement.executeUpdate();
                if (numberOfUpdatedElements > 1) {
                    throw new CustomDAOException("More than one element was deleted:" + numberOfUpdatedElements);
                }
            } catch (SQLException e) {
                throw new CustomDAOException(e);
            }
        } finally {
            if (connection != null) {
                connectionPool.returnConnection(connection);
            }
        }
    }

    @Override
    public void update(T object) throws CustomDAOException {
        String sqlScript = getUpdateQueryBase();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
                preparedStatementForUpdate(statement, object);
                int numberOfUpdatedElements = statement.executeUpdate();
                if (numberOfUpdatedElements > 1) {
                    throw new CustomDAOException("More than one element was updated:" + numberOfUpdatedElements);
                }
            } catch (SQLException e) {
                throw new CustomDAOException(e);
            }
        } finally {
            if (connection != null) {
                connectionPool.returnConnection(connection);
            }
        }
    }

    @Override
    public List<T> findAll() throws CustomDAOException {
        List<T> selection;
        String sqlScript = getSelectQueryBase();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
                try (ResultSet set = statement.executeQuery()) {
                    selection = parseResultSet(set);
                }
            } catch (SQLException e) {
                throw new CustomDAOException(e);
            }
            if (selection == null) {
                throw new CustomDAOException("Selection error while getting all elements!");
            }
        } finally {
            if (connection != null) {
                connectionPool.returnConnection(connection);
            }
        }
        return selection;
    }
}
