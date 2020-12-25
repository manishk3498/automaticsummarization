package com.git.tf.idf.calculation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author hgarg
 *
 */
public class DocumentScoreCalculationUtil {

	/**
	 * This function takes the list of sentences as input and calculate the TF IDF avergare score for each sentence
	 * This calculates the TF-IDF Score for each word in the sentence and take average of those scores.
	 * This scores gives us the indication of importance of every sentence in the document.
	 * @param document
	 * @return
	 */
	public static List<Double> calculateTfIdfScoresForDocument(List<List<String>> document){
		
		List<Double> sentenceTfIdfAverageScores = new ArrayList<Double>();
		TfIdfCalculator tfIdfSimilarityCalculationObj = new TfIdfCalculator();
		
		//Calculate the tf idf scores for each word in the first sentence and take an average of them
		
		for (List<String> sentence : document) {
			
			//Iterate over all the words/tokens of first sentence
			double sentenceTfIdfScore = 0.0;
			for (String word : sentence) {
				
				double wordTfIdfScore = tfIdfSimilarityCalculationObj.tfIdf(sentence, document, word);
				sentenceTfIdfScore += wordTfIdfScore;
				
			}
			//Take the average of all the word score (Normalizing the tf idf scores)
			sentenceTfIdfScore = sentenceTfIdfScore/(1.0*sentence.size());
			sentenceTfIdfAverageScores.add(sentenceTfIdfScore);
		}
		return sentenceTfIdfAverageScores;
	}
	/**
	 * This functions constructs the ARFF file in the standard format after calculating the 
	 * TF-IDF score of each sentence in the document
	 * @param sentenceTfIdfAverageScores
	 * @throws IOException
	 */
	public static void constructDocumentScoreFile(List<Double> sentenceTfIdfAverageScores) throws IOException{
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("scores.txt"));
		if(sentenceTfIdfAverageScores != null && sentenceTfIdfAverageScores.size() > 0){
			bw.write("@relation sentenceScores");
			bw.write("\n");
			bw.write("@attribute 'tfIdfScore' real");
			bw.write("\n");
			bw.write("@data");
			bw.write("\n");
			for (Double sentenceTfIdfScore : sentenceTfIdfAverageScores) {
				bw.write(String.valueOf(sentenceTfIdfScore));
				bw.write("\n");
			}
		}
		
		bw.close();
	}
}
