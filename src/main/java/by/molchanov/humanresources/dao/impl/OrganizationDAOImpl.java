package by.molchanov.humanresources.dao.impl;

import by.molchanov.humanresources.dao.OrganizationDAO;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.entity.OrganizationType;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.molchanov.humanresources.dao.SQLQueryVariable.*;

public class OrganizationDAOImpl extends AbstractDAO<Organization> implements OrganizationDAO {
    private static final OrganizationDAOImpl ORGANIZATION_DAO = new OrganizationDAOImpl();

    private OrganizationDAOImpl() {

    }

    public static OrganizationDAOImpl getInstance() {
        return ORGANIZATION_DAO;
    }

    @Override
    String getSelectQueryBase() {
        return ORGANIZATION_QUERY_SELECT;
    }

    @Override
    String getUpdateQueryBase() {
        return ORGANIZATION_QUERY_UPDATE;
    }

    @Override
    String getDeleteQueryBase() {
        return ORGANIZATION_QUERY_DELETE_BY_ID;
    }

    @Override
    String getCreateQueryBase() {
        return ORGANIZATION_QUERY_CREATE;
    }

    @Override
    void preparedStatementForUpdate(PreparedStatement statement, Organization object) throws CustomDAOException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getWebsite());
            statement.setString(3, object.getDescription());
            statement.setString(4, object.getType().getValue());
            statement.setInt(5, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for update!", e);
        }
    }

    @Override
    void preparedStatementForDelete(PreparedStatement statement, Organization object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for delete!", e);
        }
    }

    @Override
    void preparedStatementForInsert(PreparedStatement statement, Organization object) throws CustomDAOException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getWebsite());
            statement.setString(3, object.getDescription());
            statement.setString(4, object.getType().getValue());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for insert!", e);
        }
    }

    @Override
    String getPKName() {
        return ORGANIZATION_FIELD_ID;
    }

    @Override
    List<Organization> parseResultSet(ResultSet set) throws CustomDAOException {
        List<Organization> result = new ArrayList<>();
        Organization organization;
        try {
            while (set.next()) {
                organization = new Organization();
                organization.setId(set.getInt(ORGANIZATION_FIELD_ID));
                organization.setName(set.getString(ORGANIZATION_FIELD_NAME));
                organization.setDescription(set.getString(ORGANIZATION_FIELD_DESCRIPTION));
                organization.setWebsite(set.getString(ORGANIZATION_FIELD_WEBSITE));
                organization.setType(OrganizationType.valueOf(set.getString(ORGANIZATION_FIELD_TYPE).toUpperCase()));
                result.add(organization);
            }
        } catch (SQLException e) {
            throw new CustomDAOException("Parsing result set error!", e);
        }
        return result;
    }

    @Override
    boolean objectHasId(Organization object) {
        return object.getId() != 0;
    }
}
