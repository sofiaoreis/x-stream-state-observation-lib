package com.xstream.observation;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertThat;
import static org.xmlunit.matchers.CompareMatcher.*;
import org.xmlunit.diff.ElementSelectors;
import org.xmlunit.diff.DefaultNodeMatcher;

import java.util.HashMap;


public class StateObservation 
{	
	public static HashMap<String, Integer> executions = new HashMap<String, Integer>();
	
	public static void main(String[] args) {

	        if (args.length < 1) {
	            System.err.println("Please provide an input!");
	            System.exit(0);
	        }
			
	        retrieveObject(args[0], args[1]);
	}
	
	public static void retrieveObject(Object o, String path){

		int exec_i = 0;

		File directory = new File("oracles/");
		if(!directory.exists()){
		   directory.mkdir();
		}

		if (executions.containsKey(path)){
			int i = executions.get(path);
			exec_i = i + 1;
			executions.put(path, exec_i);
		}
		else{
			executions.put(path, 1);
			exec_i = 1;
		}
				
	    XStream xstream = new XStream(new StaxDriver()); 
		XStream.setupDefaultSecurity(xstream);
		String xml = xstream.toXML(o); 
		
		formatXml(xml, "oracles/"+exec_i+'#'+path);
	}
	
	public static int getObservationsNumber(String path, int slicing)
	{		
		File folder = new File(path);
		String[] fileNames = folder.list();
		int total_slicer = 0; int total = 0;
		for(int i = 0; i< fileNames.length; i++)
		{
		  if(fileNames[i].contains("slicer.xml")){
		      total_slicer++;
		     }   
   		  if(!fileNames[i].contains("slicer.xml")){
   		      total++;
   		     }
		}
		
		if(slicing == 1)
			return total_slicer;
		else
			return total;
	
	}
	
	public static void assertObjectEquals(String buggy, String exp){
		
		assertThat(buggy, isSimilarTo(exp).withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText)));
			
	}

	public static String fromXMLToObject(String path){
		
		try{
			String contents = new String(Files.readAllBytes(Paths.get(path)));			
			return contents;

		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
			
	public static void formatXml(String xml, String path) {
   
		File file = new File(path);
		String s = null;
	  
		try {
			file.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	  
		try {
			Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
		  
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
         
			Source xmlSource = new SAXSource(new InputSource(
				new ByteArrayInputStream(xml.getBytes())));
			StreamResult res =  new StreamResult(new ByteArrayOutputStream());            
         
			serializer.transform(xmlSource, res);
		   
			s = new String(((ByteArrayOutputStream)res.getOutputStream()).toByteArray());
		  
			try {
				
				FileWriter writer = new FileWriter(file);	 
				if (s == null)
					writer.write("Something is wrong!");
				else writer.write(s);
					writer.close();
		   
			}catch(IOException e) {
				e.printStackTrace();
			}
		           
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
