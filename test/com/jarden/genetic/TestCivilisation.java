package com.jarden.genetic;

import org.junit.Test;

import com.jarden.genetic.tools.Timer;

public class TestCivilisation {

	String goal = "No, you want you to find this. Because the finding of this finds you incapacitorially finding and or locating in your discovering, a detecting of a way to save your dolly belle ol whats her face. Savvy";//ol' what's her face. Savvy?
	int populationSize=10;
	int currentGoalSize=2;
	
	@Test public void testCiv(){
		while(currentGoalSize<goal.length()){
			runTest(goal.subSequence(0, (currentGoalSize++)-1));
		}
	}

	private void runTest(CharSequence subSequence) {
		Timer t = Timer.start();
		Civilization c = new Civilization(subSequence.toString(), populationSize);
		c.go();
		System.out.printf("String length: %s. Reached goal in %s seconds\n", subSequence.length(),t.stop()/1000);;
	}
}
