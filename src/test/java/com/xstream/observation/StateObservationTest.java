package com.xstream.observation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Unit test for simple App.
 */
public class StateObservationTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StateObservationTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(StateObservationTest.class);
    }


    public void testStateObservation()
    {
        Student student = getStudentDetails();
        StateObservation.retrieveObject(student, "studentObservationTest1.xml");
        Student student2 = getStudentDetails();
        StateObservation.retrieveObject(student2, "studentObservationTest2.xml");
		StateObservation.retrieveObject(student2, "studentObservationTest2.xml");
		StateObservation.retrieveObject(student2, "studentObservationTest2.xml");
        StateObservation.retrieveObject(student, "studentObservationTest1.xml");
		
		
		// System.out.println(StateObservation.getObservationsNumber("oracles",1));
		//
		// assertEquals(StateObservation.getObservationsNumber("oracles",1), StateObservation.getObservationsNumber("oracles",0));
		//
		// String buggy = StateObservation.fromXMLToObject("oracles/1#studentObservationTest1-slicer.xml");
		// String exp = StateObservation.fromXMLToObject("oracles/1#studentObservationTest1.xml");
		//
		// StateObservation.assertObjectEquals(buggy, exp);
    }
	
    private Student getStudentDetails() {
   
       Student student = new Student();
       student.setFirstName("Mahesh");
       student.setLastName("Parashar");
       student.setRollNo(1);
       student.setClassName("1st");

       Address address = new Address();
       address.setArea("H.No. 16/3, Preet Vihar.");
       address.setCity("Delhi");
       address.setState("Delhi");
       address.setCountry("India");
       address.setPincode(110012);

       student.setAddress(address);
       return student;
    }
    private Student getStudentDetails2() {
   
       Student student = new Student();
       student.setFirstName("Sofia");
       student.setLastName("Reis");
       student.setRollNo(1);
       student.setClassName("1st");

       Address address = new Address();
       address.setArea("H.No. 16/3, Preet Vihar.");
       address.setCity("Delhi");
       address.setState("Delhi");
       address.setCountry("India");
       address.setPincode(110012);

       student.setAddress(address);
       return student;
    }
}

class Student {
   private int rollNo;
   private String firstName;
   private String lastName;
   private String className;
   private Address address;

   public String getFirstName() {
      return firstName;
   }
   
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   
   public String getLastName() {
      return lastName;
   }
   
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   
   public int getRollNo() {
      return rollNo;
   }
   
   public void setRollNo(int rollNo) {
      this.rollNo = rollNo;
   }
   
   public String getClassName() {
      return className;
   }
   
   public void setClassName(String className) {
      this.className = className;
   }
   
   public Address getAddress() {
      return address;
   }
   
   public void setAddress(Address address) {
      this.address = address;
   }
   
   public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      
      stringBuilder.append("Student [ ");
      stringBuilder.append("\nfirstName: ");
      stringBuilder.append(firstName);
      stringBuilder.append("\nlastName: ");
      stringBuilder.append(lastName);
      stringBuilder.append("\nrollNo: ");
      stringBuilder.append(rollNo);
      stringBuilder.append("\nclassName: ");
      stringBuilder.append(className);
      stringBuilder.append("\naddress: ");
      stringBuilder.append(address);
      stringBuilder.append(" ]");
      
      return stringBuilder.toString();
   }
}

class Address {
   private String area;
   private String city;
   private String state;
   private String country;
   private int pincode;

   public String getArea() {
      return area;
   }

   public void setArea(String area) {
      this.area = area;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getState() {
      return state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public int getPincode() {
      return pincode;
   }

   public void setPincode(int pincode) {
      this.pincode = pincode;
   }

   public String toString() {

      StringBuilder stringBuilder = new StringBuilder();

      stringBuilder.append("\nAddress [ ");
      stringBuilder.append("\narea: ");
      stringBuilder.append(area);
      stringBuilder.append("\ncity: ");
      stringBuilder.append(city);
      stringBuilder.append("\nstate: ");
      stringBuilder.append(state);
      stringBuilder.append("\ncountry: ");
      stringBuilder.append(country);
      stringBuilder.append("\npincode: ");	
      stringBuilder.append(pincode);
      stringBuilder.append(" ]");

      return stringBuilder.toString();
   }
}
