package by.molchanov.humanresources.command.common;

import by.molchanov.humanresources.command.ConcreteCommand;
import by.molchanov.humanresources.controller.RequestHolder;
import by.molchanov.humanresources.dto.FilterDataDTO;
import by.molchanov.humanresources.exception.CustomBrokerException;

import static by.molchanov.humanresources.command.SessionRequestAttributeName.*;

/**
 * Class {@link VacancyFilterCommand} is used for searching and filter vacancy content.
 *
 * @author Molchanov Vladislav
 * @see ConcreteCommand
 */
public class VacancyFilterCommand implements ConcreteCommand {
    private static final VacancyFilterCommand VACANCY_FILTER_COMMAND = new VacancyFilterCommand();
    private ConcreteCommand fillContentCommand = FillContentCommand.getInstance();

    private static final int FIRST_INDEX = 0;

    private VacancyFilterCommand() {

    }

    public static VacancyFilterCommand getInstance() {
        return VACANCY_FILTER_COMMAND;
    }

    @Override
    public void execute(RequestHolder requestHolder) throws CustomBrokerException {
        Boolean vacFilterFlag = (Boolean) requestHolder.getSessionAttribute(VAC_FILTER_FLAG);
        if (vacFilterFlag != null && vacFilterFlag) {
            requestHolder.removeSessionAttribute(VAC_FILTER_FLAG, VAC_FILTER_DATA);
        } else {
            String sortColumn = requestHolder.getSingleRequestParameter(FIRST_INDEX, SORT_COL);
            String sortDirectionType = requestHolder.getSingleRequestParameter(FIRST_INDEX, SORT_TYPE);
            String searchField = requestHolder.getSingleRequestParameter(FIRST_INDEX, SEARCH_FIELD);
            FilterDataDTO filterDataDTO = new FilterDataDTO();
            filterDataDTO.setSortColumn(sortColumn);
            filterDataDTO.setSearchField(searchField);
            filterDataDTO.setSortDirectionType(sortDirectionType);
            requestHolder.addSessionAttribute(VAC_FILTER_FLAG, Boolean.TRUE);
            requestHolder.addSessionAttribute(VAC_FILTER_DATA, filterDataDTO);
        }
        fillContentCommand.execute(requestHolder);
    }
}
