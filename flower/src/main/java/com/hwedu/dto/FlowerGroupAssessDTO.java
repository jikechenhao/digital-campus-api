package com.hwedu.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>组评价DTO</p>
 *
 * @author xingyongchun
 * @date 2016年4月22日
 */
public class FlowerGroupAssessDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String groupId;

    private Date assessDate;

    private String sectionId;

    private String subjectId;

    private Integer penalizeScore;

    private Integer awardScore;

    private Boolean isDeleted;

    private Date createTime;

    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getAssessDate() {
        return assessDate;
    }

    public void setAssessDate(Date assessDate) {
        this.assessDate = assessDate;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getPenalizeScore() {
        return penalizeScore;
    }

    public void setPenalizeScore(Integer penalizeScore) {
        this.penalizeScore = penalizeScore;
    }

    public Integer getAwardScore() {
        return awardScore;
    }

    public void setAwardScore(Integer awardScore) {
        this.awardScore = awardScore;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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