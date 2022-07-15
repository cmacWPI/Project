package service;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.ws.*;
import javax.xml.*;
import javax.jws.*;
import components.data.*;
import business.*;

@Path("/Services")
public class AppointmentService
{
   LAMSService service = new LAMSService();
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
      db = new DB();		  
      db.initialLoad("LAMS");
      business = new BusinessLayer(db);
      return business.getStartTag() + business.getBeginTag() + "<intro> Welcome to the LAMS Appointment Service</intro><wadl>" + this.context.getBaseUri().toString() + "application.wadl</wadl>" + business.getEndTag();
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
      return service.getAllAppointments();
   } // End getAllAppointments method

   /**
   * Gets an specfic appointment and return the list in xml format
   * @param appointNumber - Appointment ID
   * @return xmlString - The appointment infomation based on specific Appointment ID in XML format
   */
   @Path("/Appointments/{appointment}")
   @GET
   @Produces("application/xml")
   public String getAppointment(@PathParam("appointment") String appointNumber)
   {
      return service.getAppointment(appointNumber);
   } // End getAppointment method

   /**
   * Create an new appointment in XML and receiving XML or error message
   * @param xmlStyle - String in XML format
   * @return xmlString - The appointment infomation that was added in XML format
   */
   @Path("/Appointments")
   @PUT
   @Consumes({"text/xml","application/xml"})
   @Produces("application/xml")
   public String addAppointment(String xmlStyle) throws Exception
   {
      return business.addAppointment(xmlStyle);
   } // End addAppointment method
}