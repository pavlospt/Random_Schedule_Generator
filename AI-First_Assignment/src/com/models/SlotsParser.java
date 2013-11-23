package com.models;

//Paulos-Petros Tournaris 3110199
//Georgios Tzanoydakhs 3110194
//Rousas Apostolos 3110173

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class SlotsParser {
	
    private ArrayList<Slots> days;
    
    
	public SlotsParser () {
		
	}	
        
    public SlotsParser (JsonObject json) {
    

        this.days = new ArrayList<Slots>();
        JsonArray arrayDays = json.getJsonArray("Days");
        if (null != arrayDays) {
            int daysLength = arrayDays.size();
            for (int i = 0; i < daysLength; i++) {
                JsonObject item = arrayDays.getJsonObject(i);
                if (null != item) {
                    this.days.add(new Slots(item));
                }
            }
        }
        else {
            JsonObject item = json.getJsonObject("Days");
            if (null != item) {
                this.days.add(new Slots(item));
            }
        }


    }
    
    public ArrayList<Slots> getSlots() {
        return this.days;
    }

    public void setSlots(ArrayList<Slots> days) {
        this.days = days;
    }


    
}
