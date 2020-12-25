package com.git.tf.idf.calculation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 
 * @author hgarg
 *
 */
public class TestTfIdfCalculator {
	
	private static TfIdfCalculator tfIdfCalculator = new TfIdfCalculator();
	
	/**
	 * 
	 */
	@Test
	public void testTermFrequency(){
		
		List<String> sentence = Arrays.asList("My","name","is","Harshit","Garg");
		
		String term = "name";
		
		double tfScore = tfIdfCalculator.tf(sentence, term);
		
		Assert.assertEquals(tfScore, 0.2);
		
	}
	/**
	 * 
	 */
	@Test
	public void testInverseDocumentFrequency(){
		
		List<List<String>> document = new ArrayList<List<String>>();
		List<String> sentence1 = Arrays.asList("My","name","is","Harshit","Garg");
		List<String> sentence2 = Arrays.asList("My","name","is","Manish","Garg");
		
		document.add(sentence1);
		document.add(sentence2);
		
		double idfScore = tfIdfCalculator.idf(document, "name");
	
		Assert.assertEquals(idfScore, 0.0);
		
	}
	/**
	 * 
	 */
	@Test
	public void testTfIdf(){
		
		List<List<String>> document = new ArrayList<List<String>>();
		List<String> sentence1 = Arrays.asList("My","name","is","Harshit","Garg");
		List<String> sentence2 = Arrays.asList("My","name","is","Manish","Garg");
		
		document.add(sentence1);
		document.add(sentence2);
		
		String term = "name";
		
		double tfScore = tfIdfCalculator.tf(sentence1, term);
		
		double idfScore = tfIdfCalculator.idf(document, term);
		
		double answer = tfScore*idfScore;
	
		double tfIdf = tfIdfCalculator.tfIdf(sentence1, document, term);
		
		Assert.assertEquals(tfIdf, answer);
		
	}
	
}
