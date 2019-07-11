package com.xstream.observation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.xstream.observation.StateObservation;

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

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
      
        Student student = getStudentDetails();
      
        //Object to XML Conversion
        StateObservation.retrieveObject(student, "object_state.txt");

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
