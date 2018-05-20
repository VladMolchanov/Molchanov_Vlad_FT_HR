package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.ColumnForSortingType;
import by.molchanov.humanresources.executor.FillContentExecutor;
import by.molchanov.humanresources.executor.FilterExecutor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static by.molchanov.humanresources.entity.JobVacancy.COMPARE_BY_DATE;
import static by.molchanov.humanresources.entity.JobVacancy.COMPARE_BY_NAME;
import static by.molchanov.humanresources.entity.JobVacancy.COMPARE_BY_ORG_NAME;

import static by.molchanov.humanresources.entity.JobRequest.COMPARE_BY_USER_NAME;
import static by.molchanov.humanresources.entity.JobRequest.COMPARE_BY_VAC_NAME;

/**
 * Class {@link FilterExecutorImpl} used for searching and filter records.
 *
 * @author MolcanovVladislav
 * @see FilterExecutor
 */
public class FilterExecutorImpl implements FilterExecutor {
    private static final FilterExecutorImpl VACANCY_FILTER_EXECUTOR = new FilterExecutorImpl();
    private static final FillContentExecutor FILL_CONTENT_EXECUTOR = FillContentExecutorImpl.getInstance();

    private static final String INCREASE = "increase";
    private static final String DECREASE = "decrease";
    private static final String EMPTY = "empty";

    private FilterExecutorImpl() {

    }

    public static FilterExecutorImpl getInstance() {
        return VACANCY_FILTER_EXECUTOR;
    }

    @Override
    public List<JobVacancy> filterVacancy(String sortColumn, String sortDirectionType, String searchField, String userRole) throws CustomExecutorException {
        boolean sortDirectionTypeFlag = setSortDirectionTypeFlag(sortDirectionType);
        ColumnForSortingType sortingColumnType = ColumnForSortingType.valueOf(sortColumn.toUpperCase());
        List<JobVacancy> vacancies = FILL_CONTENT_EXECUTOR.fillVacancy(userRole);
        if (!searchField.isEmpty()) {
            vacancies.removeIf(vacancy -> !vacancy.getName().toLowerCase().contains(searchField.toLowerCase()));
        }
        executeVacancySort(sortingColumnType, vacancies, sortDirectionTypeFlag);
        return vacancies;
    }

    @Override
    public List<JobRequest> filterRequest(String sortColumn, String sortDirectionType, String searchField, String userRole, int orgId) throws CustomExecutorException {
        boolean sortDirectionTypeFlag = setSortDirectionTypeFlag(sortDirectionType);
        ColumnForSortingType sortingColumnType = ColumnForSortingType.valueOf(sortColumn.toUpperCase());
        List<JobRequest> requests = FILL_CONTENT_EXECUTOR.fillRequest(userRole, orgId);
        if (!searchField.isEmpty()) {
            requests.removeIf(vacancy -> !vacancy.getJobVacancy().getName().toLowerCase().contains(searchField.toLowerCase()));
        }
        executeRequestSort(sortingColumnType, requests, sortDirectionTypeFlag);
        return requests;
    }

    private boolean setSortDirectionTypeFlag(String sortDirectionType) throws CustomExecutorException {
        boolean result;
        switch (sortDirectionType) {
            case DECREASE:
                result = true;
                break;
            case INCREASE:
                result = false;
                break;
            case EMPTY:
                result = false;
                break;
            default:
                throw new CustomExecutorException("Unknown sorting type!");
        }
        return result;
    }

    private void executeVacancySort(ColumnForSortingType sortingColumnType, List<JobVacancy> vacancies, boolean sortDirectionTypeFlag) throws CustomExecutorException {
        switch (sortingColumnType) {
            case SORT_BY_DATE:
                sorter(COMPARE_BY_DATE, vacancies, sortDirectionTypeFlag);
                break;
            case SORT_BY_VAC_NAME:
                sorter(COMPARE_BY_NAME, vacancies, sortDirectionTypeFlag);
                break;
            case SORT_BY_EMPTY_COLUMN:
                break;
            case SORT_BY_ORGANIZATION:
                sorter(COMPARE_BY_ORG_NAME, vacancies, sortDirectionTypeFlag);
                break;
            default:
                throw new CustomExecutorException("Unknown sorting column!");
        }
    }

    private void executeRequestSort(ColumnForSortingType sortingColumnType, List<JobRequest> requests, boolean sortDirectionTypeFlag) throws CustomExecutorException {
        switch (sortingColumnType) {
            case SORT_BY_VAC_NAME:
                sorter(COMPARE_BY_VAC_NAME, requests, sortDirectionTypeFlag);
                break;
            case SORT_BY_EMPTY_COLUMN:
                break;
            case SORT_BY_USER_NAME:
                sorter(COMPARE_BY_USER_NAME, requests, sortDirectionTypeFlag);
                break;
            default:
                throw new CustomExecutorException("Unknown sorting column!");
        }
    }


    private <T> void sorter(Comparator<T> comparator, List<T> vacancies, boolean sortTypeFlag) {
        vacancies.sort(comparator);
        if (sortTypeFlag) {
            Collections.reverse(vacancies);
        }
    }
}
