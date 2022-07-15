package service;

import java.util.*;
import components.data.*;
import business.*;

public class LAMSService
{
   // Attributes
   IComponentsData db;
   BusinessLayer business;
   
   // Initialize the Database
   public String initialize() 
   {
      db = new DB();		  
      db.initialLoad("LAMS");
      business = new BusinessLayer(db);
      return "Database Initialized";
   } // End initalize method
   
   // Get all appointments
   public String getAllAppointments()
   {
      if(db == null)
      {
         db = new DB();
      }
      String xmlString = business.getStartTag() + business.getBeginTag();
      List<Object> objs = db.getData("Appointment", "");
      
      // Loop through each of appointment and append to the string in XML format.
      for(Object obj : objs)
      {
         business.setAppointment((Appointment)obj);
         xmlString += business.getXML();
      }
      xmlString += business.getEndTag();
      return xmlString;
   } // End getAllAppointments method
   
   // Get an appointment based on specific appointment ID.
   public String getAppointment(String appointNumber) 
   {
      if(db == null)
      {
         db = new DB();
      }
      String xmlString = business.getStartTag() + business.getBeginTag();
      String appointID = "id='" + appointNumber + "'";
      List<Object> objs = db.getData("Appointment", appointID);
      Object data = null;
      
      // Loop through each of appointment and append to the string in XML format.
      for(Object obj : objs)
      {
         data = obj;
      }
      // If data isn't found based on Appointment ID, prints out XML Error.
      if(data == "" || data == null)
      {
         xmlString += business.getAppointIDErrorXML();
         xmlString += business.getEndTag();
      }
      else
      {
         // Convert data to XML string
         Appointment appoint = (Appointment)data;
         business.setAppointment(appoint);
         xmlString += business.getXML();
         xmlString += business.getEndTag();
      }
      return xmlString;
   } // End getAppointment method
} // End LAMSService class