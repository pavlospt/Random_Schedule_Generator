package com.texniti.ergasia;

//Paulos-Petros Tournaris 3110199 - p.tournaris@gmail.com	
//Georgios Tzanoydakhs 3110194 - chesterlos93@gmail.com
//Rousas Apostolos 3110173 - g.tzanoudakhs@gmail.com

import java.util.ArrayList;
import java.util.Random;

import com.models.Courses;
import com.models.Instructors;
import com.models.Rooms;
import com.models.Slots;

public class Chrome implements Comparable<Chrome> {

	private CustomMap mMap;
	private ArrayList<Rooms> mRooms;
	private ArrayList<Instructors> mInstructors;
	private int mScore;
	private int size;

	/**
	 * Main Constructor (with random generated map)
	 * */
	public Chrome(ArrayList<Rooms> rooms, ArrayList<Courses> courses,
			ArrayList<Slots> slots, ArrayList<Instructors> instructors) {

		this.mMap = new CustomMap(rooms, courses, slots);
		this.mScore = 0;

		this.mRooms = new ArrayList<Rooms>(rooms);
		this.mInstructors = new ArrayList<Instructors>(instructors);

		this.size = this.mMap.getSize();

		this.calculateScore(mRooms, mInstructors);
	}

	/**
	 * Main Constructor (with preset map)
	 * */
	public Chrome(CustomMap map, ArrayList<Rooms> rooms,
			ArrayList<Instructors> instructors, ArrayList<Slots> slots) {
		this.mRooms = new ArrayList<Rooms>(rooms);
		this.mInstructors = new ArrayList<Instructors>(instructors);

		this.mMap = new CustomMap(map);

		this.size = this.mMap.getSize();

		this.calculateScore(mRooms, mInstructors);
	}

	/**
	 * Calculates the score of a State and assigns it to mScore variable.
	 * @param rooms An ArrayList containing data from rooms.json file.
	 * @param instructors An ArrayList containing data from instructors.json file.
	 * */
	private void calculateScore(ArrayList<Rooms> rooms,
			ArrayList<Instructors> instructors) {
		State temp = new State(Main.fillExamDayArray(), this.mMap, rooms,
				instructors);

		this.mScore = temp.getFitness();

	}
	

	/**
	 * Get the CustomMap object from Chrome object
	 * @return Returns a CustonMap object.
	 * */
	public CustomMap getMap(){
		return this.mMap;
	}

	/**
	 * Get a LessonObject object from CustomMap object by position.
	 * @param i Position in the ArrayList.
	 * @return Returns a LessonObject object.
	 * */
	public LessonObject getMapKey(int i) {
		return this.mMap.getKey(i);
	}

	/**
	 * Get a DayTimeSlot object from CustomMap object by position.
	 * @param i Position in the ArrayList.
	 * @return Returns a LessonObject object.
	 * */
	public DayTimeSlot getMapValue(int i) {
		return this.mMap.getValue(i);
	}

	/**
	 * Returns the size of the current Chrome object, which is equivalent to the contained 
	 * CustomMap object.
	 * @return Size of the current Chrome object.*/
	public int getSize() {
		return this.size;
	}

	/**
	 * Returns the score of the current Chrome object.
	 * @return Score of the current Chrome object.*/
	public int getScore() {
		return this.mScore;
	}

	/**
	 * Mutation method used at {@link com.texniti.ergasia.Genetic#geneticAlgorithm(int, double, int, int) geneticAlgorithm() Method}
	 * @param dts ArrayList containg DayTimeSlot objects.
	 * */
	public void mutate(ArrayList<DayTimeSlot> dts) {
		Random r = new Random(System.currentTimeMillis());

		int random1 = r.nextInt(this.mMap.getSize());
		int random2 = r.nextInt(dts.size());

		DayTimeSlot randomDayTimeSlot = new DayTimeSlot(dts.get(random2));

		this.mMap.setValue(random1, randomDayTimeSlot);

		this.calculateScore(this.mRooms, this.mInstructors);
	}

	/**
	 * Custom print() method of the current Chrome Object.
	 * */
	public void print() {
		this.mMap.print();
	}

	@Override
	public String toString() {
		return "Chrome [mMap=" + mMap + ", mScore=" + mScore + "]";
	}

	@Override
	public int compareTo(Chrome o) {
		return this.mScore - o.getScore();
	}

}
