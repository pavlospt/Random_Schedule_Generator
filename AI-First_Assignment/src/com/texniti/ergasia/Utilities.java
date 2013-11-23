package com.texniti.ergasia;

//Paulos-Petros Tournaris 3110199 - p.tournaris@gmail.com	
//Georgios Tzanoydakhs 3110194 - chesterlos93@gmail.com
//Rousas Apostolos 3110173 - g.tzanoudakhs@gmail.com

import java.util.ArrayList;
import java.util.Random;

import com.models.Courses;
import com.models.Rooms;
import com.models.Slots;

public class Utilities {
	
	/**
	 * Empty Constructor
	 * */
	public Utilities(){
		
	}
	
	/**
	 * Return Day of Week based on given parameter. Used in {@link com.texniti.ergasia.Main#fillExamDayArray() fillExamDayArray()}
	 * @param i The counter of the for loop at that time. 
	 * @return Returns a <code>String</code> with the name of the Day selected,
	 * 		   returns an empty text if <code>i</code> is wrong.*/
	public static String findDay(int i){
		if (i == 0 || i == 5 || i == 10 || i == 15) {
			return "Monday";
		}
		if (i == 1 || i == 6 || i == 11 || i == 16) {
			return "Tuesday";
		}
		if (i == 2 || i == 7 || i == 12 || i == 17) {
			return "Wednesday";
		}
		if (i == 3 || i == 8 || i == 13 || i == 18) {
			return "Thursday";
		}
		if (i == 4 || i == 9 || i == 14 || i == 19) {
			return "Friday";
		}
		
		return "";
	}
	
	/**
	 * Return a Time Slot based on given parameter. Used in {@link com.texniti.ergasia.Main#fillExamDayArray() fillExamDayArray()}
	 * @param j The counter of the for loop at that time. 
	 * @return Returns a <code>String</code> with the Time Slot selected,
	 * 		   returns an empty text if <code>j</code> is wrong.*/
	public static String findTime(int j){
		if(j == 0){
			return "9:00-12:00";
		}
		if(j == 1){
			return "12:00-15:00";
		}
		if(j == 2){
			return "15:00-18:00";
		}
		return "";
	}
	
	/**
	 * Find Available days from Slots ArrayList. Used in {@link com.texniti.ergasia.Main#fillExamDayArray() fillExamDayArray()}
	 * @param i Position of the Day
	 * @param slots An ArrayList containing the all available slots from slots.json file.
	 * @return Returns a String with the available day,
	 * 		   in case something is wrong it returns an empty String*/
	public static String findAvailableDayFromMonth(int i, ArrayList<Slots> slots){

		if (i == 0 || i == 5 || i == 10 || i == 15) {
			String temp[] = slots.get(0).getCalendar().split(",");
			return temp[i/5];
		}
		
		if (i == 1 || i == 6 || i == 11 || i == 16) {
			String temp[] = slots.get(1).getCalendar().split(",");
			return temp[(i-1)/5];
		}
		
		if (i == 2 || i == 7 || i == 12 || i == 17) {
			String temp[] = slots.get(2).getCalendar().split(",");
			return temp[(i-2)/5];
		}
		
		if (i == 3 || i == 8 || i == 13 || i == 18) {
			String temp[] = slots.get(3).getCalendar().split(",");
			return temp[(i-3)/5];
		}
		
		if (i == 4 || i == 9 || i == 14 || i == 19) {
			String temp[] = slots.get(4).getCalendar().split(",");
			return temp[(i-4)/5];
		}
		return "";
	}
	
	/**
	 * Convert Boolean to Needed Text. Used in {@link com.texniti.ergasia.State#print() print()}
	 * @param flag The boolean to check
	 * @return Returns "Non Available" if flag is true or "Available" if it is false.*/
	public static String textFromBoolean(boolean flag){
		if(flag){
			return "Non Available";
		}
		return "Available";
	}
	
