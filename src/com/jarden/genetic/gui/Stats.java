package com.jarden.genetic.gui;

public class Stats {
	private static Stats instance;
	
	
	
	private String bestMatchSoFar;
	private long noOfGenerations;
	static{
		instance = new Stats();
	}
	
	public static Stats getStats(){
		return instance;
	}

}
