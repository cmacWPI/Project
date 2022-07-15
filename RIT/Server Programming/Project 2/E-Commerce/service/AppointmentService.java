package service;

import javax.jws.*;
import business.*;

@WebService(serviceName = "AppointmentService")
public class AppointmentService
{
   LAMSService service = new LAMSService();
   BusinessLayer business = new BusinessLayer();
    
   /**
   * Intitalize the database
   * @return message String
   */
   @WebMethod(operationName = "Initialize")
   public String initialize()
   {
      return service.initialize();
   } // End initialize method

   /**
   * Gets all appointment and return the list in xml format.
   * @return xmlString - The appointment infomation based on specific Appointment ID in XML format
   */
   @WebMethod(operationName = "GetAllAppoinments")
   public String getAllAppointments()
   {
      return service.getAllAppointments();
   } // End getAllAppointments method

   /**
   * Gets an specfic appointment and return the list in xml format
   * @param appointNumber - Appointment ID
   * @return xmlString - The appointment infomation based on specific Appointment ID in XML format
   */
    @WebMethod(operationName = "GetAppoinment")
    public String getAppointment(String appointNumber)
    {
      return service.getAppointment(appointNumber);
    } // End getAppointment method

   /**
   * Create an new appointment in XML and receiving XML or error message
   * @param xmlStyle - String in XML format
   * @return xmlString - The appointment infomation that was added in XML format
   */
   @WebMethod(operationName = "AddAppointment")
   public String addAppointment(String xmlStyle) throws Exception
   {
      return business.addAppointment(xmlStyle);
   } // End addAppointment method
}