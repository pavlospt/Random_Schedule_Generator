package com.models;

//Paulos-Petros Tournaris 3110199
//Georgios Tzanoydakhs 3110194
//Rousas Apostolos 3110173

import javax.json.JsonObject;

public class Instructors {
	
    private String instructor;
    private String kwdikos;
    private String nonAvailable;
    
    
	public Instructors () {
		
	}	
        
    public Instructors (JsonObject json) {
    
        this.instructor = json.getString("instructor");
        this.kwdikos = json.getString("kwdikos");
        this.nonAvailable = json.getString("non_available");

    }
    
    public String getInstructor() {
        return this.instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getKwdikos() {
        return this.kwdikos;
    }

    public void setKwdikos(String kwdikos) {
        this.kwdikos = kwdikos;
    }

    public String getNonAvailable() {
        return this.nonAvailable;
    }

    public void setNonAvailable(String nonAvailable) {
        this.nonAvailable = nonAvailable;
    }


    
}
