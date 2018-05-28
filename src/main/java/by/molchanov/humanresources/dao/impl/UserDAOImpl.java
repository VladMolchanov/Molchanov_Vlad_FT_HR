package by.molchanov.humanresources.dao.impl;

import by.molchanov.humanresources.dao.UserDAO;
import by.molchanov.humanresources.database.ConnectionPool;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.entity.UserStatusType;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.molchanov.humanresources.dao.SQLQueryVariable.*;

/**
 * Class {@link UserDAOImpl} used for work with database table 'user'.
 * Contains specified method for work with table 'user'.
 *
 * @author MolchanovVladislav
 * @see UserDAO
 * @see AbstractDAO
 */
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {
    private static final UserDAOImpl USER_DAO = new UserDAOImpl();

    private UserDAOImpl() {

    }

    public static UserDAOImpl getInstance() {
        return USER_DAO;
    }

    @Override
    public void updateUserOrgIdRole(User user) throws CustomDAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(USER_QUERY_ROLE_ORG_ID_UPDATE)) {
                statement.setString(1, user.getRole().getValue());
                statement.setInt(2, user.getOrganization().getId());
                statement.setInt(3, user.getId());
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
    public List<User> findUsersByEmailAndPassword(String email, String password) throws CustomDAOException {
        List<User> result;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(USER_QUERY_SELECT_USER_BY_EMAIL_PASS)) {
                statement.setString(1, email);
                statement.setString(2, password);
                try (ResultSet set = statement.executeQuery()) {
                    result = parseResultSet(set);
                }
            } catch (SQLException e) {
                throw new CustomDAOException(e);
            }
            if (result.size() > 1) {
                throw new CustomDAOException("Selection error while getting role by e-mail and password!");
            }
        } finally {
            if (connection != null) {
                connectionPool.returnConnection(connection);
            }
        }
        return result;
    }

    @Override
    public List<User> findPartOfUsers(UserStatusType userStatusType, int startUserNumber,
                                      int usersQuantity) throws CustomDAOException {
        List<User> result = new ArrayList<>();
        User user;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(USER_QUERY_SELECT_EXCLUDING_ROLE)) {
                statement.setString(1, userStatusType.getValue());
                statement.setInt(2, startUserNumber);
                statement.setInt(3, usersQuantity);
                try (ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        user = new User();
                        user.setEmail(set.getString(USER_FIELD_EMAIL));
                        user.setId(set.getInt(USER_FIELD_ID));
                        Organization organization = new Organization();
                        organization.setName(set.getString(ORGANIZATION_FIELD_NAME));
                        user.setOrganization(organization);
                        user.setFirstName(set.getString(USER_FIELD_FIRST_NAME));
                        user.setLastName(set.getString(USER_FIELD_LAST_NAME));
                        user.setRole(UserStatusType.valueOf(set.getString(USER_FIELD_ROLE).toUpperCase()));
                        result.add(user);
                    }
                }
            } catch (SQLException e) {
                throw new CustomDAOException(e);
            }
        } finally {
            if (connection != null) {
                connectionPool.returnConnection(connection);
            }
        }
        return result;
    }

    @Override
    public int findUsersCount(UserStatusType userStatusType) throws CustomDAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        int count = 0;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(USERS_COUNT_SELECT)) {
                statement.setString(1, userStatusType.getValue());
                try (ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        count = set.getInt(USERS_COUNT);
                    }
                }
            } catch (SQLException e) {
                throw new CustomDAOException("SQL execute error!", e);
            }
        } finally {
            if (connection != null) {
                connectionPool.returnConnection(connection);
            }
        }
        return count;
    }

    @Override
    String getSelectQueryBase() {
        return USER_QUERY_SELECT;
    }

    @Override
    String getUpdateQueryBase() {
        return USER_QUERY_UPDATE;
    }

    @Override
    String getDeleteQueryBase() {
        return USER_QUERY_DELETE_BY_ID;
    }

    @Override
    String getCreateQueryBase() {
        return USER_QUERY_CREATE;
    }

    @Override
    void preparedStatementForUpdate(PreparedStatement statement, User object) throws CustomDAOException {
        try {
            statement.setString(1, object.getEmail());
            statement.setString(2, object.getRole().getValue());
            statement.setString(3, object.getPass());
            statement.setString(4, object.getFirstName());
            statement.setString(5, object.getLastName());
            if (object.getOrganization().getId() == 0) {
                statement.setString(6, null);
            } else {
                statement.setInt(6, object.getOrganization().getId());
            }
            statement.setInt(7, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for update!", e);
        }
    }

    @Override
    void preparedStatementForDelete(PreparedStatement statement, User object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for delete!", e);
        }
    }

    @Override
    void preparedStatementForInsert(PreparedStatement statement, User object) throws CustomDAOException {
        try {
            statement.setString(2, object.getRole().getValue());
            statement.setString(1, object.getEmail());
            statement.setString(3, object.getPass());
            statement.setString(4, object.getFirstName());
            statement.setString(5, object.getLastName());
            statement.setString(6, null);
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for insert!", e);
        }
    }

    @Override
    String getPKName() {
        return USER_FIELD_ID;
    }

    @Override
    List<User> parseResultSet(ResultSet set) throws CustomDAOException {
        List<User> result = new ArrayList<>();
        User user;
        try {
            while (set.next()) {
                user = new User();
                user.setEmail(set.getString(USER_FIELD_EMAIL));
                user.setId(set.getInt(USER_FIELD_ID));
                Organization organization = new Organization();
                organization.setId(set.getInt(USER_FIELD_ORGANIZATION_ID));
                user.setOrganization(organization);
                user.setFirstName(set.getString(USER_FIELD_FIRST_NAME));
                user.setLastName(set.getString(USER_FIELD_LAST_NAME));
                user.setPass(set.getString(USER_FIELD_PASS));
                user.setRole(UserStatusType.valueOf(set.getString(USER_FIELD_ROLE).toUpperCase()));
                result.add(user);
            }
        } catch (SQLException e) {
            throw new CustomDAOException("Parsing result set error!", e);
        }
        return result;
    }

    @Override
    boolean objectHasId(User object) {
        return object.getId() != 0;
    }
}
