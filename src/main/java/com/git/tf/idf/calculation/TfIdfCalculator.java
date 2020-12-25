package com.git.tf.idf.calculation;

import java.util.Arrays;
import java.util.List;

/**
 * This class calculates Term Frequency (TF) and Inverse Document Frequency (IDF) for the 
 * word in a document.
 * 
 * This is one of the important and most popular weighing schemes used in information retrieval 
 * 83% of the text recommender systems use tf-idf as the score for building their models.
 * 
 * @author hgarg
 *
 */
public class TfIdfCalculator {
	
	public static void main(String[] args) {
		
		 List<String> doc1 = Arrays.asList("Lorem", "ipsum", "dolor", "ipsum", "sit", "ipsum");
	        List<String> doc2 = Arrays.asList("Vituperata", "incorrupte", "at", "ipsum", "pro", "quo");
	        List<String> doc3 = Arrays.asList("Has", "persius", "disputationi", "id", "simul");
	        List<List<String>> documents = Arrays.asList(doc1, doc2, doc3);

	        TfIdfCalculator calculator = new TfIdfCalculator();
	        double tfidf = calculator.tfIdf(doc1, documents, "ipsum");
	        System.out.println("TF-IDF (ipsum) = " + tfidf);
		 
		
	}
	/**
	 * This function calculates the term frequency of the word in the document
	 * @param sentence
	 * @param term
	 * @return
	 */
	public double tf(List<String> sentence, String term) {
       double result = 0;
       for (String word : sentence) {
           if (term.equalsIgnoreCase(word))
               result++;
       }
       return result / sentence.size();
	}
	/**
	 * This function calculates the Inverse Document Frequency (IDF) of a word in a document.
	 * IDF says that if a word is present in more sentences or lets say all the sentences in a document
	 * then that word is not so important.
	 * 
	 * If a word is present in all the sentences in a document then 0.0 score is assigned to that word.
	 * 
	 * @param docs
	 * @param term
	 * @return
	 */
	public double idf(List<List<String>> docs, String term) {
       double n = 0;
       for (List<String> doc : docs) {
           for (String word : doc) {
               if (term.equalsIgnoreCase(word)) {
                   n++;
                   break;
               }
           }
       }
       return Math.log(docs.size() / n);
   }
	/**
	 * This calculates the cumulative TF-IDF score for a word by multiplying the TF and IDF score 
	 * for the words
	 * @param doc
	 * @param docs
	 * @param term
	 * @return
	 */
	 public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
	        return tf(doc, term) * idf(docs, term);

	 }
	
}
