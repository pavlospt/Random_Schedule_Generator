package com.models;

//Paulos-Petros Tournaris 3110199
//Georgios Tzanoydakhs 3110194
//Rousas Apostolos 3110173

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class InstructorsParser {
	
    private ArrayList<Instructors> instructors;
    
    
	public InstructorsParser () {
		
	}	
        
    public InstructorsParser (JsonObject json) {
    

        this.instructors = new ArrayList<Instructors>();
        JsonArray arrayInstructors = json.getJsonArray("Instructors");
        if (null != arrayInstructors) {
            int instructorsLength = arrayInstructors.size();
            for (int i = 0; i < instructorsLength; i++) {
                JsonObject item = arrayInstructors.getJsonObject(i);
                if (null != item) {
                    this.instructors.add(new Instructors(item));
                }
            }
        }
        else {
            JsonObject item = json.getJsonObject("Instructors");
            if (null != item) {
                this.instructors.add(new Instructors(item));
            }
        }


    }
    
    public ArrayList<Instructors> getInstructors() {
        return this.instructors;
    }

    public void setInstructors(ArrayList<Instructors> instructors) {
        this.instructors = instructors;
    }


    
}
