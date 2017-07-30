package com.jarden.genetic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.jarden.genetic.tools.Timer;

/**
 * 19 chars: 3991 gens over 10 iterations
 * 10 generations was 14526 in 787 seconds
 * 50 generations was 9792 in 2662 seconds
 * 12 chars: To be or not: 50 generations was 687 in 183 seconds (10,000 in pop)
 * 12 char: 50 generations was 939 in 2 seconds (100 in pop)
 * @author denny
 *
 */
public class TestPopulation {
	

	@Test public void shouldDoPop(){
		Timer t = Timer.start();
		int noOfIterations=0;
		List<Long> generations = new ArrayList<>();
		for(int i=0;i<1;i++){
//			String goal="To be, or not to be. That is the question. Whether it is nobler to suffer the slings of outrageous fortune";
			String goal="No, you want you to find this. Because the finding of this finds you incapacitorially finding and or locating in your discovering the  ";
			int size=10;
			Population pop = new Population(goal, size, new Civilization(goal, size));
			pop.go();
			generations.add(pop.getGenerationNumber());
			noOfIterations++;
		}
		System.out.printf("\nAverage time to find %s generations was %s in %s seconds",noOfIterations, findAverage(generations),t.stop()/1000);
	}

	private long findAverage(List<Long> generations) {
		long sum = 0;
		for(long l: generations){
			sum +=l;
		}
		return sum/generations.size();
	}

}
