package com.hwedu.common.po;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* <p>基础数据__家长PO</p>  
* @author xingyongchun
* @date 2016年4月21日
 */
public class BaseParentPO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String studentJson;

    private String relation;

    private String personId;

    private Boolean isDeleted;

    private Date createTime;

    private Date modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentJson() {
        return studentJson;
    }

    public void setStudentJson(String studentJson) {
        this.studentJson = studentJson;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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