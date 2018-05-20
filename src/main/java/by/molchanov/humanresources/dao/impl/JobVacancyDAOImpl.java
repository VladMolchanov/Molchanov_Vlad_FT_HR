package by.molchanov.humanresources.dao.impl;

import by.molchanov.humanresources.dao.JobVacancyDAO;
import by.molchanov.humanresources.database.ConnectionPool;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.JobVacancyStatusType;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static by.molchanov.humanresources.dao.SQLQueryVariable.*;

/**
 * Class {@link JobVacancyDAOImpl} used for work with database table 'job_vacancy'.
 * Contains specified method for work with table 'job_vacancy'.
 *
 * @author MolchanovVladislav
 * @see JobVacancyDAO
 * @see AbstractDAO
 */
public class JobVacancyDAOImpl extends AbstractDAO<JobVacancy> implements JobVacancyDAO {
    private static final JobVacancyDAOImpl JOB_REQUEST_DAO = new JobVacancyDAOImpl();

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private JobVacancyDAOImpl() {}

    public static JobVacancyDAOImpl getInstance() {
        return JOB_REQUEST_DAO;
    }

    @Override
    public List<JobVacancy> findVacancyInfoByType(JobVacancyStatusType jobVacancyStatusType) throws CustomDAOException {
        List<JobVacancy> result = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(JOB_VACANCY_QUERY_SELECT_VACANCY_CONTENT)) {
                statement.setString(1, jobVacancyStatusType.getValue());
                try (ResultSet set = statement.executeQuery()) {
                    JobVacancy jobVacancy;
                    while (set.next()) {
                        jobVacancy = new JobVacancy();
                        jobVacancy.setId(set.getInt(JOB_VACANCY_FIELD_ID));
                        jobVacancy.setName(set.getString(JOB_VACANCY_FIELD_NAME));
                        Organization organization = new Organization();
                        organization.setId(set.getInt(ORGANIZATION_FIELD_ID));
                        jobVacancy.setOrganization(organization);
                        jobVacancy.getOrganization().setName(set.getString(ORGANIZATION_FIELD_NAME));
                        jobVacancy.getOrganization().setWebsite(set.getString(ORGANIZATION_FIELD_WEBSITE));
                        jobVacancy.setUploadDate(set.getString(JOB_VACANCY_FIELD_UPLOAD_DATE));
                        jobVacancy.setRequirement(set.getString(JOB_VACANCY_FIELD_REQUIREMENT));
                        jobVacancy.setStatus(JobVacancyStatusType.valueOf(set.getString(JOB_VACANCY_FIELD_STATUS).toUpperCase()));
                        result.add(jobVacancy);
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
        return result;
    }

    @Override
    String getSelectQueryBase() {
        return JOB_VACANCY_QUERY_SELECT;
    }

    @Override
    String getUpdateQueryBase() {
        return JOB_VACANCY_QUERY_UPDATE;
    }

    @Override
    String getDeleteQueryBase() {
        return JOB_VACANCY_QUERY_DELETE_BY_ID;
    }

    @Override
    String getCreateQueryBase() {
        return JOB_VACANCY_QUERY_CREATE;
    }

    @Override
    void preparedStatementForUpdate(PreparedStatement statement, JobVacancy object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getOrganization().getId());
            statement.setString(2, object.getName());
            statement.setString(4, object.getRequirement());
            String timeStamp = new SimpleDateFormat(DATE_PATTERN).format(Calendar.getInstance().getTime());
            object.setUploadDate(timeStamp);
            statement.setString(3, timeStamp);
            statement.setString(5, object.getStatus().getValue());
            statement.setInt(6, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for update!", e);
        }
    }

    @Override
    void preparedStatementForDelete(PreparedStatement statement, JobVacancy object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for delete!", e);
        }
    }

    @Override
    void preparedStatementForInsert(PreparedStatement statement, JobVacancy object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getOrganization().getId());
            statement.setString(2, object.getName());
            String timeStamp = new SimpleDateFormat(DATE_PATTERN).format(Calendar.getInstance().getTime());
            object.setUploadDate(timeStamp);
            statement.setString(3, timeStamp);
            statement.setString(4, object.getRequirement());
            statement.setString(5, object.getStatus().getValue());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for insert!", e);
        }
    }

    @Override
    String getPKName() {
        return JOB_VACANCY_FIELD_ID;
    }

    @Override
    List<JobVacancy> parseResultSet(ResultSet set) throws CustomDAOException {
        List<JobVacancy> result = new ArrayList<>();
        JobVacancy jobVacancy;
        try {
            while (set.next()) {
                jobVacancy = new JobVacancy();
                jobVacancy.setId(set.getInt(JOB_VACANCY_FIELD_ID));
                jobVacancy.setName(set.getString(JOB_VACANCY_FIELD_NAME));
                Organization organization = new Organization();
                organization.setId(set.getInt(JOB_VACANCY_FIELD_ORGANIZATION_ID));
                jobVacancy.setOrganization(organization);
                jobVacancy.setUploadDate(set.getString(JOB_VACANCY_FIELD_UPLOAD_DATE));
                jobVacancy.setRequirement(set.getString(JOB_VACANCY_FIELD_REQUIREMENT));
                jobVacancy.setStatus(JobVacancyStatusType.valueOf(set.getString(JOB_VACANCY_FIELD_STATUS).toUpperCase()));
                result.add(jobVacancy);
            }
        } catch (SQLException e) {
            throw new CustomDAOException("Parsing result set error!", e);
        }
        return result;
    }

    @Override
    boolean objectHasId(JobVacancy object) {
        return object.getId() != 0;
    }
}
