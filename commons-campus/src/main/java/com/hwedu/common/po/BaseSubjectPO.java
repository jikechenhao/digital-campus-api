package com.hwedu.common.po;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* <p>基础数据__学科PO</p>  
* @author xingyongchun
* @date 2016年4月21日
 */
public class BaseSubjectPO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String name;

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