package com.hwedu.common.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* <p>基础数据__组织机构DTO</p>  
* @author xingyongchun
* @date 2016年4月21日
 */
public class BaseOrganizationDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String name;

    private String parentId;

    private String districtId;

    private String officeMaster;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getOfficeMaster() {
        return officeMaster;
    }

    public void setOfficeMaster(String officeMaster) {
        this.officeMaster = officeMaster;
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