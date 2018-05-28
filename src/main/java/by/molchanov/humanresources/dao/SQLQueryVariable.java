package by.molchanov.humanresources.dao;

/**
 * Class {@link SQLQueryVariable} contains all sql-query and field names for database.
 *
 * @author MolchanovVladislav
 */
public class SQLQueryVariable {
    public static final String USER_QUERY_SELECT = "SELECT u_id, u_email, u_role, u_password, u_firstname," +
            " u_lastname, u_organization_id FROM user ";
    public static final String USER_QUERY_UPDATE = "UPDATE user SET u_email = ?, u_role = ?, u_password = ?, u_firstname = ?," +
            " u_lastname = ?, u_organization_id = ? WHERE u_id = ?";
    public static final String USER_QUERY_ROLE_ORG_ID_UPDATE = "UPDATE user SET u_role = ?, u_organization_id = ? WHERE u_id = ?";
    public static final String USER_QUERY_DELETE_BY_ID = "DELETE FROM user WHERE u_id = ?";
    public static final String USER_QUERY_CREATE = "INSERT INTO user (u_email, u_role, u_password, u_firstname," +
            " u_lastname, u_organization_id) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String USER_QUERY_SELECT_USER_BY_EMAIL_PASS = "SELECT u_id, u_email, u_role, u_password, u_firstname," +
            " u_lastname, u_organization_id FROM user WHERE u_email = ? AND u_password = ?";
    public static final String USER_QUERY_SELECT_EXCLUDING_ROLE = "SELECT user.u_id, user.u_email, user.u_role, user.u_password, user.u_firstname," +
            " user.u_lastname, organization.o_name FROM user LEFT JOIN organization ON user.u_organization_id = organization.o_id " +
            "WHERE user.u_role != ? LIMIT ? , ?";
    public static final String USER_FIELD_ID = "u_id";
    public static final String USER_FIELD_EMAIL = "u_email";
    public static final String USER_FIELD_PASS = "u_password";
    public static final String USER_FIELD_FIRST_NAME = "u_firstname";
    public static final String USER_FIELD_LAST_NAME = "u_lastname";
    public static final String USER_FIELD_ORGANIZATION_ID = "u_organization_id";
    public static final String USER_FIELD_ROLE = "u_role";
    public static final String USERS_COUNT_SELECT = "SELECT COUNT(u_id) FROM user WHERE user.u_role != ?";
    public static final String USERS_COUNT = "COUNT(u_id)";


    public static final String ORGANIZATION_QUERY_SELECT = "SELECT o_id, o_name, o_description, o_website, o_type FROM organization ";
    public static final String ORGANIZATION_QUERY_SELECT_PART = "SELECT o_id, o_name, o_description, o_website, o_type FROM organization LIMIT ? , ?";
    public static final String ORGANIZATION_QUERY_UPDATE = "UPDATE organization SET o_name = ?, o_website = ?, o_description = ?, o_type = ? WHERE o_id = ";
    public static final String ORGANIZATION_QUERY_DELETE_BY_ID = "DELETE FROM organization WHERE o_id = ?";
    public static final String ORGANIZATION_QUERY_CREATE = "INSERT INTO organization (o_name, o_website, o_description, o_type) VALUES (?, ?, ?, ?)";
    public static final String ORGANIZATION_FIELD_ID = "o_id";
    public static final String ORGANIZATION_FIELD_NAME = "o_name";
    public static final String ORGANIZATION_FIELD_WEBSITE = "o_website";
    public static final String ORGANIZATION_FIELD_DESCRIPTION = "o_description";
    public static final String ORGANIZATION_FIELD_TYPE = "o_type";
    public static final String ORGANIZATIONS_COUNT_SELECT = "SELECT COUNT(o_id) FROM organization";
    public static final String ORGANIZATIONS_COUNT = "COUNT(o_id)";

