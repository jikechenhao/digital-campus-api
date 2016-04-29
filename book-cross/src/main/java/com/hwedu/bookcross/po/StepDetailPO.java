package com.hwedu.bookcross.po;

import java.util.Date;

public class StepDetailPO {
    private String id;
    private Date borrowTime;
    private Date backTime;
    private Integer enumState;
    private String bookId;
    private String newBookId;
    private String studentId;
    private String stepId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public Integer getEnumState() {
        return enumState;
    }

    public void setEnumState(Integer enumState) {
        this.enumState = enumState;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getNewBookId() {
        return newBookId;
    }

    public void setNewBookId(String newBookId) {
        this.newBookId = newBookId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }
}