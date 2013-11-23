package com.texniti.ergasia;

//Paulos-Petros Tournaris 3110199 - p.tournaris@gmail.com	
//Georgios Tzanoydakhs 3110194 - chesterlos93@gmail.com
//Rousas Apostolos 3110173 - g.tzanoudakhs@gmail.com


import java.util.ArrayList;
import java.util.Arrays;

public class ExamDay {
	
	/*ArrayLists Declaration*/
	private ArrayList<String> mRoom;
	private ArrayList<String> mLesson;
	private ArrayList<String> mSemester;
	private ArrayList<String> mDepartment;
	private ArrayList<String> mRoomSize;
	private ArrayList<String> mRequiredSize;
	private ArrayList<String> mProfessor;
	private ArrayList<ArrayList<String>> mProfessorAvailableDays;

	/*Strings Declaration*/
	private String mTime;
	private String mDay;
	private String mDayFromMonth;

	/**
	 * Main Constructor
	 * @param time Time to set in this ExamDay instance
	 * @param day Day to set in this ExamDay instance*/
	public ExamDay(String time, String day){
		
		mRoom = new ArrayList<String>();
		mLesson = new ArrayList<String>();
		mSemester = new ArrayList<String>();
		mDepartment = new ArrayList<String>();
		mLesson = new ArrayList<String>();
		mRoomSize = new ArrayList<String>();
		mRequiredSize = new ArrayList<String>();
		mProfessor = new ArrayList<String>();
		mProfessorAvailableDays = new ArrayList<ArrayList<String>>();
		
		mTime = time;
		mDay = day;
	}
	
	/**
	 * Empty Constructor
	 * */
	public ExamDay(){
		
	}
	
	/**
	 * Set the time of the current ExamDay instance
	 * @param time The <code>time</code> to be set in the current <code>ExamDay</code> instance.*/
	public void setTime(String time) {
		this.mTime = time;
	}
	
	/**
	 * Set the day of the current ExamDay instance
	 * @param day The <code>day</code> to be set in the current <code>ExamDay</code> instance.*/
	public void setDay(String day) {
		this.mDay = day;
	}
	
	/**
	 * Set the dayFromMonth of the current ExamDay instance
	 * @param dayFromMonth The <code>dayFromMonth</code> to be set in the current <code>ExamDay</code> instance.*/
	public void setDayFromMonth(String dayFromMonth) {
		this.mDayFromMonth = dayFromMonth;
	}
	
	/**
	 * Add lesson to Lessons ArrayList
	 * @param lesson The lesson to be added in mLesson ArrayList*/
	public void setLesson(String lesson) {
		this.mLesson.add(lesson);
	}
	
	/**
	 * Fill <code>mLesson</code> ArrayList
	 * @param lessons The ArrayList to be added in <code>mLesson</code> ArrayList*/
	public void setAllLessons(ArrayList<String> lessons){
		this.mLesson.addAll(lessons);
	}
	
	/**
	 * Fill mRoom ArrayList
	 * @param rooms The ArrayList to be added in <code>mRoom</code> ArrayList*/
	public void setAllRooms(ArrayList<String> rooms){
		this.mRoom.addAll(rooms);
	}
	
	/**
	 * Fill mProfessor ArrayList
	 * @param professors The ArrayList to be added in <code>mProfessor</code> ArrayList*/
	public void setAllProfessors(ArrayList<String> professors){
		this.mProfessor.addAll(professors);
	}
	
	/**
	 * Fill mSemester ArrayList
	 * @param semesters The ArrayList to be added in <code>mSemester</code> ArrayList*/
	public void setAllSemester(ArrayList<String> semesters){
		this.mSemester.addAll(semesters);
	}
	
	/**
	 * Fill mDepartment ArrayList
	 * @param departments The ArrayList to be added in <code>mDepartment</code> ArrayList*/
	public void setAllDepartment(ArrayList<String> departments){
		this.mDepartment.addAll(departments);
	}
	
