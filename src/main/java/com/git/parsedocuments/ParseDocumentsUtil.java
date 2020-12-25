package com.git.parsedocuments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * This is an utility to parse the sentences in the documents and keep them in memory (in arraylist data structure)
 * @author hgarg
 *
 */
public class ParseDocumentsUtil {

	/**
	 * This function takes the inputFileName as the input which contains the original sentences
	 * present in the original document. For example : input.txt
	 * 
	 * This function gives the list of tokens for every sentence present in the input document.
	 * 
	 * Example::
	 * =======================Sample Original Document===============================
	 * My name is Harshit Garg.
	 * I am doing BTech in Computer Science and Engineering.
	 * ===========================================================
	 * List<List<String>>
	 * There will be two list of strings each containing the tokens of every sentence
	 * 
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 */
	public static List<List<String>> parseSentencesFromDocument(String inputFileName) throws IOException{
		
		List<String> sentences = new ArrayList<String>();
		List<List<String>> tokensList = new ArrayList<List<String>>();
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader(inputFileName));
		
		while((line=br.readLine()) != null){
			
			//Parse the tokens (words) from the sentence and add them in the tokenlist 
			String tokensArr[] = line.split(" ");
			if(tokensArr != null && tokensArr.length > 0){
				//Constructing a sentence from array of tokens
				List<String> tokenList = new ArrayList<String>();
				for (String token : tokensArr) {
					tokenList.add(token);
				}
				tokensList.add(tokenList);
			}
			//Add the full sentence in the sentences list
			sentences.add(line);
		}
		
		br.close();
		return tokensList;
	}
	
	public static void writeSummaryToFile(List<List<String>> document,int[] assignments,int clusterNumber) throws IOException{
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("summary.txt"));
		
		if(assignments != null && assignments.length > 0){
			for(int i=0;i<assignments.length;i++){
				if(assignments[i] == clusterNumber){
					List<String> sentence = document.get(i);
					for (String token : sentence) {
						bw.write(token);
						bw.write(" ");
					}
					bw.write("\n");
				}
			}
		}
		
		bw.close();
	}
	
}
