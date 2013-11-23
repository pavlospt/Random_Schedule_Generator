package com.texniti.ergasia;

//Paulos-Petros Tournaris 3110199 - p.tournaris@gmail.com	
//Georgios Tzanoydakhs 3110194 - chesterlos93@gmail.com
//Rousas Apostolos 3110173 - g.tzanoudakhs@gmail.com

import java.util.ArrayList;
import java.util.Random;

import com.models.Courses;
import com.models.Instructors;
import com.models.Rooms;

public class State implements Comparable<State> {

	/* String array [Days][Hours] */
	private ExamDay[] mExamDays;

	/* Random ints for Day and Hour Slots */
	private int mRandomLesson;
	private int mRandomRoom;

	/* Slots covered counter */
	private int mCoursePosition = 0;

	/* Random variable */
	private Random mRandom;

	/* Fitness Variable */
	public int mFitness;

	/* Courses Size */
	public int mCoursesArraySize;

	/**
	 * Main Constructor
	 * 
	 * @param coursesArraylist
	 *            An ArrayList containing the courses from <b>courses.json</b>
	 *            file
	 * @param roomsArraylist
	 *            An ArrayList containing the rooms from <b>rooms.json</b> file
	 * @param examDayArray
	 *            An ArrayList containing the ExamDay objects we created.
	 * @param instructorsArray
	 *            An ArrayList containing the instructors from
	 *            <b>instructors.json</b> file
	 * */
	public State(ArrayList<Courses> coursesArraylist,
			ArrayList<Rooms> roomsArraylist, ArrayList<ExamDay> examDayArray,
			ArrayList<Instructors> instructorsArray) {

		this.mExamDays = new ExamDay[examDayArray.size()];
		this.mRandom = new Random(System.currentTimeMillis());
		this.mCoursesArraySize = coursesArraylist.size();
		fillExamDayArray(examDayArray);

		while (mCoursePosition < coursesArraylist.size()) {
			this.mRandomLesson = mRandom.nextInt(examDayArray.size());
			this.mRandomRoom = mRandom.nextInt(roomsArraylist.size());

			int p;

			ExamDay temp = examDayArray.get(mRandomLesson);
			temp.setLesson(coursesArraylist.get(mCoursePosition)
					.getCourseName());
			temp.setRoom(roomsArraylist.get(mRandomRoom).getRoomName());
			temp.setSemester(coursesArraylist.get(mCoursePosition)
					.getSemester());
			temp.setDepartment(coursesArraylist.get(mCoursePosition)
					.getDepartment());
			temp.setRequiredSize(coursesArraylist.get(mCoursePosition)
					.getRequiredClassSize());
			temp.setRoomSize(roomsArraylist.get(mRandomRoom).getRoomSpace());
			temp.setProfessor(coursesArraylist.get(mCoursePosition)
					.getInstructor());

			for (p = 0; p < instructorsArray.size() - 1; p++) {
				if (instructorsArray
						.get(p)
						.getInstructor()
						.equals(coursesArraylist.get(mCoursePosition)
								.getInstructor())) {
					break;
				}
			}

			temp.setProfessorAvailableDays(instructorsArray.get(p).getNonAvailable());

			this.mExamDays[mRandomLesson] = temp;
			mCoursePosition++;
		}

		this.calculateHeuristic();
		// System.out.println("------------------------------------------>"+this.mCoursesArraySize);
	}

	/**
	 * Copy Constructor
	 * 
	 * @param array
	 *            Array to be copied.
	 * */
	public State(ExamDay[] array, int size) {
		this.mExamDays = new ExamDay[array.length];
		for (int i = 0; i < array.length; i++) {
			this.mExamDays[i] = array[i];
		}
		this.mCoursesArraySize = size;
		this.calculateHeuristic();
	}
	
