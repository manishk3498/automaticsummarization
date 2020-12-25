package com.git.parsedocuments;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

	
	public static void main(String[] args) {
		try {
			parseCourtCases("06_6.xml", null);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method reads/parses the XML Document and removes the special characters and stores the sentences in arraylist.
	 * Then it writes all the sentences in txt file ...input.txt 
	 * @param courtCaseFileName
	 * @param fileAbsolutePath TODO
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static void parseCourtCases(String courtCaseFileName, File fileAbsolutePath) throws SAXException, IOException, ParserConfigurationException{
		
		List<List<String>> sentenceList = new ArrayList<List<String>>();
		
		File file = new File(courtCaseFileName);
		if(fileAbsolutePath != null) {
			file = fileAbsolutePath;
		}
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		//document.getDocumentElement().normalize();
        
		NodeList nodeList = document.getElementsByTagName("sentence");
		
		int size = nodeList.getLength();
		
		if(nodeList != null && nodeList.getLength() > 0){
			for(int i=0;i<nodeList.getLength();i++){
				
				Node node = nodeList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element element = (Element) node;
					String str = element.getTextContent().replaceAll("[^a-zA-Z0-9 ]", "");
					if(!str.isEmpty()){
						String[] tokens = str.split(" ");
						List<String> sentence = new ArrayList<String>();
						
						if(tokens != null && tokens.length > 0){
							for (String token : tokens) {
								if(!token.equalsIgnoreCase("")){
									sentence.add(token);
								}
							}
						}
						sentenceList.add(sentence);
					//	System.out.println(element.getTextContent());
					}
				}
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("input.txt"));
		
		for (List<String> sentence : sentenceList) {
			for (String token : sentence) {
				bw.write(token);
				bw.write(" ");
			}
			bw.write("\n");
		}
		bw.close();
		
	}
}
