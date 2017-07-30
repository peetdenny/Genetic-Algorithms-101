package com.jarden.genetic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

public class Civilization {

	private String goal;
	private int size;
	private Map<Population, ArrayBlockingQueue<List<Chromasome>>> migrants = new HashMap<>();

	public Civilization(String goal, int populationSize) {
		this.goal = goal;
		this.size = populationSize;
	}

	public void go() {
		int cores = Runtime.getRuntime().availableProcessors();
//		System.out.printf("Using %s cores", cores);
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < cores; i++) {
			Population population = new Population(goal, size, this);
			migrants.put(population, new ArrayBlockingQueue<List<Chromasome>>(
					20));
			PopulationRunner r = new PopulationRunner(population, i);
			Thread t = new Thread(r);
			t.setDaemon(false);
			t.start();
			threads.add(t);
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void emigrate(Population sourcePopulation, List<Chromasome> kids) {
		for (Population destPop : migrants.keySet()) {
			if (destPop.equals(sourcePopulation)) {
				continue;
			}
			ArrayBlockingQueue<List<Chromasome>> immigrationQueue = migrants
					.get(destPop);
			synchronized (immigrationQueue) {
				immigrationQueue.offer(kids);
			}
		}
	}

	public Collection<Chromasome> getImmigrants(Population destPop) {
		ArrayBlockingQueue<List<Chromasome>> immigrationQueue = migrants
				.get(destPop);
		Collection<Chromasome> immigrants = new ArrayList<>();
		synchronized (immigrationQueue) {
			List<Chromasome> kids = immigrationQueue.poll();
			while (kids != null) {
				immigrants.addAll(kids);
				kids = immigrationQueue.poll();
			}
		}
		return immigrants;
	}

	public void complete() {
		for(Population pop: migrants.keySet()){
			pop.setFound();
		}
	}

}