	/**
	 * Convert Constructor
	 * */
	public State(ArrayList<ExamDay> examArray,CustomMap map,ArrayList<Rooms> roomsArraylist,ArrayList<Instructors> instructorsArray){
		
		this.mRandom = new Random(System.currentTimeMillis());
		this.mExamDays = new ExamDay[examArray.size()];
		
		for (int i = 0; i < examArray.size(); i++) {
			this.mExamDays[i] = examArray.get(i);
		}
		
		LessonObject key=null;
		DayTimeSlot value=null;
				
		for (int j=0;j<map.getSize();j++) {
			for (int i = 0; i < mExamDays.length; i++) {
				
				this.mRandomRoom = mRandom.nextInt(roomsArraylist.size());
				
				key=map.getKey(j);
				value=map.getValue(j);
				
				if (value.getDayFromMonth().equals(mExamDays[i].getDayFromMonth())
					&& value.getTime().equals(mExamDays[i].getTime())) {
					
					mExamDays[i].setLesson(key.getLessonName());
					mExamDays[i].setProfessor(key.getProfessor());
					mExamDays[i].setRoom(key.getRoom());
					mExamDays[i].setSemester(key.getSemester());
					mExamDays[i].setRequiredSize(key.getRequiredRoomSize());
					mExamDays[i].setDepartment(key.getDepartment());
					mExamDays[i].setRoom(roomsArraylist.get(mRandomRoom).getRoomName());
					mExamDays[i].setRoomSize(roomsArraylist.get(mRandomRoom).getRoomSpace());

					int p;
					for (p = 0; p < instructorsArray.size() - 1; p++) {
						if (instructorsArray.get(p).getInstructor().equals(key.getProfessor())) {
							break;
						}
					}

					mExamDays[i].setProfessorAvailableDays(instructorsArray.get(p).getNonAvailable());
				}
			}
		}
		
		this.calculateHeuristic();	
	}
	
	

	/**
	 * Fill the mExamDays array with data from the given ArrayList
	 * 
	 * @param examDayArray
	 * */
	public void fillExamDayArray(ArrayList<ExamDay> examDayArray) {
		for (int i = 0; i < examDayArray.size(); i++) {
			this.mExamDays[i] = examDayArray.get(i);
		}
	}

	/**
	 * Return array with slots
	 * 
	 * @return Returns the ExamDay[] array of the current State instance.
	 * */
	public ExamDay[] getSlots() {
		return this.mExamDays;
	}

	/**
	 *  Get Fitness
	 *  @return Returns the mFitness of the current State object.
	 *   */
	public int getFitness() {
		return this.mFitness;
	}

	/**
	 * Returns the length of the ExamDay[] array.
	 * 
	 * @return The length of the ExamDay[] array.
	 * */
	public int getArraySize() {
		return this.mExamDays.length;
	}

	/* Calculate Heuristic */
	private void calculateHeuristic() {
		this.mFitness += this.constraint1(false);
		this.mFitness += this.constraint2(false);
		this.mFitness += this.constraint3(false);
		this.mFitness += this.constraint4(false);
		this.mFitness += this.constraint5(false);
		this.mFitness += this.constraint6(false);
		this.mFitness += this.constraint7(false);
		this.mFitness += this.constraint8(false);
	}

	// 100 - return * 10 ---> weight

	/* Check for same day same hour */
	public int constraint1(boolean flag) {
		int counter = 100;
		for (int i = 0; i < mExamDays.length; i++) {
			if (mExamDays[i].getLessonSize() > 1) {
				for (int j = 0; j < mExamDays[i].getLessonSize(); j++) {
					for (int k = j + 1; k < mExamDays[i].getLessonSize(); k++) {
						if (mExamDays[i].getRoom(j).equals(
								mExamDays[i].getRoom(k))) {
							if (flag)
								System.out.println("Constrain 1 Broken at Day"
										+ mExamDays[i].getDay() + " "
										+ mExamDays[i].getDayFromMonth());
							counter -= 1;
						}
					}
				}
			}
		}
		if (flag)
			System.out.println("1--" + counter);
		return counter;
	}

	/* Same Department Different Lessons */
	public int constraint2(boolean flag) {
		int counter = 100;
		for (int i = 0; i < mExamDays.length; i++) {
			if (mExamDays[i].getLessonSize() > 1) {
				for (int j = 0; j < mExamDays[i].getLessonSize(); j++) {
					for (int k = j + 1; k < mExamDays[i].getLessonSize(); k++) {
						if (mExamDays[i].getSemester(j).equals(
								mExamDays[i].getSemester(k))
								&& mExamDays[i].getDepartment(j).equals(
										mExamDays[i].getDepartment(k))) {
							if (flag)
								System.out.println("Constrain 2 Broken at Day"
										+ mExamDays[i].getDay() + " "
										+ mExamDays[i].getDayFromMonth());
							counter -= 1;
						}
					}
				}
			}
		}
		if (flag)
			System.out.println("2--counter" + counter);
		return counter;
	}

