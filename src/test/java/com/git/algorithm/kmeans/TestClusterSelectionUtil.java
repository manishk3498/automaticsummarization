package com.git.algorithm.kmeans;

import java.util.List;
import java.util.Map;




import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClusterSelectionUtil {

	ClusterSelectionUtil clusterSelectionUtil = new ClusterSelectionUtil();
	
	@Test
	public void testGetSentencesInCluster(){
		
		int[] assignments = {0,1,0,2,1,0,0,2,1,2};
		Map<Integer,List<Integer>> map = clusterSelectionUtil.getSentencesInCluster(assignments);
		
		//Test number of sentences in cluster 0
		List<Integer> sentenceZeroList = map.get(0);
		Assert.assertEquals(sentenceZeroList.size(), 4);
		
		
		
		//Test number of sentences in cluster 0
		List<Integer> sentenceOneList = map.get(1);
		Assert.assertEquals(sentenceOneList.size(), 3);
				
		List<Integer> sentenceTwoList = map.get(2);
		Assert.assertEquals(sentenceTwoList.size(), 3);
		
	}
	
	@Test
	public void testGetMaximumSentencesInCluster(){
		
		int[] assignments = {0,1,0,2,1,0,0,2,1,2};
		Map<Integer,List<Integer>> map = clusterSelectionUtil.getSentencesInCluster(assignments);
		Integer clusterNumber = clusterSelectionUtil.getClusterHavingMaximumSentences(map);
		
		Assert.assertEquals(clusterNumber, new Integer(0));
		
	}
}
