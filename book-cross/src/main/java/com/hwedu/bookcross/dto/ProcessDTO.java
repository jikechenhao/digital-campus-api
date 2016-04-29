package com.hwedu.bookcross.dto;

public class ProcessDTO {
    private String id;
    private String gradeId;
    private String groupIdJSON;
    private String termId;
    private String name;
    private Integer expectedSteps;
    private Integer startWeekDay;
    private Integer enumState;
    private String managerId;
    private String configId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGroupIdJSON() {
        return groupIdJSON;
    }

    public void setGroupIdJSON(String groupIdJSON) {
        this.groupIdJSON = groupIdJSON;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExpectedSteps() {
        return expectedSteps;
    }

    public void setExpectedSteps(Integer expectedSteps) {
        this.expectedSteps = expectedSteps;
    }

    public Integer getStartWeekDay() {
        return startWeekDay;
    }

    public void setStartWeekDay(Integer startWeekDay) {
        this.startWeekDay = startWeekDay;
    }

    public Integer getEnumState() {
        return enumState;
    }

    public void setEnumState(Integer enumState) {
        this.enumState = enumState;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }
}