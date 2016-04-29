package com.hwedu.bookcross.po;

import java.util.Date;

public class ProcessPO {
    private String id;
    private String gradeId;
    private String groupIdJSON;
    private String termId;
    private String name;
    private Integer expectedSteps;
    private Integer startWeekDay;
    private Integer enumProcessState;
    private String managerId;
    private String configId;
    private Boolean isDeleted;
    private Date createTime;
    private Date modifyTime;

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

    public Integer getEnumProcessState() {
        return enumProcessState;
    }

    public void setEnumProcessState(Integer enumProcessState) {
        this.enumProcessState = enumProcessState;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}