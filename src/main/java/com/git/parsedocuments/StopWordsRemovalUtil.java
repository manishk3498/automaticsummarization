package com.git.parsedocuments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class is used to remove the stop words from the original document after parsing them
 * and storing them in arraylists
 * @author hgarg
 *
 */
public class StopWordsRemovalUtil {

	/**
	 * Remove stop words from the original document which needs to be summarized
	 * @param document
	 * @throws IOException
	 */
	public static List<List<String>> removeStopWords(List<List<String>> document) throws IOException{
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();
				
		try {
			map  = loadStopWordsFromFile("stopwords.txt");
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		List<List<String>> processedDocument = new ArrayList<List<String>>();
		
		for (List<String> sentence : document) {	
			List<String> processedSentence = new ArrayList<String>();
			for (String token : sentence) {
				if(!map.containsKey(token)){
					processedSentence.add(token);
				}
			}
			if(processedSentence.size() > 0){
				processedDocument.add(processedSentence);
			}
		}
		return processedDocument;
	}
	/**
	 * This function loads the stop words from the file - stopWords.txt
	 * @param stopWordsFileName
	 * @return
	 * @throws IOException
	 */
	public static Map<String,Boolean> loadStopWordsFromFile(String stopWordsFileName) throws IOException{
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		String stopWord = null;
		BufferedReader br = new BufferedReader(new FileReader(stopWordsFileName));
		
		while((stopWord=br.readLine()) != null){
			map.put(stopWord, true);
		}
		
		br.close();
		return map;
	}
}
