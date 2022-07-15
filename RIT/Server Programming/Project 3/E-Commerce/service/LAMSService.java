package service;

import java.util.*;
import java.lang.*;
import javax.servlet.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.ws.*;
import javax.xml.*;
import javax.jws.*;
import javax.ejb.*;
import components.data.*;
import business.*;

@Stateless
@Path("/Services")
public class LAMSService
{
   // Attributes
   IComponentsData db;
   BusinessLayer business;
   
   @Context
   UriInfo context;
   
   /**
   * Intitalize the database
   * @return message String
   */
   @GET
   @Produces("application/xml")
   public String getInfo()
   {
      IComponentsData db = new DB();		  
      db.initialLoad("LAMS");
      business = new BusinessLayer(db);
      return business.getStartTag() + business.getBeginTag() + "<intro>Welcome to the LAMS Appointment Service</intro><wadl>" + this.context.getBaseUri().toString() + "application.wadl</wadl>" + business.getEndTag();
   } // End getInfo method
   
   /**
   * Gets all appointment and return the list in xml format.
   * @return xmlString - The appointment infomation based on specific Appointment ID in XML format
   */
   @GET
   @Path("/Appointments")
   @Produces("application/xml")
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
         Appointment appoint = (Appointment)obj;
         business.setAppointment(appoint);
         business.setUri("<uri>" + this.context.getBaseUri().toString() + "Services/Appointments/" + appoint.getId() + "</uri>");
         xmlString += business.getXML();
      }
      xmlString += business.getEndTag();
      return xmlString;
   } // End getAllAppointments method
   
   /**
   * Gets an specfic appointment and return the list in xml format
   * @param appointment - Appointment ID
   * @return xmlString - The appointment infomation based on specific Appointment ID in XML format
   */
   @GET
   @Path("/Appointments/{appointment}")
   @Produces("application/xml")
   public String getAppointment(@PathParam("appointment") String appointment)
   {
      if(db == null)
      {
         db = new DB();
      }
      String xmlString = business.getStartTag() + business.getBeginTag();
      String appointID = "id='" + appointment + "'";
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
         business.setUri("<uri>" + this.context.getBaseUri().toString() + "Services/Appointments/" + appoint.getId() + "</uri>");
         xmlString += business.getXML();
         xmlString += business.getEndTag();
      }
      return xmlString;
   } // End getAppointment method
   
   /**
   * Create an new appointment in XML and receiving XML or error message
   * @param xmlStyle - String in XML format
   * @return xmlString - The appointment infomation that was added in XML format
   */
   @PUT
   @Path("/Appointments")
   @Consumes({"text/plain"})
   @Produces("application/xml")
   public String addAppointment(String xmlStyle) throws Exception
   {
      business.setUri("<uri>" + this.context.getBaseUri().toString() + "Services/Appointments/");
      return business.addAppointment(xmlStyle);
   } // End addAppointment method
} // End LAMSService class