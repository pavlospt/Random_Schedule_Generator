package com.models;

//Paulos-Petros Tournaris 3110199
//Georgios Tzanoydakhs 3110194
//Rousas Apostolos 3110173

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class RoomsParser {
	
    private ArrayList<Rooms> rooms;
    
    
	public RoomsParser () {
		
	}	
        
    public RoomsParser (JsonObject json) {
    

        this.rooms = new ArrayList<Rooms>();
        JsonArray arrayRooms = json.getJsonArray("Rooms");
        if (null != arrayRooms) {
            int roomsLength = arrayRooms.size();
            for (int i = 0; i < roomsLength; i++) {
                JsonObject item = arrayRooms.getJsonObject(i);
                if (null != item) {
                    this.rooms.add(new Rooms(item));
                }
            }
        }
        else {
            JsonObject item = json.getJsonObject("Rooms");
            if (null != item) {
                this.rooms.add(new Rooms(item));
            }
        }


    }
    
    public ArrayList<Rooms> getRooms() {
        return this.rooms;
    }

    public void setRooms(ArrayList<Rooms> rooms) {
        this.rooms = rooms;
    }


    
}
