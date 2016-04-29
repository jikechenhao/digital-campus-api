package com.hwedu.common.po;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* <p>基础数据__人员PO</p>  
* @author xingyongchun
* @date 2016年4月21日
 */
public class BasePersonPO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String nickName;

    private String trueNamePinyin;

    private String trueName;

    private String cardId;

    private String personType;

    private String roleJson;

    private String schoolDistrictId;

    private Boolean isDeleted;

    private Date createTime;

    private Date modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTrueNamePinyin() {
        return trueNamePinyin;
    }

    public void setTrueNamePinyin(String trueNamePinyin) {
        this.trueNamePinyin = trueNamePinyin;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getRoleJson() {
        return roleJson;
    }

    public void setRoleJson(String roleJson) {
        this.roleJson = roleJson;
    }

    public String getSchoolDistrictId() {
        return schoolDistrictId;
    }

    public void setSchoolDistrictId(String schoolDistrictId) {
        this.schoolDistrictId = schoolDistrictId;
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