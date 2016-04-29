package com.hwedu.po;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>学生小组PO</p>
 *
 * @author xingyongchun
 * @date 2016年4月22日
 */
public class FlowerStudentGroupPO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String studentId;

    private String groupId;

    private Boolean isDeleted;

    private Date createTime;

    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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