package com.models;

//Paulos-Petros Tournaris 3110199
//Georgios Tzanoydakhs 3110194
//Rousas Apostolos 3110173

import javax.json.JsonObject;

public class Slots {
	
    private String calendar;
    private String dayName;
    private String availableHours;
    
    
	public Slots () {
		
	}	
        
    public Slots (JsonObject json) {
    
        this.calendar = json.getString("calendar");
        this.dayName = json.getString("day_name");
        this.availableHours = json.getString("available_hours");

    }
    
    public String getCalendar() {
        return this.calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getDayName() {
        return this.dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getAvailableHours() {
        return this.availableHours;
    }

    public void setAvailableHours(String availableHours) {
        this.availableHours = availableHours;
    }


    
}
