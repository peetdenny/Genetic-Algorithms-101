package com.jarden.genetic;

public class PopulationRunner implements Runnable {

	private final int threadId;
	private final Population population;

	public PopulationRunner(Population population,int threadId) {
		this.population = population;
		this.threadId = threadId;
		
	}

	@Override
	public void run() {
		population.go();
	}

}
