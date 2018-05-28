package by.molchanov.humanresources.dao;

import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.exception.CustomDAOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class JobVacancyDAOTest {
    private Organization organization;

    @Test
    public void findAllVacancyTest() {
        JobVacancyDAO jobVacancyDAO = JobVacancyDAOImpl.getInstance();
        List<JobVacancy> vacancies = null;
        try {
            vacancies = jobVacancyDAO.findAll();
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }
        Assert.assertNotNull(vacancies);
    }

    @Test
    public void persistVacancyTest() {
        JobVacancyDAO jobVacancyDAO = JobVacancyDAOImpl.getInstance();
        List<JobVacancy> vacancies;
        String testString = "This is test string";
        JobVacancy jobVacancy = new JobVacancy();
        jobVacancy.setName(testString);
        jobVacancy.setRequirement(testString);
        int primaryVacanciesNumber = 0;
        int newVacanciesNumber = 0;
        try {
            vacancies = jobVacancyDAO.findAll();
            primaryVacanciesNumber = vacancies.size();
            jobVacancy = jobVacancyDAO.persist(jobVacancy);
            vacancies = jobVacancyDAO.findAll();
            newVacanciesNumber = vacancies.size();
            jobVacancyDAO.delete(jobVacancy);
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }
        Assert.assertNotEquals(primaryVacanciesNumber, newVacanciesNumber);
    }
}
