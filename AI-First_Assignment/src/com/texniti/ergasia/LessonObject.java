package com.texniti.ergasia;

//Paulos-Petros Tournaris 3110199 - p.tournaris@gmail.com	
//Georgios Tzanoydakhs 3110194 - chesterlos@gmail.com
//Rousas Apostolos 3110173 - g.tzanoudakhs@gmail.com

public class LessonObject {

	private String mProfessor;
	private String mSemester;
	private String mRequiredRoomSize;
	private String mDepartment;
	private String mRoom;
	private String mLessonName;

	/**
	 * Empty Constructor
	 * */
	public LessonObject() {

	}

	/**
	 * Return the lessonName of the current LessonObejct instance
	 * @return The mLessonName of the current <code>LessonObejct</code> instance.*/
	public String getLessonName() {
		return this.mLessonName;
	}

	/**
	 * Set the lessonName of the current LessonObject instance
	 * @param mName The <code>name</code> to be set in the current <code>LessonObject</code> instance.*/
	public void setLessonName(String mName) {
		this.mLessonName = mName;
	}

	/**
	 * Return the professor of the current LessonObejct instance
	 * @return The mProfessor of the current <code>LessonObejct</code> instance.*/
	public String getProfessor() {
		return this.mProfessor;
	}

	/**
	 * Set the professor of the current LessonObject instance
	 * @param mProfessor The <code>professor</code> to be set in the current <code>LessonObject</code> instance.*/
	public void setProfessor(String mProfessor) {
		this.mProfessor = mProfessor;
	}

	/**
	 * Return the semester of the current LessonObejct instance
	 * @return The mSemester of the current <code>LessonObejct</code> instance.*/
	public String getSemester() {
		return this.mSemester;
	}

	/**
	 * Set the semester of the current LessonObject instance
	 * @param mSemester The <code>semester</code> to be set in the current <code>LessonObject</code> instance.*/
	public void setSemester(String mSemester) {
		this.mSemester = mSemester;
	}

	/**
	 * Return the requiredRoomSize of the current LessonObejct instance
	 * @return The mRequiredRoomSize of the current <code>LessonObejct</code> instance.*/
	public String getRequiredRoomSize() {
		return this.mRequiredRoomSize;
	}

	/**
	 * Set the requiredRoomSize of the current LessonObject instance
	 * @param mRequiredRoomSize The <code>requiredRoomSize</code> to be set in the current <code>LessonObject</code> instance.*/
	public void setRequiredRoomSize(String mRequiredRoomSize) {
		this.mRequiredRoomSize = mRequiredRoomSize;
	}

	/**
	 * Return the department of the current LessonObejct instance
	 * @return The mDepartment of the current <code>LessonObejct</code> instance.*/
	public String getDepartment() {
		return mDepartment;
	}

	/**
	 * Set the department of the current LessonObject instance
	 * @param mDepartment The <code>department</code> to be set in the current <code>LessonObject</code> instance.*/
	public void setDepartment(String mDepartment) {
		this.mDepartment = mDepartment;
	}

	/**
	 * Return the room of the current LessonObejct instance
	 * @return The mRoom of the current <code>LessonObejct</code> instance.*/
	public String getRoom() {
		return this.mRoom;
	}

	/**
	 * Set the room of the current LessonObject instance
	 * @param mRoom The <code>room</code> to be set in the current <code>LessonObject</code> instance.*/
	public void setRoom(String mRoom) {
		this.mRoom = mRoom;
	}

	@Override
	public String toString() {
		return this.mLessonName;
	}

}
