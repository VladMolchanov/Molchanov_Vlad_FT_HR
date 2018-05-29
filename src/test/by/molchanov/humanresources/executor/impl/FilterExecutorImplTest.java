package by.molchanov.humanresources.executor.impl;

import by.molchanov.humanresources.dto.FilterDataDTO;
import by.molchanov.humanresources.entity.JobRequest;
import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.exception.CustomExecutorException;
import by.molchanov.humanresources.executor.FillContentExecutor;
import by.molchanov.humanresources.executor.FilterExecutor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class FilterExecutorImplTest {

    @InjectMocks
    private FilterExecutor executor = FilterExecutorImpl.getInstance();

    @Mock
    private FillContentExecutor fillContentExecutor;

    private List<JobVacancy> vacancies = new ArrayList<>();
    private List<JobRequest> requests = new ArrayList<>();
    private FilterDataDTO filterDataDTO = new FilterDataDTO();

    @BeforeTest
    public void before() {
        initMocks(this);
        filterDataDTO.setSortDirectionType("increase");
        filterDataDTO.setSortColumn("SORT_BY_EMPTY_COLUMN");
        filterDataDTO.setSearchField("field");
    }

    @AfterTest
    public void after() {
        reset(fillContentExecutor);
    }

    @Test
    public void filterVacancyTest() throws CustomExecutorException {
        when(fillContentExecutor.fillVacancy(anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn(vacancies);

        executor.filterVacancy(filterDataDTO, "role", 1, 1);

        verify(fillContentExecutor).fillVacancy(anyString(), anyString(), anyInt(), anyInt());
        verifyNoMoreInteractions(fillContentExecutor);
    }

    @Test
    public void filterRequestTest() throws CustomExecutorException {
        when(fillContentExecutor.fillRequest(anyString(), anyInt(), anyString(), anyInt(), anyInt()))
                .thenReturn(requests);

        executor.filterRequest(filterDataDTO, "role", 1, 1);

        verify(fillContentExecutor).fillRequest(anyString(), anyInt(), anyString(), anyInt(), anyInt());
        verifyNoMoreInteractions(fillContentExecutor);
    }
}
