package com.models;

//Paulos-Petros Tournaris 3110199
//Georgios Tzanoydakhs 3110194
//Rousas Apostolos 3110173

import javax.json.JsonObject;

public class Courses {
	
    private String semester;
    private String courseName;
    private String instructor;
    private String requiredClassSize;
    private String department;
    private String courseCode;
    
    
	public Courses () {
		
	}	
        
    public Courses (JsonObject json) {
    	
    	
        this.semester =  json.getString("semester");
        this.courseName = json.getString("course_name");
        this.instructor = json.getString("instructor");
        this.requiredClassSize = json.getString("required_class_size");
        this.department = json.getString("department");
        this.courseCode = json.getString("course_code");

    }
    
    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return this.instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getRequiredClassSize() {
        return this.requiredClassSize;
    }

    public void setRequiredClassSize(String requiredClassSize) {
        this.requiredClassSize = requiredClassSize;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }


    
}
