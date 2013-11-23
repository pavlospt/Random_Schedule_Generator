package com.models;

//Paulos-Petros Tournaris 3110199
//Georgios Tzanoydakhs 3110194
//Rousas Apostolos 3110173

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class CoursesParser {
	
    private ArrayList<Courses> courses;
    
    
	public CoursesParser () {
		
	}	
        
    public CoursesParser (JsonObject json) {
    

        this.courses = new ArrayList<Courses>();
        JsonArray arrayCourses = json.getJsonArray("Courses");
        if (null != arrayCourses) {
            int coursesLength = arrayCourses.size();
            for (int i = 0; i < coursesLength; i++) {
                JsonObject item = arrayCourses.getJsonObject(i);
                if (null != item) {
                    this.courses.add(new Courses(item));
                }
            }
        }
        else {
            JsonObject item = json.getJsonObject("Courses");
            if (null != item) {
                this.courses.add(new Courses(item));
            }
        }


    }
    
    public ArrayList<Courses> getCourses() {
        return this.courses;
    }

    public void setCourses(ArrayList<Courses> courses) {
        this.courses = courses;
    }


    
}
