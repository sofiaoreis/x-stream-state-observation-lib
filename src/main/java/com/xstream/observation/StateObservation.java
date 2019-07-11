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

public class StateObservation 
{
	
	public static void main(String[] args) {

	        if (args.length < 1) {
	            System.err.println("Please provide an input!");
	            System.exit(0);
	        }
			
	        retrieveObject(args[0], args[1]);
	}
	
	public static void retrieveObject(Object o, String path){
	    XStream xstream = new XStream(new StaxDriver()); 
		String xml = xstream.toXML(o); 
		formatXml(xml, path);	
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
