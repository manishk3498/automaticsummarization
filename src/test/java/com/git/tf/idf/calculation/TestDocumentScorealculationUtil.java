package com.git.tf.idf.calculation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class TestDocumentScorealculationUtil {

	
	
	@Test
	public void testFileWrite(){
		
		List<Double> sentenceScores = new ArrayList<Double>();
		sentenceScores.add(new Double(0.2));
		sentenceScores.add(new Double(0.3));
		sentenceScores.add(new Double(0.4));
		
		try {
			DocumentScoreCalculationUtil.constructDocumentScoreFile(sentenceScores);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
