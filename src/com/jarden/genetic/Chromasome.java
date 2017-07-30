package com.jarden.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chromasome implements Comparable<Chromasome> {

	private String code;
	private int cost;
	private static Random random = CoreRandom.getRandom();
	private static String chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ., ";
	
	public Chromasome(String candidate){
		this.code = candidate;
	}
	
	public void mutate(double chance){
		if(random.nextDouble() < chance){
			int index = random.nextInt(code.length());
			boolean up = random.nextBoolean();
			byte[] bytes = code.getBytes();
			if(up){
				bytes[index]++;
			}else{
				bytes[index]--;
			}
			code = new String(bytes);
		}
	}
	
	public String toString(){
		return code;
	}

	public int calculateCost(String model){
		int score=0;
		for(int i=0;i<code.length();i++){
			int absScore = model.charAt(i)-code.charAt(i);
			score += absScore*absScore;
		}
		this.cost=score;
		return cost;
	}
	
	public List<Chromasome> mate(Chromasome partner){
		List<Chromasome> children = new ArrayList<>();
		int centre = this.code.length()-1;
		String child1Code = this.getCode().substring(0,centre)+partner.getCode().substring(centre);
		String child2Code = partner.getCode().substring(0,centre)+this.getCode().substring(centre);
		children.add(new Chromasome(child1Code));
		children.add(new Chromasome(child2Code));
		
		return children;
	}
	
	
	public static Chromasome createRandom(int length){
		char[] b = new char[length];
		for(int i=0;i<length;i++){
			b[i] = chars.charAt(random.nextInt(chars.length()));
		}
		return new Chromasome(new String(b));
	}
	
	public String getCode() {
		return code;
	}

	public void setCandidate(String candidate) {
		this.code = candidate;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public int compareTo(Chromasome o) {
		return this.cost-o.getCost();
	}


	
}
