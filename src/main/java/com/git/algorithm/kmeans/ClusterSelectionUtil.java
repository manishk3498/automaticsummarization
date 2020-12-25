package com.git.algorithm.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author hgarg
 *
 */
public class ClusterSelectionUtil {

	public static Map<Integer,List<Integer>> getSentencesInCluster(int[] assignments){

		//Key - Cluster No and Value is List of Sentences
		Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
		
		if(assignments != null && assignments.length > 0){
			
			for(int i=0;i<assignments.length;i++){
				
				int clusterNumber = assignments[i];
				List<Integer> sentences = map.get(clusterNumber);
				if(sentences == null){
					sentences = new ArrayList<Integer>();
				}
				sentences.add(i);
				map.put(assignments[i], sentences);
			}
		}
		return map;
	}
	
	public static Integer getClusterHavingMaximumSentences(Map<Integer,List<Integer>> map){
		
		if(map == null){
			return null;
		}
		
		Set<Integer> keySet = map.keySet();
		int maximumSize = 0;
		int index = 0;
		if(keySet != null && keySet.size() > 0){
			for (Integer key : keySet) {
				List<Integer> value = map.get(key);
				if(value != null && value.size() > maximumSize){
					maximumSize = value.size();
					index = key;
				}
			}
		}
		return index;
	}
	
}
