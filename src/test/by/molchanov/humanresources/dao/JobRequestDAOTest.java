package by.molchanov.humanresources.dao;

import by.molchanov.humanresources.dao.impl.JobRequestDAOImpl;
import by.molchanov.humanresources.dao.impl.JobVacancyDAOImpl;
import by.molchanov.humanresources.dao.impl.OrganizationDAOImpl;
import by.molchanov.humanresources.dao.impl.UserDAOImpl;
import by.molchanov.humanresources.entity.*;
import by.molchanov.humanresources.exception.CustomDAOException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class JobRequestDAOTest {
    private static Organization testOrganization;
    private static JobVacancy testJobVacancy;
    private static User testUser;

    @BeforeClass
    public void entityInit() {
        OrganizationDAO organizationDAO = OrganizationDAOImpl.getInstance();
        JobVacancyDAO jobVacancyDAO = JobVacancyDAOImpl.getInstance();
        UserDAO userDAO = UserDAOImpl.getInstance();

        Organization organization = new Organization();
        String testString = "This is test string";
        organization.setName(testString);
        organization.setType(OrganizationType.COMMERCIAL);
        organization.setDescription(testString);

        try {
            testOrganization = organizationDAO.persist(organization);
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }

        JobVacancy jobVacancy = new JobVacancy();
        jobVacancy.setName(testString);
        jobVacancy.setOrganization(testOrganization);
        jobVacancy.setRequirement(testString);
        jobVacancy.setStatus(JobVacancyStatusType.CLOSE);

        try {
            testJobVacancy = jobVacancyDAO.persist(jobVacancy);
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }

        User user = new User();
        user.setEmail(testString);
        user.setFirstName(testString);
        user.setLastName(testString);
        user.setPass(testString);
        user.setRole(UserStatusType.ASPIRANT);

        try {
            testUser = userDAO.persist(user);
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @AfterClass
    public void entityDestroy() {
        OrganizationDAO organizationDAO = OrganizationDAOImpl.getInstance();
        UserDAO userDAO = UserDAOImpl.getInstance();

        try {
            organizationDAO.delete(testOrganization);
            userDAO.delete(testUser);
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @Test
    public void findAllRequestTest() {
        JobRequestDAO jobRequestDAO = JobRequestDAOImpl.getInstance();
        List<JobRequest> requests = null;
        try {
            requests = jobRequestDAO.findAll();
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }
        Assert.assertNotNull(requests);
    }

    @Test
    public void persistRequestTest() {
        JobRequestDAO jobRequestDAO = JobRequestDAOImpl.getInstance();
        List<JobRequest> requests;
        String testString = "This is test string";
        JobRequest jobRequest = new JobRequest();
        jobRequest.setJobVacancy(testJobVacancy);
        jobRequest.setResume(testString);
        jobRequest.setUser(testUser);
        jobRequest.setStatus(JobRequestStatusType.ADDED);
        int primaryRequestsSize = 0;
        int newRequestsSize = 0;

        try {
            requests = jobRequestDAO.findAll();
            primaryRequestsSize = requests.size();
            jobRequest = jobRequestDAO.persist(jobRequest);
            requests = jobRequestDAO.findAll();
            newRequestsSize = requests.size();
            jobRequestDAO.delete(jobRequest);
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }
        Assert.assertNotEquals(primaryRequestsSize, newRequestsSize);
    }

    @Test
    public void findRequestByIdTest() {
        JobRequestDAO jobRequestDAO = JobRequestDAOImpl.getInstance();
        String testString = "This is test string";
        JobRequest firstJobRequest = new JobRequest();
        firstJobRequest.setJobVacancy(testJobVacancy);
        firstJobRequest.setResume(testString);
        firstJobRequest.setUser(testUser);
        firstJobRequest.setStatus(JobRequestStatusType.ADDED);
        JobRequest secondJobRequest = null;
        int requestId;
        try {
            firstJobRequest = jobRequestDAO.persist(firstJobRequest);
            requestId = firstJobRequest.getId();
            secondJobRequest = jobRequestDAO.findById(requestId);
            jobRequestDAO.delete(firstJobRequest);
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }
        Assert.assertEquals(firstJobRequest, secondJobRequest);
    }

    @Test(expectedExceptions = CustomDAOException.class)
    public void deleteRequest() throws CustomDAOException {
        JobRequestDAO jobRequestDAO = JobRequestDAOImpl.getInstance();
        String testString = "This is test string";
        JobRequest jobRequest = new JobRequest();
        jobRequest.setJobVacancy(testJobVacancy);
        jobRequest.setResume(testString);
        jobRequest.setUser(testUser);
        jobRequest.setStatus(JobRequestStatusType.ADDED);
        int jobRequestId = 0;
        try {
            jobRequest = jobRequestDAO.persist(jobRequest);
            jobRequestId = jobRequest.getId();
            jobRequestDAO.delete(jobRequest);
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }
        jobRequestDAO.findById(jobRequestId);
    }

    @Test
    public void updateRequest() {
        JobRequestDAO jobRequestDAO = JobRequestDAOImpl.getInstance();
        String testString = "This is test string";
        JobRequest firstJobRequest = new JobRequest();
        JobRequest secondJobRequest = null;
        firstJobRequest.setJobVacancy(testJobVacancy);
        firstJobRequest.setResume(testString);
        firstJobRequest.setUser(testUser);
        firstJobRequest.setStatus(JobRequestStatusType.ADDED);
        int jobRequestId;
        try {
            firstJobRequest = jobRequestDAO.persist(firstJobRequest);
            jobRequestId = firstJobRequest.getId();
            secondJobRequest = jobRequestDAO.findById(jobRequestId);
            firstJobRequest.setResume(testString + testString);
            jobRequestDAO.update(firstJobRequest);
            firstJobRequest = jobRequestDAO.findById(jobRequestId);
            jobRequestDAO.delete(firstJobRequest);
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }
        Assert.assertNotEquals(firstJobRequest, secondJobRequest);
    }

    @Test
    public void findRequestByTypeRoleTest() {
        JobRequestDAO jobRequestDAO = JobRequestDAOImpl.getInstance();
        String testString = "This is test string";
        JobRequest firstJobRequest = new JobRequest();
        firstJobRequest.setJobVacancy(testJobVacancy);
        firstJobRequest.setResume(testString);
        firstJobRequest.setUser(testUser);
        firstJobRequest.setStatus(JobRequestStatusType.ADDED);
        JobRequest secondJobRequest = new JobRequest();
        secondJobRequest.setJobVacancy(testJobVacancy);
        secondJobRequest.setResume(testString);
        secondJobRequest.setUser(testUser);
        secondJobRequest.setStatus(JobRequestStatusType.ADDED);
        int orgId = testOrganization.getId();
        List<JobRequest> requests = new ArrayList<>();
        try {
            firstJobRequest = jobRequestDAO.persist(firstJobRequest);
            secondJobRequest = jobRequestDAO.persist(secondJobRequest);
            requests = jobRequestDAO.findRequestByTypeRole(JobRequestStatusType.ADDED , orgId, "" , 0 , 10);
            jobRequestDAO.delete(firstJobRequest);
            jobRequestDAO.delete(secondJobRequest);
        } catch (CustomDAOException e) {
            Assert.fail(e.getMessage(), e);
        }
        int rightSize = 2;
        Assert.assertEquals(rightSize, requests.size());
    }
}
