package com.hwedu.bookcross.vo;

public class GroupDetailVO {
    private String id;//套系详情表id
    private String bookId;//书id
    private String bookName;//书名
    private String groupId;//套系id
    private String groupName;//套系名
    private Integer crossTotal;//流转量
    private Integer errorTotal;//错误量
    private Integer initTotal;//初始化量

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
}