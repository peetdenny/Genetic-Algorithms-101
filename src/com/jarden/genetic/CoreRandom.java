package com.jarden.genetic;

import java.util.Random;

public class CoreRandom {
	
	static Random random;
	static{
		random = new Random(System.currentTimeMillis());
	}
	
	public static Random getRandom() {
		return random;
	}
	
	

}
