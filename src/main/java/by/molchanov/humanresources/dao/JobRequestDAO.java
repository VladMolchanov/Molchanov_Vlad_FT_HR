package by.molchanov.humanresources.dao;

import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobRequestStatusType;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.util.List;

/**
 * Interface {@link JobRequestDAO} is used for job request dao.
 * Contains specified method for job request dao.
 *
 * @author MolchanovVladislav
 */
public interface JobRequestDAO extends OverallDAO<JobRequest> {
    List<JobRequest> findRequestByTypeRole(JobRequestStatusType jobRequestStatusType, int orgId, String searchField,
                                           int startRequestNumber,
                                           int requestsQuantity) throws CustomDAOException;
    int findRequestsCount(JobRequestStatusType jobRequestStatusType, String searchField, int orgId) throws CustomDAOException;
}