    public static final String JOB_REQUEST_QUERY_SELECT = "SELECT jr_id, jr_job_vacancy_id, jr_user_id, jr_resume, jr_status FROM job_request ";
    public static final String JOB_REQUEST_QUERY_UPDATE = "UPDATE job_request SET jr_job_vacancy_id = ?, jr_user_id = ?," +
            " jr_resume = ?, jr_status = ? WHERE jr_id = ?";
    public static final String JOB_REQUEST_QUERY_SELECT_REQUEST_CONTENT = "SELECT user.u_id, job_request.jr_id, user.u_email, job_request.jr_resume, job_request.jr_status, job_vacancy.jv_name " +
            "FROM (job_request   INNER JOIN job_vacancy ON job_request.jr_job_vacancy_id = job_vacancy.jv_id )   " +
            "INNER JOIN user ON job_request.jr_user_id = user.u_id " +
            " WHERE job_request.jr_status != ?  AND job_vacancy.jv_organization_id = ? AND job_vacancy.jv_name LIKE ? limit ?, ?";
    public static final String JOB_REQUEST_QUERY_DELETE_BY_ID = "DELETE FROM job_request WHERE jr_id = ?";
    public static final String JOB_REQUEST_QUERY_CREATE = "INSERT INTO job_request (jr_job_vacancy_id, jr_user_id, jr_resume, jr_status)" +
            " VALUES (?, ?, ?, ?)";
    public static final String JOB_REQUEST_FIELD_ID = "jr_id";
    public static final String JOB_REQUEST_FIELD_JOB_VACANCY_ID = "jr_job_vacancy_id";
    public static final String JOB_REQUEST_FIELD_USER_ID = "jr_user_id";
    public static final String JOB_REQUEST_FIELD_RESUME = "jr_resume";
    public static final String JOB_REQUEST_FIELD_STATUS = "jr_status";
    public static final String JOB_REQUESTS_COUNT_SELECT = "SELECT COUNT(jr_id) FROM job_request " +
            "INNER JOIN job_vacancy ON job_request.jr_job_vacancy_id = job_vacancy.jv_id " +
            "WHERE job_request.jr_status != ? AND job_vacancy.jv_organization_id = ? AND job_vacancy.jv_name LIKE ?";
    public static final String JOB_REQUESTS_COUNT = "COUNT(jr_id)";


    public static final String JOB_VACANCY_QUERY_SELECT = "SELECT jv_id, jv_organization_id, jv_name, jv_upload_date, jv_requirement, jv_status FROM job_vacancy ";
    public static final String JOB_VACANCY_QUERY_SELECT_VACANCY_CONTENT = "SELECT job_vacancy.jv_id, job_vacancy.jv_name, job_vacancy.jv_upload_date," +
            " job_vacancy.jv_requirement, job_vacancy.jv_status, organization.o_name, organization.o_website, organization.o_id \n" +
            "FROM job_vacancy INNER JOIN organization ON organization.o_id = job_vacancy.jv_organization_id WHERE job_vacancy.jv_status = ? AND job_vacancy.jv_name LIKE ? limit ?, ?";
    public static final String JOB_VACANCY_QUERY_UPDATE = "UPDATE job_vacancy SET jv_organization_id = ?, jv_name = ?, jv_upload_date = ?," +
            " jv_requirement = ?, jv_status = ? WHERE jv_id = ?";
    public static final String JOB_VACANCY_QUERY_DELETE_BY_ID = "DELETE FROM job_vacancy WHERE jv_id = ?";
    public static final String JOB_VACANCY_QUERY_CREATE = "INSERT INTO job_vacancy (jv_organization_id, jv_name, jv_upload_date, jv_requirement, jv_status)" +
            " VALUES (?, ?, ?, ?, ?);";
    public static final String JOB_VACANCY_FIELD_ID = "jv_id";
    public static final String JOB_VACANCY_FIELD_ORGANIZATION_ID = "jv_organization_id";
    public static final String JOB_VACANCY_FIELD_NAME = "jv_name";
    public static final String JOB_VACANCY_FIELD_UPLOAD_DATE = "jv_upload_date";
    public static final String JOB_VACANCY_FIELD_REQUIREMENT = "jv_requirement";
    public static final String JOB_VACANCY_FIELD_STATUS = "jv_status";
    public static final String JOB_VACANCIES_COUNT_SELECT = "SELECT COUNT(jv_id) FROM job_vacancy WHERE job_vacancy.jv_status = ? AND job_vacancy.jv_name LIKE ?";
    public static final String JOB_VACANCIES_COUNT = "COUNT(jv_id)";

    private SQLQueryVariable() {}
}
