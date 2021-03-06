package by.molchanov.humanresources.dao.impl;

import by.molchanov.humanresources.dao.JobRequestDAO;
import by.molchanov.humanresources.database.ConnectionPool;
import by.molchanov.humanresources.entity.*;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.molchanov.humanresources.dao.SQLQueryVariable.*;
import static by.molchanov.humanresources.entity.JobRequestStatusType.*;

/**
 * Class {@link JobRequestDAOImpl} used for work with database table 'job_request'.
 * Contains specified method for work with table 'job_request'.
 *
 * @author MolchanovVladislav
 * @see JobRequestDAO
 * @see AbstractDAO
 */
public class JobRequestDAOImpl extends AbstractDAO<JobRequest> implements JobRequestDAO {
    private static final JobRequestDAOImpl JOB_REQUEST_DAO = new JobRequestDAOImpl();

    private static final String PERCENT_SIGN = "%";

    private JobRequestDAOImpl() {

    }

    public static JobRequestDAOImpl getInstance() {
        return JOB_REQUEST_DAO;
    }

    @Override
    public List<JobRequest> findRequestByTypeRole(JobRequestStatusType jobRequestStatusType, int orgId, String searchField,
                                                  int startRequestNumber,
                                                  int requestsQuantity) throws CustomDAOException {
        List<JobRequest> result = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(JOB_REQUEST_QUERY_SELECT_REQUEST_CONTENT)) {
                statement.setString(1, jobRequestStatusType.getValue());
                statement.setInt(2, orgId);
                statement.setString(3, PERCENT_SIGN + searchField + PERCENT_SIGN);
                statement.setInt(4, startRequestNumber);
                statement.setInt(5, requestsQuantity);
                try (ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        JobRequest jobRequest = new JobRequest();
                        jobRequest.setResume(set.getString(JOB_REQUEST_FIELD_RESUME));
                        jobRequest.setId(set.getInt(JOB_REQUEST_FIELD_ID));
                        jobRequest.setStatus(JobRequestStatusType.valueOf(set.getString(JOB_REQUEST_FIELD_STATUS).toUpperCase()));
                        JobVacancy jobVacancy = new JobVacancy();
                        jobVacancy.setName(set.getString(JOB_VACANCY_FIELD_NAME));
                        User user = new User();
                        user.setEmail(set.getString(USER_FIELD_EMAIL));
                        user.setId(set.getInt(USER_FIELD_ID));
                        jobRequest.setUser(user);
                        jobRequest.setJobVacancy(jobVacancy);
                        result.add(jobRequest);
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
    public int findRequestsCount(JobRequestStatusType jobRequestStatusType, String searchField, int orgId) throws CustomDAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        int count = 0;
        try {
            connection = connectionPool.takeConnection();
            try (PreparedStatement statement = connection.prepareStatement(JOB_REQUESTS_COUNT_SELECT)) {
                statement.setString(1, jobRequestStatusType.getValue());
                statement.setInt(2, orgId);
                statement.setString(3, PERCENT_SIGN + searchField + PERCENT_SIGN);
                try (ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        count = set.getInt(JOB_REQUESTS_COUNT);
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
        return JOB_REQUEST_QUERY_SELECT;
    }

    @Override
    String getUpdateQueryBase() {
        return JOB_REQUEST_QUERY_UPDATE;
    }

    @Override
    String getDeleteQueryBase() {
        return JOB_REQUEST_QUERY_DELETE_BY_ID;
    }

    @Override
    String getCreateQueryBase() {
        return JOB_REQUEST_QUERY_CREATE;
    }

    @Override
    void preparedStatementForUpdate(PreparedStatement statement, JobRequest object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getJobVacancy().getId());
            statement.setInt(2, object.getUser().getId());
            statement.setString(3, object.getResume());
            statement.setString(4, object.getStatus().getValue());
            statement.setInt(5, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for update!", e);
        }
    }

    @Override
    void preparedStatementForDelete(PreparedStatement statement, JobRequest object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getId());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for delete!", e);
        }
    }

    @Override
    void preparedStatementForInsert(PreparedStatement statement, JobRequest object) throws CustomDAOException {
        try {
            statement.setInt(1, object.getJobVacancy().getId());
            statement.setInt(2, object.getUser().getId());
            statement.setString(3, object.getResume());
            statement.setString(4, object.getStatus().getValue());
        } catch (SQLException e) {
            throw new CustomDAOException("Statement error for insert!", e);
        }
    }

    @Override
    String getPKName() {
        return JOB_REQUEST_FIELD_ID;
    }

    @Override
    List<JobRequest> parseResultSet(ResultSet set) throws CustomDAOException {
        List<JobRequest> result = new ArrayList<>();
        JobRequest jobRequest;
        try {
            while (set.next()) {
                jobRequest = new JobRequest();
                jobRequest.setId(set.getInt(JOB_REQUEST_FIELD_ID));
                JobVacancy jobVacancy = new JobVacancy();
                jobVacancy.setId(set.getInt(JOB_REQUEST_FIELD_JOB_VACANCY_ID));
                jobRequest.setJobVacancy(jobVacancy);
                User user = new User();
                user.setId(set.getInt(JOB_REQUEST_FIELD_USER_ID));
                jobRequest.setUser(user);
                jobRequest.setResume(set.getString(JOB_REQUEST_FIELD_RESUME));
                jobRequest.setStatus(valueOf(set.getString(JOB_REQUEST_FIELD_STATUS).toUpperCase()));
                result.add(jobRequest);
            }
        } catch (SQLException e) {
            throw new CustomDAOException("Parsing result set error!", e);
        }
        return result;
    }

    @Override
    boolean objectHasId(JobRequest object) {
        return object.getId() != 0;
    }

}
