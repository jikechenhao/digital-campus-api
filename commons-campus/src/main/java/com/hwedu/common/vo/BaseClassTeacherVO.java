package com.hwedu.common.vo;

import java.io.Serializable;
/**
 * 
* <p>基础数据__班级教师VO</p>  
* @author xingyongchun
* @date 2016年4月21日
 */
public class BaseClassTeacherVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String id;

    private String teacherId;

    private String classId;

    private String subjectId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}