package com.hwedu.common.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* <p>基础数据__年级DTO</p>  
* @author xingyongchun
* @date 2016年4月21日
 */
public class BaseGradeDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String name;

    private Integer enrollmentDate;

    private Boolean isGraduated;

    private String facultyId;

    private Integer gradeNo;

    private Boolean isDeleted;

    private Date createTime;

    private Date modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Integer enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Boolean getIsGraduated() {
        return isGraduated;
    }

    public void setIsGraduated(Boolean isGraduated) {
        this.isGraduated = isGraduated;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public Integer getGradeNo() {
        return gradeNo;
    }

    public void setGradeNo(Integer gradeNo) {
        this.gradeNo = gradeNo;
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