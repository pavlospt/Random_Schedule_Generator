package com.texniti.ergasia;

//Paulos-Petros Tournaris 3110199 - p.tournaris@gmail.com	
//Georgios Tzanoydakhs 3110194 - chesterlos93@gmail.com
//Rousas Apostolos 3110173 - g.tzanoudakhs@gmail.com

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.models.Courses;
import com.models.Instructors;
import com.models.Rooms;
import com.models.Slots;

public class Genetic {

	private ArrayList<Chrome> mPopulation;
	private ArrayList<Integer> mFitnessBounds;

	private ArrayList<Courses> mCourses;
	private ArrayList<Rooms> mRooms;
	private ArrayList<Instructors> mInstructors;
	private ArrayList<Slots> mSlots;

	/**
	 * Empty Constructor
	 * */
	public Genetic(ArrayList<Courses> coursesArraylist,ArrayList<Rooms> roomsArraylist,
				   ArrayList<Instructors> instructorsArray, ArrayList<Slots> slots) {
		
		this.mCourses = new ArrayList<Courses>(coursesArraylist);
		this.mRooms = new ArrayList<Rooms>(roomsArraylist);
		this.mInstructors = new ArrayList<Instructors>(instructorsArray);
		this.mSlots = new ArrayList<Slots>(slots);
	}

	/**
	 * Reproduce method that generates a new <code>Chrome</code> object from the
	 * two given ones.
	 * 
	 * @param x
	 *            First <code>Chrome</code> object
	 * @param y
	 *            Second <code>Chrome</code> object
	 * @return The <code>Chrome</code> object which was created from
	 *         <code>x</code> and <code>y</code>
	 * */
	public Chrome reproduce(Chrome x, Chrome y) {		
		Random r = new Random();
		CustomMap childGenes=new CustomMap();
		
		// Randomly choose the intersection point
		int intersectionPoint = r.nextInt(x.getSize());
		
		for(int i=0;i<intersectionPoint;i++){
			childGenes.addKey(x.getMapKey(i));
			childGenes.addValue(x.getMapValue(i));
		}
		
		for(int i=intersectionPoint; i<x.getSize(); i++)
		{
			childGenes.addKey(y.getMapKey(i));
			childGenes.addValue(y.getMapValue(i));
		}
		return new Chrome(childGenes,mRooms,mInstructors,mSlots);
	}

	/**
	 *  Genetic Algorithm 
	 *  @param populationSize The size of the new ArrayList
	 *  @param mutationProbability The number of the probability we want.
	 *  @param minimumFitness THe minimumFitness score of our generated Chrome object.
	 *  @param maximumSteps The maximum steps for which the algorithm will run for.
	 *  @return Returns a new Chrome object.*/
	public Chrome geneticAlgorithm(int populationSize,double mutationProbability, int minimumFitness, int maximumSteps) {
		initializePopulation(populationSize);
		Random r = new Random(System.currentTimeMillis());
		
		for(int step=0;step<maximumSteps;step++){
			
			ArrayList<Chrome> newPopulation = new ArrayList<Chrome>();
			
			for(int i=0;i<populationSize;i++){
				
				int xIndex = this.mFitnessBounds.get(r.nextInt(this.mFitnessBounds.size()));
				Chrome x = this.mPopulation.get(xIndex);
				
				int yIndex = this.mFitnessBounds.get(r.nextInt(this.mFitnessBounds.size()));
				while(yIndex == xIndex)
				{
					yIndex = this.mFitnessBounds.get(r.nextInt(this.mFitnessBounds.size()));
				}
				Chrome y = this.mPopulation.get(yIndex);
				
				Chrome child = this.reproduce(x, y);
				
				if(r.nextDouble() < mutationProbability)
				{
					child.mutate(Utilities.createDayTimeSlots(mSlots));
				}
				
				newPopulation.add(child);	
			}
			this.mPopulation = new ArrayList<Chrome>(newPopulation);
			
			Collections.sort(this.mPopulation, Collections.reverseOrder());
            
			
			if(this.mPopulation.get(0).getScore() >= minimumFitness)
			{
                System.out.println("Finished after " + step + " steps...");
				return this.mPopulation.get(0);
			}
			
            //We update the fitnessBounds arrayList
			this.updateFitnessBounds();
		}

        System.out.println("Finished after " + maximumSteps + " steps...");
		return this.mPopulation.get(0);
	}

	/**
	 *  Initialize population 
	 *  @param populationSize The size of the population we need.
	 *  */
	public void initializePopulation(int populationSize) {
			this.mPopulation= new ArrayList<Chrome>();
		
		for (int i = 0; i < populationSize; i++) {
			this.mPopulation.add(new Chrome(this.mRooms, this.mCourses, this.mSlots, this.mInstructors));
		}
		this.updateFitnessBounds();
	}

	/**
	 *  Update fitness bounds for every element in the <code>mPopulation</code> ArrayList.
	 *  */
	public void updateFitnessBounds() {
		this.mFitnessBounds = new ArrayList<Integer>();
		
		for (int i = 0; i < this.mPopulation.size(); i++) {
			for (int j = 0; j < this.mPopulation.get(i).getScore(); j++) {
				this.mFitnessBounds.add(i);
			}
		}
	}

}
