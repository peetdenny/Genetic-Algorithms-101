package com.jarden.genetic;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Population {
	
	private static final double CHANCE_OF_MIGRATION = 0.01;
	private final String goal;
	private LinkedList<Chromasome> members;
	private long generationNumber=0;
	private final int genSize;
	boolean initialRun=true;
	public long getGenerationNumber() {
		return generationNumber;
	}



	public void setGenerationNumber(long generationNumber) {
		this.generationNumber = generationNumber;
	}

	private double probablilityOfMutation=0.5;
	private final Civilization civilization;
	private boolean found;

	public Population(String goal,int size, Civilization civilization){
		this.goal = goal;
		this.genSize = size;
		this.civilization = civilization;
		this.members = new LinkedList<Chromasome>();
		for(int i=0;i<size;i++){
			Chromasome randomChromasome = Chromasome.createRandom(goal.length());
			if(initialRun){
//				System.out.println(randomChromasome);
			}
			members.add(randomChromasome);
		}
		initialRun =false;
		
	}
	

	
	public boolean generation(){
		if(found){
//			System.out.println("Found elsewhere, exiting thread");
			return true;
		}
		generationNumber++;
		for(Chromasome c: members){
			c.calculateCost(goal);
		}
		Collections.sort(members);	// sorts them low to high
		Chromasome leadingMatch = members.get(0);
		
		if(generationNumber % 1000000 == 0){
//			System.out.printf("Best match so far after %s generations: %s\n", leadingMatch,generationNumber);
		}
		
		if(leadingMatch.getCode().equals(goal)){
			System.out.printf("\nFound string '%s' in %s generations \n",leadingMatch.getCode(),generationNumber);
			civilization.complete();
			return true;
		}
		
		// mate the top two and replace the bottom two
		List<Chromasome> kids = leadingMatch.mate(members.get(1));
		if(civilization!= null && CoreRandom.getRandom().nextDouble() < CHANCE_OF_MIGRATION){
			migrate(kids);
		}else{
			members.remove(members.size()-1);
			members.remove(members.size()-1);
			members.addAll(kids);
		}
		assert(members.size() == genSize);
		// Mutate!!!!
		for(Chromasome c:members){
			c.mutate(probablilityOfMutation);
		}
		if(generationNumber % 1000 ==0){
			Collection<Chromasome> immigrants = civilization.getImmigrants(this);
			if(immigrants.size() >0){
				for(Chromasome i: immigrants){
					members.remove(members.size()-1);
					members.add(0, i);
				}
			}
		}
		
		return false;
	}
	
	private void migrate(List<Chromasome> kids) {
		civilization.emigrate(this,kids);
	}



	public void go(){
		while(!generation());
	}



	public void setFound() {
		this.found = true;
	}

}
