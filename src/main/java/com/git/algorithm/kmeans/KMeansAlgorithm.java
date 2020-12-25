package com.git.algorithm.kmeans;

import java.io.BufferedReader;
import java.io.FileReader;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

/**
 * This class take score file as input which contains the score for every sentence and numberOfClusters.
 * This class uses Weka SimpleKMeans Utility to apply k means algorithm on the input file to form the clusters.
 * It assigns every sentence to one of the clusters until optima is reached as per k means algorithm.
 * Finally the information is printed as to which cluster the sentence is assigned.
 * @author hgarg
 *
 */
public class KMeansAlgorithm {

	public static int[] applyKMeansAlgorithm(String scoresFileName, int numberOfClusters) throws Exception{

		SimpleKMeans obj = new SimpleKMeans();
		obj.setPreserveInstancesOrder(true);
		obj.setNumClusters(numberOfClusters);
		
		BufferedReader datafile = new BufferedReader(new FileReader(scoresFileName));
		Instances instances = new Instances(datafile);
		
		obj.buildClusterer(instances);
		
		int assignments[] = obj.getAssignments();
		/*
		System.out.println("Printing Assignments of Sentences to Different Clusters");
		if(assignments != null && assignments.length > 0){
			for (int i : assignments) {
				System.out.println(i);
			}
		}
		System.out.println("Completed Printing Assignments of Sentences to Different Clusters");
		*/
		return assignments;
	}
	
}
