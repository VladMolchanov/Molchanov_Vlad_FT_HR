package by.molchanov.humanresources.dao;

import by.molchanov.humanresources.entity.User;
import by.molchanov.humanresources.exception.CustomDAOException;

import java.util.List;

public interface UserDAO extends OverallDAO<User> {
    void updateUserOrgIdRole(User user) throws CustomDAOException;
    List<User> findUsersByEmailAndPassword(String email, String password) throws CustomDAOException;
    List<User> findPartOfUsers(int firstLimit, int secondLimit) throws CustomDAOException;
}