	/**
	 * Fill mRoomSize ArrayList
	 * @param roomSize The ArrayList to be added in <code>mRoomSize</code> ArrayList*/
	public void setAllRoomSize(ArrayList<String> roomSize){
		this.mRoomSize.addAll(roomSize);
	}
	
	/**
	 * Fill mRequiredSize ArrayList
	 * @param requiredSize The ArrayList to be added in <code>mRequiredSize</code> ArrayList*/
	public void setAllRequiredSize(ArrayList<String> requiredSize){
		this.mRequiredSize.addAll(requiredSize);
	}
	
	/**
	 * Fill mProfessorAvailableDays ArrayList
	 * @param days The List to be added in <code>mProfessorAvailableDays</code> ArrayList*/
	public void setProfessorAvailableDays(String days) {
		ArrayList<String> toBeAdded=new ArrayList<String>(Arrays.asList(days.split(",")));
		this.mProfessorAvailableDays.add(toBeAdded);
	}
	
	/**
	 * Add room to Rooms ArrayList
	 * @param room The <code>room</code> to be added in <code>mRoom</code> ArrayList*/
	public void setRoom(String room) {
		this.mRoom.add(room);
	}
	
	/**
	 * Add semester to Semesters ArrayList
	 * @param semester The <code>semester</code> to be added in <code>mSemester</code> ArrayList*/
	public void setSemester(String semester) {
		this.mSemester.add(semester);
	}
	
	/**
	 * Add department to Departments ArrayList
	 * @param department The <code>department</code> to be added in <code>mDepartment</code> ArrayList*/
	public void setDepartment(String department) {
		this.mDepartment.add(department);
	}
	
	/**
	 * Add roomSize to RoomSize ArrayList
	 * @param size The <code>size</code> to be added in <code>mRoomSize</code> ArrayList*/
	public void setRoomSize(String size) {
		this.mRoomSize.add(size);
	}
	
	/**
	 * Add requiredRoomSize to RequiredRoomSize ArrayList
	 * @param required The <code>requiredSize</code> to be added in <code>mRequiredSize</code> ArrayList*/
	public void setRequiredSize(String required) {
		this.mRequiredSize.add(required);
	}
	
	/**
	 * Add professor to Professors ArrayList
	 * @param professor The <code>professor</code> to be added in <code>mProfessor</code> ArrayList*/
	public void setProfessor(String professor) {
		this.mProfessor.add(professor);
	}
	
	/**
	 * Get lesson from Lessons ArrayList by position
	 * @param position Position of the item inside the ArrayList
	 * @return The chosen item from the ArrayList*/
	public String getLesson(int position) {
		return this.mLesson.get(position);
	}
	
	/**
	 * Get room from Rooms ArrayList by position
	 * @param position Position of the item inside the ArrayList
	 * @return The chosen item from the ArrayList*/
	public String getRoom(int position) {
		return this.mRoom.get(position);
	}

	/**
	 * Get department from Departments ArrayList by position
	 * @param position Position of the item inside the ArrayList
	 * @return The chosen item from the ArrayList*/
	public String getDepartment(int position) {
		return this.mDepartment.get(position);
	}
	
	/**
	 * Get semester from Semesters ArrayList by position
	 * @param position Position of the item inside the ArrayList
	 * @return The chosen item from the ArrayList*/
	public String getSemester(int position) {
		return this.mSemester.get(position);
	}
	
	/**
	 * Get roomSize from RoomSize ArrayList by position
	 * @param position Position of the item inside the ArrayList
	 * @return The chosen item from the ArrayList*/
	public String getRoomSizeAvailable(int position) {
		return this.mRoomSize.get(position);
	}
	
	/**
	 * Get requiredRoomSize from RequiredRoomSize ArrayList by position
	 * @param position Position of the item inside the ArrayList
	 * @return The chosen item from the ArrayList*/
	public String getRequiredRoomSize(int position) {
		return this.mRequiredSize.get(position);
	}
	
