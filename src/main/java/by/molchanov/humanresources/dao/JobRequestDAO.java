package by.molchanov.humanresources.dao;

import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.util.List;

/**
 * Interface {@link JobRequestDAO} is used for job request dao.
 * Contains specified method for job request dao.
 *
 * @author MolchanovVladislav
 */
public interface JobRequestDAO extends OverallDAO<JobRequest> {
    List<JobRequest> findRequestByTypeRole(String userRole, int orgId) throws CustomDAOException;
}
