package by.molchanov.humanresources.controller;

import by.molchanov.humanresources.exception.CustomBrokerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.COMMAND;
import static by.molchanov.humanresources.command.SessionRequestAttributeName.HASH;

/**
 * Class {@link RequestHolder} - wrapper class is used for read and store data from servlet request.
 *
 * @author Molchanov Vladislav
 */
public class RequestHolder {
    private static final int PRIMARY_HASH = 0;
    private static final String EMPTY_STRING = "";
    private Map<String, Object> requestAttributes = new HashMap<>();
    private Map<String, Object> sessionAttributes = new HashMap<>();
    private Map<String, String[]> requestParameters = new HashMap<>();
    private List<String> sessionAttributesForDelete = new ArrayList<>();

    public RequestHolder(HttpServletRequest request) {
        Object retrievedObject;
        String retrievedName;
        requestParameters = new HashMap<>(request.getParameterMap());
        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();
        Enumeration<String> requestAttributeNames = request.getAttributeNames();
        while (requestAttributeNames.hasMoreElements()) {
            retrievedName = requestAttributeNames.nextElement();
            retrievedObject = request.getAttribute(retrievedName);
            requestAttributes.put(retrievedName, retrievedObject);
        }

        Integer currentHash = PRIMARY_HASH;
        Integer hash = (Integer) session.getAttribute(HASH);
        for (Map.Entry<String, String[]> pair : requestParameters.entrySet()) {
            currentHash += pair.getValue()[0].hashCode();
            currentHash += pair.getKey().hashCode();
        }
        if (hash == null) {
            session.setAttribute(HASH, PRIMARY_HASH);
        } else if (!currentHash.equals(hash)) {
            session.removeAttribute(HASH);
            session.setAttribute(HASH, currentHash);
        } else {
            requestParameters.remove(COMMAND);
        }
        while (sessionAttributeNames.hasMoreElements()) {
            retrievedName = sessionAttributeNames.nextElement();
            retrievedObject = session.getAttribute(retrievedName);
            sessionAttributes.put(retrievedName, retrievedObject);
        }
    }

    public void addRequestAttribute(String key, Object value) {
        requestAttributes.put(key, value);
    }

    public void addSessionAttribute(String key, Object value) {
        removeSessionAttribute(key);
        sessionAttributes.put(key, value);
    }

    public Object getSessionAttribute(String key) {
        return sessionAttributes.get(key);
    }

    public String getSingleRequestParameter(int position, String key) {
        if (requestParameters.isEmpty()) {
            return EMPTY_STRING;
        }
        return requestParameters.containsKey(key) ? requestParameters.get(key)[position] : EMPTY_STRING;
    }

    public String[] getRequestParameter(String key) {
        return requestParameters.get(key);
    }

    public void removeSessionAttribute(String... attributeForDelete) {
        for (String attribute : attributeForDelete) {
            sessionAttributes.remove(attribute);
            sessionAttributesForDelete.add(attribute);
        }
    }

    public void update(HttpServletRequest request) throws CustomBrokerException {
        String key;
        Object value;
        for (Map.Entry<String, Object> attribute : requestAttributes.entrySet()) {
            key = attribute.getKey();
            value = attribute.getValue();
            request.setAttribute(key, value);
        }
        HttpSession session = request.getSession();
        for (String attribute: sessionAttributesForDelete) {
            session.removeAttribute(attribute);
        }

        for (Map.Entry<String, Object> attribute : sessionAttributes.entrySet()) {
            key = attribute.getKey();
            value = attribute.getValue();
            if (value instanceof Serializable) {
                session.setAttribute(key, value);
            } else {
                throw new CustomBrokerException("Try to add non-serializable object to session!");
            }
        }
    }
}
