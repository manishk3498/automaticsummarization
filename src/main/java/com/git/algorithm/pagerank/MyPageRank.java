package com.git.algorithm.pagerank;

//Input keywords.txt containing keywords in args[1]
//Processed file in args[0]
//args[2] - original file
import java.util.*;
import java.io.*;
import java.lang.*;
class sentence implements Comparable{
	int id;
	double rank;
	String str;
	public int compareTo(Object obj){
		
		sentence st = (sentence)obj;
		if(this.rank > st.rank)
			return -1;
		else if(this.rank < st.rank)
			return 1;
		else
			return 0;
	}
}
public class MyPageRank{

	public static double d=0.85;
	public static int iter=10;
	public static double simMatrix[][];
	public static ArrayList<ArrayList<String>> alist;
	public static double ranks[];
	public static void pageRank(){
		
		double wji=0.0,pagerank=0.0,othersum=0.0,wjk=0.0,sum=0.0;
		ranks = new double[alist.size()];
		for(int i=0;i<alist.size();i++){
			
			ranks[i]=1.0;
		}
		for(int cnt=0;cnt<iter;cnt++){
			for(int i=0;i<alist.size();i++){
			sum=0.0;
				for(int j=0;j<alist.size();j++){
					
					if (j == i)
						continue;
					wji = simMatrix[j][i];
					pagerank = ranks[j];
					othersum=0.0;
					
					for(int k=0;k<alist.size();k++){
					
						wjk = simMatrix[j][k];
						othersum += wjk;
					}
				/*	if(othersum == 0.0)
						System.out.println("OtherSum is 0");
					else 
						System.out.println("OtherSumis not 0");*/
					if(othersum == 0.0 && wji == 0.0)
						sum = 0.0;
					else
						sum += (wji*pagerank/othersum);
					
					
				}
				ranks[i] = (1- d) + d*sum;		
			}
		}
	}
		
	public static void printRanks(String original) throws Exception{
		double check=0.0;
		ArrayList<sentence> sentences = new ArrayList<sentence>();
		for(int i=0;i<alist.size();i++){
			
			sentence tempp = new sentence();
			tempp.id=i;
			tempp.rank = ranks[i];
			sentences.add(tempp);
		}
		BufferedReader br = new BufferedReader(new FileReader(original));
		String temppp;
		int i=0;
		while((temppp=br.readLine()) != null){
		
			sentences.get(i).str = temppp;
			i++;
		}
		br.close();
		Collections.sort(sentences);
		for(i=0;i<sentences.size();i++){
			
			System.out.println(sentences.get(i).rank);
		}
	
		/*System.out.println("Summary Generation");
		for(i=0;i<25;i++){
			
			System.out.println(sentences.get(i).str);
		}
	*/
	}
	public static void main(String args[]) throws Exception{
		
		//BufferedReader br = new BufferedReader(new FileReader(args[0]));
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		BufferedReader keybr;
		String temp;
		alist = new ArrayList<ArrayList<String>>();
		
		while((temp=br.readLine()) != null){
			
			ArrayList<String> sen = new ArrayList<String>();
			String words[] = temp.split(" ");
			for(int i=0;i<words.length;i++){
				sen.add(words[i]);
			}
			alist.add(sen);
		}
		//keybr = new BufferedReader(new FileReader(args[1]));
		keybr = new BufferedReader(new FileReader("stopwords.txt"));
		HashMap<String,Integer> keyWords = new HashMap<String,Integer>();
		String word;
			
			while((word=keybr.readLine()) != null){
				
				keyWords.put(word,1);
				//System.out.println("Keyword "+word);
			}
			
			
		simMatrix = new double[alist.size()][alist.size()];
		double common=0.0;
		for(int i=0;i<alist.size();i++){
			for(int j=0;j<alist.size();j++){
			common=0.0;	
			HashMap<String,Integer> hmap = new HashMap<String,Integer>();
			
			
				for(int k=0;k<alist.get(i).size();k++){
					hmap.put(alist.get(i).get(k),1);
				}
				for(int k=0;k<alist.get(j).size();k++){
					if(hmap.containsKey(alist.get(j).get(k)) == true && keyWords.containsKey(alist.get(j).get(k)) == true)
						common++;
						
				}
				
			//	System.out.println(alist.get(i).size()+" Size "+alist.get(j).size());
				simMatrix[i][j] = (common/(double)((alist.get(i).size()+alist.get(j).size())*1.0));
			}
		}
		System.out.println(alist.size());
		/*for(int i=0;i<alist.size();i++){
			for(int j=0;j<alist.size();j++){
				
				System.out.print("Hello");
				System.out.print(simMatrix[i][j]+" ");
			}
			System.out.println("Next Line");
		}*/
		pageRank();
		//printRanks(args[2]);
		printRanks("ranks.txt");
		
	}
}