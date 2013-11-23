package com.models;

//Paulos-Petros Tournaris 3110199
//Georgios Tzanoydakhs 3110194
//Rousas Apostolos 3110173

import javax.json.JsonObject;

public class Rooms {
	
    private String roomCode;
    private String roomName;
    private String roomSpace;
    
    
	public Rooms () {
		
	}	
        
    public Rooms (JsonObject json) {
    
        this.roomCode = json.getString("room_code");
        this.roomName = json.getString("room_name");
        this.roomSpace = json.getString("room_space");

    }
    
    public String getRoomCode() {
        return this.roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomSpace() {
        return this.roomSpace;
    }

    public void setRoomSpace(String roomSpace) {
        this.roomSpace = roomSpace;
    }


    
}
