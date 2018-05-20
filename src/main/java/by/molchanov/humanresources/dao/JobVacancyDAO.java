package by.molchanov.humanresources.dao;

import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.JobVacancyStatusType;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.util.List;

/**
 * Interface {@link JobVacancyDAO} is used for job vacancy dao.
 * Contains specified method for job vacancy dao.
 *
 * @author MolchanovVladislav
 */
public interface JobVacancyDAO extends OverallDAO<JobVacancy> {
    List<JobVacancy> findVacancyInfoByType(JobVacancyStatusType jobVacancyStatusType) throws CustomDAOException;
}
