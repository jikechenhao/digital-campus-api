package com.hwedu.bookcross.dto;

import com.hwedu.bookcross.dto.base.BaseDTO;

import java.util.Date;

public class BookDTO extends BaseDTO {
    private String id;
    private String name;
    private String intro;
    private String author;
    private String press;
    private Date createTime;
    private Date modifyTime;
    private Boolean isDeleted;
    private Integer storeTotal;

    private Integer increment;

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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getStoreTotal() {
        return storeTotal;
    }

    public void setStoreTotal(Integer storeTotal) {
        this.storeTotal = storeTotal;
    }

    public Integer getIncrement() {
        return increment;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }
}