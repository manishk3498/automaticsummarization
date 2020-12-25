package com.git.parsedocuments;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestParseDocumentUtil {

	
	@Test
	public void testParseDocuments(){
		
		try {
			List<List<String>> document = ParseDocumentsUtil.parseSentencesFromDocument("test.txt");
		
			Assert.assertEquals(document.get(0).get(0),"My");
			Assert.assertEquals(document.get(1).size(),8);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
