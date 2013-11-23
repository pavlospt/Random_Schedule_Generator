package com.texniti.ergasia;

//Paulos-Petros Tournaris 3110199 - p.tournaris@gmail.com	
//Georgios Tzanoydakhs 3110194 - chesterlos93@gmail.com
//Rousas Apostolos 3110173 - g.tzanoudakhs@gmail.com

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.models.Courses;
import com.models.CoursesParser;
import com.models.Departments;
import com.models.DepartmentsParser;
import com.models.Instructors;
import com.models.InstructorsParser;
import com.models.Rooms;
import com.models.RoomsParser;
import com.models.Slots;
import com.models.SlotsParser;

public class Main {
	
	/* Models and Parsers static declaration */
	
	/* Courses */
	private static ArrayList<Courses> mCourses;
	private static CoursesParser mCoursesParser;
	
	/* Departments */
	private static ArrayList<Departments> mDepartments;
	private static DepartmentsParser mDepartmentsParser;
	
	/* Instructors */
	private static ArrayList<Instructors> mInstructors;
	private static InstructorsParser mInstructorsParser;
	
	/* Rooms */
	private static ArrayList<Rooms> mRooms;
	private static RoomsParser mRoomsParser;
	
	/* Slots */
	private static ArrayList<Slots> mSlots;
	private static SlotsParser mSlotsParser;
	private static int mSlotsSize;
	
	
	/* Current Directory Detection */
	private static File mCurrentDirectory = new File(new File(".").getAbsolutePath());
	private static String mCurrentDirectoryPath = mCurrentDirectory.getAbsolutePath()
												.substring(0,mCurrentDirectory.getAbsolutePath().length() - 1);
	
	/* JSON Files Base Path */
	private static String mBasePath =  mCurrentDirectoryPath + "\\JSON Files\\";
	
	/* JSON Path for each file */
	private static String mCoursesJsonPath = 	 mBasePath + "courses.json";
	private static String mDepartmentsJsonPath = mBasePath + "departments.json";
	private static String mInstructorsJsonPath = mBasePath + "instructors.json";
	private static String mRoomsJsonPath = 		 mBasePath + "rooms.json";
	private static String mSlotsJsonPath = 		 mBasePath + "slots.json";
	
	/* Json Library Variables*/
	private static JsonReader mJsonReader;
	private static JsonObject mJsonObject;
	
	/* Main */
	public static void main(String[] args) {
		
		try {
			listsInstantiation();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		Genetic newGen= new Genetic(mCourses,mRooms,mInstructors,mSlots);
		Chrome x=newGen.geneticAlgorithm(100, 0.01,300,1000);
		System.out.println("Score--> "+x.getScore());
		
		String schedule= new State(Main.fillExamDayArray(), x.getMap(), mRooms,mInstructors).toString();
		
		write_file(schedule);

	
	}
	
	public static void write_file(String s){
		File f=null;
		BufferedWriter wr= null;
		
		try 
		{
			f = new File("schedule.txt");
		}
		catch(NullPointerException e)
		{
			System.err.println("File not found!");
		}
		
		try
		{
			f.delete();
		}
		catch(SecurityException e)
		{
			System.err.println("Error in file deletion!");
		}
		
		try
		{
			wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("schedule.txt")));
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Error opening file.");
		}
		
		try
		{
			wr.write(s);
		}
		catch(IOException e)
		{
			System.err.println("Error while writing file!");
		}
		catch(NullPointerException npe)
		{
			System.err.println("Tried to write empty string!");
		}
		
		try
		{
			wr.close();
		}
		catch(IOException e)
		{
			System.err.println("Error while trying to close file!");
		}
		
		System.out.println("scedule.txt successfully created.");
	}

	/**
	 * Instantiate ArrayLists and  Model Parsers - Add models into ArrayLists
	 * */
	public static void listsInstantiation() throws FileNotFoundException{
		
		mCourses = new ArrayList<Courses>();
		mDepartments = new ArrayList<Departments>();
		mInstructors = new ArrayList<Instructors>();
		mRooms = new ArrayList<Rooms>();
		mSlots = new ArrayList<Slots>();
		
		mCoursesParser = new CoursesParser(getJsonObjectFromPath(mCoursesJsonPath));
		mDepartmentsParser = new DepartmentsParser(getJsonObjectFromPath(mDepartmentsJsonPath));
		mInstructorsParser = new InstructorsParser(getJsonObjectFromPath(mInstructorsJsonPath));
		mRoomsParser = new RoomsParser(getJsonObjectFromPath(mRoomsJsonPath));
		mSlotsParser = new SlotsParser(getJsonObjectFromPath(mSlotsJsonPath));
		
		mCourses = mCoursesParser.getCourses();
		mDepartments = mDepartmentsParser.getDepartments();
		mInstructors = mInstructorsParser.getInstructors();
		mRooms = mRoomsParser.getRooms();
		mSlots = mSlotsParser.getSlots();
		
		mSlotsSize = mSlots.size();
		
	}
	
	/**
	 *  Return JsonObject object based on JSON file path given
	 *  @param jsonPath The path which contains the JSON file to read.
	 *  @return Returns the JSON Object created from the file that was read.*/
	public static JsonObject getJsonObjectFromPath(String jsonPath){
		try {
			mJsonReader = Json.createReader(new FileReader(jsonPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mJsonObject = mJsonReader.readObject();
		mJsonReader.close();
		return mJsonObject;
		
	}

	/**
	 * Fills an ArrayList with ExamDay objects for every Day and Every 3hour timespace.
	 * @return Returns an ArrayList containing the created data.
	 * */
	public static ArrayList<ExamDay> fillExamDayArray(){
		ArrayList<ExamDay> mExamDayTemp = new ArrayList<ExamDay>();
		for(int i=0;i<mSlotsSize*4;i++){
			for(int j=0;j<3;j++){
				ExamDay temp = new ExamDay(Utilities.findTime(j),Utilities.findDay(i));
				temp.setDayFromMonth(Utilities.findAvailableDayFromMonth(i, mSlots));
				mExamDayTemp.add(temp);
			}
		}
		return mExamDayTemp;
	}
	
}
