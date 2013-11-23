package com.models;

//Paulos-Petros Tournaris 3110199
//Georgios Tzanoydakhs 3110194
//Rousas Apostolos 3110173

import javax.json.JsonObject;

public class Departments {
	
    private String departmentName;
    
    
	public Departments () {
		
	}	
        
    public Departments (JsonObject json) {
    
        this.departmentName = json.getString("department_name");

    }
    
    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    
}
