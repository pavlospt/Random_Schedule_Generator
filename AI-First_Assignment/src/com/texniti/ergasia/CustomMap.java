package com.texniti.ergasia;

//Paulos-Petros Tournaris 3110199 - p.tournaris@gmail.com	
//Georgios Tzanoydakhs 3110194 - chesterlos93@gmail.com
//Rousas Apostolos 3110173 - g.tzanoudakhs@gmail.com


import java.util.ArrayList;
import java.util.Random;

import com.models.Courses;
import com.models.Rooms;
import com.models.Slots;

public class CustomMap {
	
	private ArrayList<LessonObject> mLessonObjectArray;
	private ArrayList<DayTimeSlot> mDayTimeSlotArray;

	/*Main Constructor*/
	public CustomMap(ArrayList<Rooms> rooms, ArrayList<Courses> courses,
			ArrayList<Slots> slots) {
		
		this.mLessonObjectArray = new ArrayList<LessonObject>();
		this.mDayTimeSlotArray = new ArrayList<DayTimeSlot>();

		Random r = new Random(System.currentTimeMillis());

		ArrayList<LessonObject> lessons = new ArrayList<LessonObject>();
		lessons = Utilities.fillLessons(courses, rooms);

		ArrayList<DayTimeSlot> dayTimeSlots = new ArrayList<DayTimeSlot>();
		dayTimeSlots = Utilities.createDayTimeSlots(slots);

		for (int i = 0; i < lessons.size(); i++) {
			this.mLessonObjectArray.add(lessons.get(i));
			this.mDayTimeSlotArray.add(dayTimeSlots.get(r.nextInt(dayTimeSlots.size())));
		}
	}
	
	/*Copy Constructor*/
	public CustomMap(CustomMap map) {
		this.mLessonObjectArray = new ArrayList<LessonObject>();
		this.mDayTimeSlotArray = new ArrayList<DayTimeSlot>();

		for (int i = 0; i < map.getSize(); i++) {
			this.mLessonObjectArray.add(map.getKey(i));
			this.mDayTimeSlotArray.add(map.getValue(i));
		}
	}
	
	/*Empty Constructor*/
	public CustomMap() {
		this.mLessonObjectArray = new ArrayList<LessonObject>();
		this.mDayTimeSlotArray = new ArrayList<DayTimeSlot>();
	}

	/**
	 * This method adds a LessonObject to <code>mLessonObjectArray</code> ArrayList.
	 * @param obj LessonObject object to be added in ArrayList.*/
	public void addKey(LessonObject obj) {
		this.mLessonObjectArray.add(obj);
	}

	/**
	 * This method adds a DayTimeSlot to <code>mDayTimeSlotArray</code> ArrayList.
	 * @param obj DayTimeSlot object to be added in ArrayList.*/
	public void addValue(DayTimeSlot obj) {
		this.mDayTimeSlotArray.add(obj);
	}
	
	/**
	 * This method sets the index of a LessonObject in <code>mLessonObjectArray</code> ArrayList.
	 * @param index The new position of the LessonObject object.
	 * @param obj LessonObject object to change its position in ArrayList.*/
	public void setKey(int index, LessonObject obj) {
		this.mLessonObjectArray.set(index, obj);
	}

	/**
	 * This method sets the index of a DayTimeSlot in <code>mDayTimeSlotArray</code> ArrayList.
	 * @param index The new position of the DayTimeSlot object.
	 * @param obj DayTimeSlot object to change its position in ArrayList.*/
	public void setValue(int index, DayTimeSlot obj) {
		this.mDayTimeSlotArray.set(index, obj);
	}

	/**
	 * Returns the size of the current CustomMap object.
	 * @return Returns the size of the current CustomMap object.*/
	public int getSize() {
		return this.mLessonObjectArray.size();
	}

	/**
	 * Returns a LessonObject object, based on the position given, from the <code>mLessonObjectArray</code> ArrayList.
	 * @param i The position of the object we want.
	 * @return Returns a LessonObject object.*/
	public LessonObject getKey(int i) {
		return this.mLessonObjectArray.get(i);
	}

	/**
	 * Returns a DayTimeSlot object, based on the position given, from the <code>mDayTimeSlotArray</code> ArrayList.
	 * @param i The position of the object we want.
	 * @return Returns a DayTimeSlot object.*/
	public DayTimeSlot getValue(int i) {
		return this.mDayTimeSlotArray.get(i);
	}

	/**
	 * Custom print of the current CustomMap object.
	 * */
	public void print() {
		for (int i = 0; i < mLessonObjectArray.size(); i++) {
			System.out.println(mLessonObjectArray.get(i) + " --> "
					+ this.mDayTimeSlotArray.get(i));
		}
	}

	@Override
	public String toString() {
		return "CustomMap [mLessonObjectArray=" + mLessonObjectArray
				+ ", mDayTimeSlot=" + this.mDayTimeSlotArray + "]";
	}

}