	/**
	 * Get professor from Professors ArrayList by position
	 * @param position Position of the item inside the ArrayList
	 * @return The chosen item from the ArrayList*/
	public String getProfessor(int position) {
		return this.mProfessor.get(position);
	}
	
	/**
	 * Get Departments ArrayList
	 * @return mDepartment ArrayList*/
	public ArrayList<String> getDepartmentList() {
		return this.mDepartment;
	}
	
	/**
	 * Get Semesters ArrayList
	 * @return mSemester ArrayList*/
	public ArrayList<String> getSemesterList() {
		return this.mSemester;
	}
	
	/**
	 * Get Lessons ArrayList
	 * @return mLesson ArrayList*/
	public ArrayList<String> getLessonsList(){
		return this.mLesson;
	}
	
	/**
	 * Get Professors ArrayList
	 * @return mProfessor ArrayList*/
	public ArrayList<String> getProfessorsList(){
		return this.mProfessor;
	}
	
	/**
	 * Get Rooms ArrayList
	 * @return mRoom ArrayList*/
	public ArrayList<String> getRoomsList(){
		return this.mRoom;
	}
	
	/**
	 * Get RequiredSize ArrayList
	 * @return mRequiredSize ArrayList*/
	public ArrayList<String> getRequiredSizeList(){
		return this.mRequiredSize;
	}
	
	/**
	 * Get RoomsSize ArrayList
	 * @return mRoomSize ArrayList*/
	public ArrayList<String> getRoomSizeList(){
		return this.mRoomSize;
	}
	
	/**
	 * Return the day of the current ExamDay instance
	 * @return The day of the current <code>ExamDay</code> instance.*/
	public String getDay() {
		return this.mDay;
	}
	
	/**
	 * Return the time of the current ExamDay instance
	 * @return The time of the current <code>ExamDay</code> instance.*/
	public String getTime() {
		return this.mTime;
	}
	
	/**
	 * Return the dayFromMonth of the current ExamDay instance
	 * @return The dayFromMonth of the current <code>ExamDay</code> instance.*/
	public String getDayFromMonth() {
		return this.mDayFromMonth;
	}
	
	public int  getProfessorAvailableDaysSize(){
		return this.mProfessorAvailableDays.size();
	};
	
	/**
	 * Return Lessons ArrayList size
	 * @return The size of <code>mLesson</code> ArrayList.*/
	public int getLessonSize(){
		return this.mLesson.size();
	}
	
	/**
	 * Return Rooms ArrayList size
	 * @return The size of <code>mRoom</code> ArrayList.*/
	public int getRoomsSize(){
		return this.mRoom.size();
	}
	
	/**
	 * Return RoomSizeAvailable ArrayList size
	 * @return The size of <code>mRoomSize</code> ArrayList.*/
	public int getRoomSizeAvailableSize(){
		return this.mRoomSize.size();
	}
	
	/**
	 * Return RequiredRoomSize ArrayList size
	 * @return The size of <code>mRequiredSize</code> ArrayList.*/
	public int getRequiredRoomSize(){
		return this.mRequiredSize.size();
	}
	
	/**
	 * Return Professors ArrayList size
	 * @return The size of <code>mProfessor</code> ArrayList.*/
	public int getProfessorSize(){
		return this.mProfessor.size();
	}
	
	/**
	 * Check Professor's Availability by day
	 * @param day The day to be checked.
	 * @return True if <code>mProfessorAvailableDays</code> contains the given argument.*/
	public boolean getProfessorAvailability(int index,String day) {
		return this.mProfessorAvailableDays.get(index).contains(day);
	}
	
	/**
	 * toString() Overrided method
	 * @return Custom string with the Day and Time of current instance.*/
	@Override
	public String toString(){
		return this.mDay+" "+this.mTime;
	}
	
}
