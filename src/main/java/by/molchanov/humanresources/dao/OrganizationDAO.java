package by.molchanov.humanresources.dao;

import by.molchanov.humanresources.entity.Organization;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.util.List;

/**
 * Interface {@link OrganizationDAO} is used for organization dao.
 * Contains specified method for organization dao.
 *
 * @author MolchanovVladislav
 */
public interface OrganizationDAO extends OverallDAO<Organization> {
    List<Organization> findPartOfOrganizations(int startOrganizationNumber, int organizationsQuantity) throws CustomDAOException;

    int findOrganizationsCount() throws CustomDAOException;
}
