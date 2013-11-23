package com.texniti.ergasia;

//Paulos-Petros Tournaris 3110199 - p.tournaris@gmail.com	
//Georgios Tzanoydakhs 3110194 - chesterlos93@gmail.com
//Rousas Apostolos 3110173 - g.tzanoudakhs@gmail.com


public class DayTimeSlot {

	private String mDay;
	private String mTime;
	private String mDayFromMonth;

	/**
	 * Empty Constructor
	 * */
	public DayTimeSlot() {

	}

	/**
	 * Main Constructor
	 * */
	public DayTimeSlot(DayTimeSlot d) {
		this.mDay = d.getDay();
		this.mTime = d.getTime();
		this.mDayFromMonth = d.getDayFromMonth();
	}

	/**
	 * Return the day of the current DayTimeSlot instance
	 * @return The day of the current <code>DayTimeSlot</code> instance.*/
	public String getDay() {
		return mDay;
	}

	/**
	 * Set the day of the current DayTimeSlot instance
	 * @param mDay The <code>mDay</code> to be set in the current <code>DayTimeSlot</code> instance.*/
	public void setDay(String mDay) {
		this.mDay = mDay;
	}
	
	/**
	 * Return the time of the current DayTimeSlot instance
	 * @return The time of the current <code>DayTimeSlot</code> instance.*/
	public String getTime() {
		return mTime;
	}

	/**
	 * Set the time of the current DayTimeSlot instance
	 * @param mTime The <code>mTime</code> to be set in the current <code>DayTimeSlot</code> instance.*/
	public void setTime(String mTime) {
		this.mTime = mTime;
	}

	/**
	 * Return the dayFromMonth of the current DayTimeSlot instance
	 * @return The mDayFromMonth of the current <code>DayTimeSlot</code> instance.*/
	public String getDayFromMonth() {
		return mDayFromMonth;
	}

	/**
	 * Set the dayFromMonth of the current DayTimeSlot instance
	 * @param mDayFromMonth The <code>mDayFromMonth</code> to be set in the current <code>DayTimeSlot</code> instance.*/
	public void setDayFromMonth(String mDayFromMonth) {
		this.mDayFromMonth = mDayFromMonth;
	}

	@Override
	public String toString() {
		return this.mDay + " -- " + this.mDayFromMonth + " -- " + this.mTime;
	}

}
