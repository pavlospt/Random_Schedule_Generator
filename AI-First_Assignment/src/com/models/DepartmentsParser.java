package com.models;

//Paulos-Petros Tournaris 3110199
//Georgios Tzanoydakhs 3110194
//Rousas Apostolos 3110173

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class DepartmentsParser {
	
    private ArrayList<Departments> departments;
    
    
	public DepartmentsParser () {
		
	}	
        
    public DepartmentsParser (JsonObject json) {
    

        this.departments = new ArrayList<Departments>();
        JsonArray arrayDepartments = json.getJsonArray("departments");
        if (null != arrayDepartments) {
            int departmentsLength = arrayDepartments.size();
            for (int i = 0; i < departmentsLength; i++) {
                JsonObject item = arrayDepartments.getJsonObject(i);
                if (null != item) {
                    this.departments.add(new Departments(item));
                }
            }
        }
        else {
            JsonObject item = json.getJsonObject("departments");
            if (null != item) {
                this.departments.add(new Departments(item));
            }
        }


    }
    
    public ArrayList<Departments> getDepartments() {
        return this.departments;
    }

    public void setDepartments(ArrayList<Departments> departments) {
        this.departments = departments;
    }


    
}
