package com.git.automaticsummarization;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.parser.DocumentParser;

import com.git.algorithm.kmeans.ClusterSelectionUtil;
import com.git.algorithm.kmeans.KMeansAlgorithm;
import com.git.parsedocuments.ParseDocumentsUtil;
import com.git.parsedocuments.StopWordsRemovalUtil;
import com.git.parsedocuments.XMLParser;
import com.git.stemming.StopWordsFileCreation;
import com.git.tf.idf.calculation.DocumentScoreCalculationUtil;

public class AutomaticSummarizer {
	
	public static void main(String[] args) {
		
		try {
			generateSummary(null,"06_6.xml","input.txt",10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void generateSummary(File fileAbsolutePath,String courtCaseFileName,String inputFileName, int numberOfClusters) throws Exception{
	
		
		long startTime = System.currentTimeMillis();
		System.out.println("Started parsing Legal XML Case Document");
		
		//Step 1
		XMLParser.parseCourtCases(courtCaseFileName, fileAbsolutePath);
		
		System.out.println("Completed parsing Legal XML Case Document and time taken is "+(System.currentTimeMillis()-startTime));
		
		//Step 2 Parse the input.txt and store the tokens in arraylist
		List<List<String>> document = ParseDocumentsUtil.parseSentencesFromDocument(inputFileName);
		
		StopWordsFileCreation stopWordsFileCreation = new StopWordsFileCreation();
		stopWordsFileCreation.createStopWordsFile();
		
		//Step 3 remove the stop words from the document
		document = StopWordsRemovalUtil.removeStopWords(document);
		
		//Step 4 Generate the score of each sentence to identify whether sentence is important or not
		List<Double> sentenceScores = DocumentScoreCalculationUtil.calculateTfIdfScoresForDocument(document);
		
		//Step 5 Write the scores in the file (ARFF file - scores.txt)
		//This scores.txt file will be used as an input to K Means algorithm
		DocumentScoreCalculationUtil.constructDocumentScoreFile(sentenceScores);
		
		//This array tells us which sentence is assigned to which cluster
		startTime = System.currentTimeMillis();
		System.out.println("Started applying K Means Algorithm");
		
		//Step 6
		int[] assignments = KMeansAlgorithm.applyKMeansAlgorithm("scores.txt",numberOfClusters);
		
		System.out.println("Completed applying K Means Algorithm and time taken is "+(System.currentTimeMillis()-startTime));
		
		//Step 7 Key - CusterNumber, Value - List of sentences
		Map<Integer,List<Integer>> map = ClusterSelectionUtil.getSentencesInCluster(assignments);
		
		//Finding the cluster number which is having maximum sentences
		int clusterNumber = ClusterSelectionUtil.getClusterHavingMaximumSentences(map);

		//Step 8 
		//Writing the summary to summary.txt
		ParseDocumentsUtil.writeSummaryToFile(document, assignments, clusterNumber);
	
	}
	
}