	/* Fit Room Size to Size Needed */
	public int constraint3(boolean flag) {
		int counter = 70;

		for (int i = 0; i < mExamDays.length; i++) {

			if (mExamDays[i].getRoomSizeAvailableSize() > 1) {
				for (int j = 0; j < mExamDays[i].getLessonSize(); j++) {
					if (mExamDays[i].getRoomSizeAvailable(j).equals(
							mExamDays[i].getRequiredRoomSize(j))) {
						if (flag)
							System.out.println("Constrain 3 Broken at Day"
									+ mExamDays[i].getDay() + " "
									+ mExamDays[i].getDayFromMonth());
						counter -= 3;
					}
				}
			}
		}
		if (flag)
			System.out.println("3--" + counter);
		return counter;
	}

	/* Professor Available at Day needed */
	public int constraint4(boolean flag) {
		int counter = 50;
		for (int i = 0; i < mExamDays.length; i++) {
			for(int j=0;j<mExamDays[i].getProfessorAvailableDaysSize();j++){
				if (mExamDays[i].getProfessorAvailability(j,mExamDays[i].getDayFromMonth())) {
					if (flag)
						System.out.println("Constrain 4 Broken at Day"
								+ mExamDays[i].getDay() + " "
								+ mExamDays[i].getDayFromMonth());
					counter -= 5;
				}
			}
		}
		if (flag)
			System.out.println("4--" + counter);
		return counter;
	}

	/* Same day same department and semester */
	public int constraint5(boolean flag) {
		int counter = 60;
		for (int i = 0; i < mExamDays.length; i += 3) {
	
			ArrayList<String> temp1 = new ArrayList<>();
			// Initialize Array with string of department and semester
			for (int p = 0; p < mExamDays[i].getDepartmentList().size(); p++) {
				temp1.add(mExamDays[i].getDepartment(p)
						+ mExamDays[i].getSemester(p));
			}

			ArrayList<String> temp2 = new ArrayList<>();
			for (int p = 0; p < mExamDays[i + 1].getDepartmentList().size(); p++) {
				temp2.add(mExamDays[i + 1].getDepartment(p)
						+ mExamDays[i + 1].getSemester(p));
			}
			ArrayList<String> temp3 = new ArrayList<>();

			for (int p = 0; p < mExamDays[i + 2].getDepartmentList().size(); p++) {
				temp3.add(mExamDays[i + 2].getDepartment(p)
						+ mExamDays[i + 2].getSemester(p));
			}

			// Check if string (department +semester) is the same

			for (int j = 0; j < temp1.size(); j++) {
				if (temp2.contains(temp1.get(j))) {
					if (flag)
						System.out.println("Constrain 5.1 Broken at Day"
								+ mExamDays[i].getDay() + " "
								+ mExamDays[i].getDayFromMonth());
					counter -= 4;
				}
			}

			for (int j = 0; j < temp1.size(); j++) {
				if (temp3.contains(temp1.get(j))) {
					if (flag)
						System.out.println("Constrain 5.2 Broken at Day"
								+ mExamDays[i].getDay() + " "
								+ mExamDays[i].getDayFromMonth());
					counter -= 4;
				}
			}

			for (int j = 0; j < temp2.size(); j++) {
				if (temp3.contains(temp2.get(j))) {
					if (flag)
						System.out.println("Constrain 5.3 Broken at Day"
								+ mExamDays[i].getDay() + " "
								+ mExamDays[i].getDayFromMonth());
					counter -= 4;
				}
			}
		}
		if (flag)
			System.out.println("5--" + counter);
		return counter;
	}

