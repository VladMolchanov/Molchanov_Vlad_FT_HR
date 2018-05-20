package by.molchanov.humanresources.executor;

import java.util.List;

/**
 * Interface {@link LogOutExecutor} is used for system log out.
 *
 * @author MolchanovVladislav
 */
public interface LogOutExecutor {
    /**
     * User system log out
     * @return collection of names of session parameters
     */
    List<String> logOut();
}
