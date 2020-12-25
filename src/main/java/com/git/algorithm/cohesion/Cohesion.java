package com.git.algorithm.cohesion;

import java.io.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.*;

public class Cohesion {
 
	public static void main(String[] args) throws Exception{
    	
    	//Take the sentences from input file
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		ArrayList<String> sentence;
		HashMap<String,Integer> hmap = new HashMap<String,Integer>();
		HashMap<String,Integer> hmap1 ;
		String line;
		int commonWords=0;
		while((line=br.readLine()) != null){
			sentence = new ArrayList<String>();
			String words[] = line.split(" ");
			for(int i=0;i<words.length;i++){
				if(hmap.containsKey(words[i]) == false)
					hmap.put(words[i],1);
				sentence.add(words[i]);
			}
			alist.add(sentence);	
		}
		double simMatrix[][] = new double[alist.size()][alist.size()];

		for(int i=0;i<alist.size();i++){
			for(int j=0;j<alist.size();j++){
				commonWords=0;
				hmap1 = new HashMap<String,Integer>();
				for(int k=0;k<alist.get(i).size();k++){
					hmap1.put(alist.get(i).get(k),1);
				}

				for(int k=0;k<alist.get(j).size();k++){
					if(hmap1.containsKey(alist.get(j).get(k)) == true)
						commonWords++;
				}

				simMatrix[i][j] = ((double)(commonWords*1.0)/(double)((alist.get(i).size()+alist.get(j).size())*1.0));
			}
		}
		double jacSum=0.0;
		double rowSum=0.0;
		for(int i=1;i<alist.size();i++){
			rowSum += simMatrix[i-1][i];
		}
		jacSum = (rowSum/((alist.size()-1)*1.0));
		System.out.println("Cohesion "+jacSum);
    }

}