	/* Continuous days Same Department same Semester */
	public int constraint6(boolean flag) {
		int counter = 70;
		ArrayList<String> tempArrayNow;
		ArrayList<String> tempArrayNext;
		for (int i = 0; i < mExamDays.length; i += 3) {
			if (mExamDays[i].getDay().equals("Friday"))
				continue;

			tempArrayNow = new ArrayList<String>();
			tempArrayNext = new ArrayList<String>();
			for (int k = i; k < i + 2; k++) {
				tempArrayNow.addAll(Utilities.generateCustomArray(
						mExamDays[k].getDepartmentList(),
						mExamDays[k].getSemesterList(), false));
				tempArrayNext.addAll(Utilities.generateCustomArray(
						mExamDays[k + 1].getDepartmentList(),
						mExamDays[k + 1].getSemesterList(), false));
			}
			int temp = Utilities.checkLists(tempArrayNow, tempArrayNext);
			if (temp != 0) {
				counter -= temp * 3;
				if (flag)
					System.out.println("Constrain 6 Broken at Day"
							+ mExamDays[i].getDay() + " "
							+ mExamDays[i].getDayFromMonth() + "--" + temp);
			}

		}
		if (flag)
			System.out.println("6--" + counter);
		return counter;
	}

	/* Same day same department lessons */
	public int constraint7(boolean flag) {
		int counter = 50;
		ArrayList<String> tempArray;
		for (int i = 0; i < mExamDays.length; i += 3) {
			tempArray = new ArrayList<String>();
			for (int j = i; j < i + 3; j++) {
				tempArray.addAll(mExamDays[j].getDepartmentList());
			}
			int temp = Utilities.checkForDoubles(tempArray);
			if (temp != 0) {
				counter -= temp * 5;
				if (flag)
					System.out.println("Constrain 7 Broken at Day"
							+ mExamDays[i].getDay() + " "
							+ mExamDays[i].getDayFromMonth() + "--" + temp);
			}
		}
		if (flag)
			System.out.println("7--" + counter);
		return counter;
	}

	/* Continuous days same departments lessons */
	public int constraint8(boolean flag) {
		int counter = 60;
		ArrayList<String> tempArrayNow;
		ArrayList<String> tempArrayNext;
		for (int i = 0; i < mExamDays.length; i += 3) {
			if (mExamDays[i].getDay().equals("Friday"))
				continue;

			tempArrayNow = new ArrayList<String>();
			tempArrayNext = new ArrayList<String>();
			for (int k = i; k < i + 3; k++) {
				tempArrayNow.addAll(Utilities.generateCustomArray(
						mExamDays[k].getDepartmentList(),
						mExamDays[k].getSemesterList(), true));
				tempArrayNext.addAll(Utilities.generateCustomArray(
						mExamDays[k].getDepartmentList(),
						mExamDays[k].getSemesterList(), true));
			}
			int temp = Utilities.checkLists(tempArrayNow, tempArrayNext);
			if (temp != 0) {
				counter -= temp * 4;
				if (flag)
					System.out.println("Constrain 8 Broken at Day"
							+ mExamDays[i].getDay() + " "
							+ mExamDays[i].getDayFromMonth() + "--" + temp);
			}

		}
		if (flag)
			System.out.println("8--" + counter);
		return counter;
	}
	

	
	/**
	 * Transforms the current State to a custom form
	 * */
	public String toString(){
		String s="";
		
		for(int i=0;i<mExamDays.length;i++){
			if(mExamDays[i].getLessonSize()>0){
				
				s+= mExamDays[i].getDay()+" "+mExamDays[i].getDayFromMonth()+"/12/2013 ,"+ "Time : "+mExamDays[i].getTime()+"\n";
				
				for(int j=0;j<mExamDays[i].getLessonSize();j++){
					s+="\tLesson : " +mExamDays[i].getLesson(j)+", Department : "+mExamDays[i].getDepartment(j)+
						", Semester : "+mExamDays[i].getSemester(j)+", Room : "+mExamDays[i].getRoom(j)+
						", Proffessor : "+mExamDays[i].getProfessor(j);
					
					if(mExamDays[i].getProfessorAvailability(j,mExamDays[i].getDayFromMonth()))
					{
						s+="(Not Available)\n";
					}
					else{
						s+="(Available)\n";
					}
				}
				s+="\n";
			}	
		}
		
		return s;
	}

	@Override
	public int compareTo(State o) {
		return this.mFitness - o.getFitness();
	}

}