	/**
	 * Generate custom array containing concatenated Strings from both given ArrayLists.
	 * <p> Used in {@link com.texniti.ergasia.State#constraint8 constraint8()}
	 * @param departments An ArrayList containing the Departments.
	 * @param semester An ArrayList containing the Semesters.
	 * @param flag Boolean to check if we want to return an ArrayList with concatenated Strings from 
	 * 		       departments and semester OR just return an ArrayList with departments only.
	 * @return Returns an ArrayList containing concatenated (or not) Strings.
	 * */
	public static ArrayList<String> generateCustomArray(ArrayList<String> departments, ArrayList<String> semester, boolean flag){
		ArrayList<String> toBeReturned = new ArrayList<String>();
		for(int i=0;i<departments.size();i++){
			if(flag){
				toBeReturned.add(departments.get(i));
			}else{
				toBeReturned.add(departments.get(i) + " " + semester.get(i));
			}
			
		}
		return toBeReturned;
	}
	
	/**
	 * Checks if second ArrayList contains data from first ArrayList
	 * <p> Used in {@link com.texniti.ergasia.State#constraint6 constraint6()},
	 *  {@link com.texniti.ergasia.State#constraint8 constraint8()}
	 * @param first An ArrayList with the data to be checked if they exist.
	 * @param second The ArrayList that will be checked.
	 * @return Returns a number that shows how many times an element from the first ArrayList
	 * 		  existed in the second ArrayList
	 * */
	public static int checkLists(ArrayList<String> first, ArrayList<String> second){
		int temp = 0;
		for(int i=0;i<first.size();i++){
			if(second.contains(first.get(i))){
				temp++;
			}
		}
		return temp;
	}
	
	/**
	 * Check for same data inside an ArrayList
	 * <p> Used in {@link com.texniti.ergasia.State#constraint7 constraint7()}
	 * @param arrayList The ArrayList to be checked.
	 * @return Returns a number that shows if there were any same data in the <code>arrayList</code>
	 * 		   If there are no same data, it returns 0.
	 * */
	public static int checkForDoubles(ArrayList<String> arrayList){
		int temp = 0;
		for(int i=0;i<arrayList.size();i++){
			for(int j=i+1;j<arrayList.size();j++){
				if(arrayList.get(i).equals(arrayList.get(j))){
					temp++;
				}
			}
		}
		return temp;
	}
	
	
	/**
	 * Fills an ArrayList with DayTimeSlot objects for every Day and Every 3hour timespace.
	 * @param mSlots ArrayList containing data from slots.json file.
	 * @return Returns an ArrayList containing the created data.
	 * */
	public static ArrayList<DayTimeSlot> createDayTimeSlots(ArrayList<Slots> mSlots){
		ArrayList<DayTimeSlot> toBeReturned = new ArrayList<DayTimeSlot>();
		for(int i=0;i<mSlots.size()*4;i++){
			for(int j=0;j<3;j++){
				DayTimeSlot temp = new DayTimeSlot();
				temp.setDay(Utilities.findDay(i));
				temp.setTime(Utilities.findTime(j));
				temp.setDayFromMonth(Utilities.findAvailableDayFromMonth(i, mSlots));
				toBeReturned.add(temp);
			}
		}
		return toBeReturned;
	}
	
	
	/**
	 * Fills an ArrayList with LessonObject objects.
	 * @param mCourses ArrayList containing data from courses.json file.
	 * @param mRooms ArrayList containing data from rooms.json file.
	 * @return Returns an ArrayList containing the created data.
	 * */
	public static ArrayList<LessonObject> fillLessons(ArrayList<Courses> mCourses, ArrayList<Rooms> mRooms){
		ArrayList<LessonObject> toBeReturned = new ArrayList<LessonObject>();
		Random mRandom = new Random(System.currentTimeMillis());
		for(int i=0;i<mCourses.size();i++){
			int mRandomRoom = mRandom.nextInt(mRooms.size());
			LessonObject temp = new LessonObject();
			temp.setLessonName(mCourses.get(i).getCourseName());
			temp.setDepartment(mCourses.get(i).getDepartment());
			temp.setProfessor(mCourses.get(i).getInstructor());
			temp.setSemester(mCourses.get(i).getSemester());
			temp.setRequiredRoomSize(mCourses.get(i).getRequiredClassSize());
			temp.setRoom(mRooms.get(mRandomRoom).getRoomName());
			toBeReturned.add(temp);
		}
		return toBeReturned;
	}
	
}
