package com.jarden.genetic;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Random;

import org.junit.Test;

public class TestChromasome {
	
	Random random = new Random(System.currentTimeMillis());
	Chromasome c1 = new Chromasome("abcdefghijkl");
	Chromasome c2 = new Chromasome("wsldkfjwofji");
	private String model = "Hello World!";

	@Test
	public void shouldReturnRandom12CharString() {
		Chromasome newChrom = c1.createRandom(12);
		assertEquals(12, newChrom.getCode().length());
	}
	
	@Test public void shouldProduceNew12CharStrings(){
		Collection<Chromasome> kids = c1.mate(c2);
		for(Chromasome kid: kids){
			assertEquals(12, kid.getCode().length());
		}
	}
	
	@Test public void shouldMutateOneCodepoint(){
		int cost =c1.calculateCost(model);
		System.out.println(c1);
		c1.mutate(1.0);
		System.out.println(c1);
		assertTrue(cost-c1.calculateCost(model)!=0);
	}

}
