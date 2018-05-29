package by.molchanov.humanresources.dto;

import java.io.Serializable;

public class FilterDataDTO implements Serializable {
    private String sortColumn;
    private String sortDirectionType;
    private String searchField;
    private int orgId;

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortDirectionType() {
        return sortDirectionType;
    }

    public void setSortDirectionType(String sortDirectionType) {
        this.sortDirectionType = sortDirectionType;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
}
