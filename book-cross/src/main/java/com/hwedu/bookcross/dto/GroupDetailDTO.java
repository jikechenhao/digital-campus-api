package com.hwedu.bookcross.dto;

public class GroupDetailDTO {
    private String id;
    private String bookId;
    private String bookName;
    private String groupId;
    private String groupName;
    private Integer crossTotal;
    private Integer errorTotal;
    private Integer initTotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getCrossTotal() {
        return crossTotal;
    }

    public void setCrossTotal(Integer crossTotal) {
        this.crossTotal = crossTotal;
    }

    public Integer getErrorTotal() {
        return errorTotal;
    }

    public void setErrorTotal(Integer errorTotal) {
        this.errorTotal = errorTotal;
    }

    public Integer getInitTotal() {
        return initTotal;
    }

    public void setInitTotal(Integer initTotal) {
        this.initTotal = initTotal;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}