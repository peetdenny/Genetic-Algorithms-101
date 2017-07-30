package com.jarden.genetic.tools;


public class Timer {

		
	private long start;

	private Timer(){
		start = System.currentTimeMillis();
	}
	
	public static Timer start() {
		return new Timer();
	}

	public long stop() {
		return System.currentTimeMillis() - start;
	}
	

}
