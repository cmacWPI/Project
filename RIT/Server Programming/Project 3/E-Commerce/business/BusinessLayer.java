package business;

import service.*;
import java.util.*;
import components.data.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.io.*;
import java.text.*;

public class BusinessLayer
{
   // Attributes
   IComponentsData db;
   Appointment appoint;
   private DocumentBuilder builder;
   private XPath path;
   private String uri = "";
   
   // XML Tags
   private final String START_TAG = "<?xml version='1.0' encoding='UTF-8' standalone='no'?>";
   private final String BEGIN_TAG = "<AppointmentList>";
   private final String END_TAG = "</AppointmentList>";
   
   // Default Constructor - Get Database to Initialize
   public BusinessLayer()
   {
      if(db == null)
      {
         db = new DB();
      }
   } // End Default Constructor
   
   // Get Database to Initialize
   public BusinessLayer(IComponentsData db)
   {
      this.db = db; 
   } // End Constructor 
   
   public void setUri(String uri)
   {
      this.uri = uri;
   }
   
   // Set an Appoinment to get data for XML
   public void setAppointment(Appointment appoint)
   {
      this.appoint = appoint; 
   } // End setAppointment method

   // Get XML Start Tag
   public String getStartTag()
   {
      return START_TAG; 
   } // End getStartTag method
   
   // Get XML Begin Tag - AppointmentList
   public String getBeginTag()
   {
      return BEGIN_TAG; 
   } // End getBeginTag method
   
   // Get XML End Tag - AppointmentList
   public String getEndTag()
   {
      return END_TAG; 
   } // End getEndTag method
   
   // Get XML format of appointment data.
   public String getXML()
   {
      return getAppointmentXML() + getUriXML() + getPatientXML() + getPhlebXML() + getPSCXML() + getLabTestXML() + "</appointment>";
   } // End getXML method
   
   // Get XML format of uri
   public String getUriXML()
   {
      return uri;
   } // End getUriXML method
   
   // Get appointment infomation in XML format
   public String getAppointmentXML()
   {
      return "<appointment date='" + appoint.getApptdate() + "' id='" + appoint.getId() + "' time='" + appoint.getAppttime() + "'>";
   } // End getAppointmentXML method
   
   // Get patient infomation in XML format
   public String getPatientXML()
   {
      Patient patient = appoint.getPatientid();
      return "<patient id='" + patient.getId() + "'><name>" + patient.getName() + "</name><address>" + patient.getAddress() + "</address><insurance>"+ patient.getInsurance() +"</insurance><dob>" + patient.getDateofbirth() + "</dob></patient>";
   } // End getPatientXML method

   // Get phlebotomist infomation in XML format
   public String getPhlebXML()
   {
      Phlebotomist phleb = appoint.getPhlebid();
      return "<phlebotomist id='" + phleb.getId() + "'><name>" + phleb.getName() + "</name></phlebotomist>";
   } // End getPhlebXML method

   // Get PSC infomation in XML format
   public String getPSCXML()
   {
      PSC psc = appoint.getPscid();
      return "<psc id='" + psc.getId() + "'><name>" + psc.getName() + "</name></psc>";
   } // End getPSCXML method

   // Get lab test infomation in XML format
   public String getLabTestXML()
   {
      List<AppointmentLabTest> appointLT = appoint.getAppointmentLabTestCollection();
      String xmlString = "<allLabTests>";
      for(AppointmentLabTest alt: appointLT)
      {
         Diagnosis diag = alt.getDiagnosis();
         LabTest labtest = alt.getLabTest();
         xmlString += "<appointmentLabTest appointmentId='" + appoint.getId() + "' dxcode='" + diag.getCode() + "' labTestId='" + labtest.getId() + "'/>";
      }
      xmlString += "</allLabTests>";
      return xmlString;
   } // End getLabTestXML method
   
   // Get Error XML of Appointment
   public String getAppointErrorXML()
   {
      return "<error>ERROR: Appointment is not available</error>";
   } // End getAppointErrorXML method
   
   // Get Error XML of Appointment if ID is not found
   public String getAppointIDErrorXML()
   {
      return "<error>ERROR: Appointment ID is not found</error>";
   } // End getAppointIDErrorXML
   
   // Get patient object based on Patient ID
   public Patient getPatient(String id)
   {
      String patientID = "id='" + id + "'";
      List<Object> objs = db.getData("Patient", patientID);

      Object data = null;
      Object obj;
      
      Iterator patient = objs.iterator();
      while(patient.hasNext())
      {
         obj = patient.next();
         data = obj;
      }
      return (Patient)data;
   } // End getPatient method
   
   // Get phlebotomist object based on Phlebotomist ID    
   public Phlebotomist getPhleb(String id)
   {
      String phlebID = "id='" + id + "'";
      List<Object> objs = db.getData("Phlebotomist", phlebID);

      Object data = null;
      Object obj;
      
      Iterator phleb = objs.iterator();
      while(phleb.hasNext())
      {
         obj = phleb.next();
         data = obj;
      }
      return (Phlebotomist)data;
   } // End getPhleb method

   // Get PSC object based on PSC ID
   public PSC getPSC(String id)
   {
      String pscID = "id='" + id + "'";
      List<Object> objs = db.getData("PSC", pscID);

      Object data = null;
      Object obj;
      
      Iterator psc = objs.iterator();
      while(psc.hasNext())
      {
         obj = psc.next();
         data = obj;
      }
      return (PSC)data;
   } // End getPSC method
   
   // Get Physician object based on Physician ID
   public Physician getPhysician(String id)
   {
      String physicianID = "id='" + id + "'";
      List<Object> objs = db.getData("Physician", physicianID);

      Object data = null;
      Object obj;
      
      Iterator physician = objs.iterator();
      while(physician.hasNext())
      {
         obj = physician.next();
         data = obj;
      }
      return (Physician)data;
   } // End getPhysician method
   
   // Get LabTest object based on Lab Test ID
   public LabTest getLabTest(String id)
   {
      String labtestID = "id='" + id + "'";
      List<Object> objs = db.getData("LabTest", labtestID);

      Object data = null;
      Object obj;
      
      Iterator labtest = objs.iterator();
      while(labtest.hasNext())
      {
         obj = labtest.next();
         data = obj;
      }
      return (LabTest)data;
   } // End getLabTest method
   
   // Get Diagnosis object based on Diagnosis ID
   public Diagnosis getDiagnosis(String code)
   {
      String diagCode = "code='" + code + "'";
      List<Object> objs = db.getData("Diagnosis", diagCode);

      Object data = null;
      Object obj;
      
      Iterator diag = objs.iterator();
      while(diag.hasNext())
      {
         obj = diag.next();
         data = obj;
      }
      return (Diagnosis)data;
   } // End getDiagnosis method
   
   // Get current Appointment ID incremented by 10
   public String getAppointmentID()
   {
      List<Object> objs = db.getData("Appointment", "");
      int appointID = 0;
      
      // Loops through and assign highest appointment ID to variable
      for(Object obj: objs)
      {
         Appointment appoint = (Appointment)obj;
         int highID = Integer.parseInt(appoint.getId()); 
         if(highID > appointID)
         {
            appointID = highID;
         }
      }
      // Increment the current appointment ID by 10.
      appointID += 10;
      return String.valueOf(appointID);
   } // End getAppointmentID

   // Parse the XML string.
   public Document xmlParser(String xmlStyle) throws Exception 
   {
      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
      builder = dbfactory.newDocumentBuilder();
      XPathFactory xpfactory = XPathFactory.newInstance();
      path = xpfactory.newXPath();
      InputSource is = new InputSource(new StringReader(xmlStyle));
      return builder.parse(is);
   } // End xmlParser method
   
   // Check if date is valid
   public boolean isValidDate(String date)
   {
      if(date.length() == 10)
      {
         try 
         {
            DateFormat dateCheck = new SimpleDateFormat("yyyy-MM-dd");
            dateCheck.setLenient(false);
            dateCheck.parse(date);
            return true;
         }
         catch(ParseException pe) 
         {
            return false;
         }
      }
      else
      {
         return false;
      }
   } // End isValidDate method

   // Check if time is valid
   public boolean isValidTime(String time)
   {
      try 
      {
         DateFormat timeCheck = new SimpleDateFormat("HH:mm:ss");
         timeCheck.setLenient(false);
         timeCheck.parse(time);
         return true;
      }
      catch(ParseException pe) 
      {
         return false;
      } 
   } // End isValidTime method
   
   // Check if appointment time is valid and does not conflict with other appointments
   public boolean isValidAppointDateTime(String date, String time)
   {
      // Date and Time Validation
      if(!isValidDate(date))
      {
         return false;
      }
      else if(!isValidTime(time))
      {
         return false;
      }
      String sql = "apptdate='" + date + "' AND appttime='" + time + "'";
      List<Object> objs = db.getData("Appointment", sql);
      
      // If there is conflict found as in same date/time is found in database, return false.
      if(objs.size() > 0)
      {
         return false;
      }
      else
      {
         return true;
      }
   } // End isValidAppointDateTime method
   
   // Check if appointment time is at least 30 minutes apart in same date.
   public boolean checkAppointmentTime(String date, String time)
   {
      int minutes;
      int timeCount = 0;
      DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
      String dateCheck = "apptdate='" + date + "'";
      List<Object> objs = db.getData("Appointment", dateCheck);
      String firstTime = time.substring(0,2) + time.substring(3,5);
      
      // Check if appointment is made before 8:00 AM or after 5:00 PM, return false.
      if(Integer.parseInt(firstTime) < 800 || Integer.parseInt(firstTime) > 1700)
      {
         return false;
      }
      else
      {
         // Loop through infomation of times in same date and check if time are 30 minutes apart.
         for(Object obj: objs)
         {
            Appointment appoint = (Appointment)obj;
            String timeCheck = timeFormat.format(appoint.getAppttime());
            String secondTime = timeCheck.substring(0,2) + timeCheck.substring(3,5);
            minutes = Integer.parseInt(firstTime) - Integer.parseInt(secondTime);
      		if(minutes < 0)
      		{
      			minutes = minutes * -1;
      		}
            if(!(minutes >= 30))
            {
               return false;
            }
            else
            {
               timeCount++;
            }
         }
      }
      if(timeCount == objs.size())
      {
         return true;
      }
      else
      {
         return false;
      }
   } // End checkAppointmentTime method
   
   // Check if patient exist based on Patient ID
   public boolean isValidPatient(String patientID)
   {
      String patientCheck = "id='" + patientID + "'";
      List<Object> objs = db.getData("Patient", patientCheck);
      // If there is match found in database, return true.
      if(objs.size() > 0)
      {
         return true;
      }
      else
      {
         return false;
      }
   } // End isValidPatient method

   // Check if physician exist based on Physician ID
   public boolean isValidPhysician(String physicianID)
   {
      String physicianCheck = "id='" + physicianID + "'";
      List<Object> objs = db.getData("Physician", physicianCheck);
      // If there is match found in database, return true.
      if(objs.size() > 0)
      {
         return true;
      }
      else
      {
         return false;
      } 
   } // End isValidPhysician method

   // Check if PSC exist based on PSC ID
   public boolean isValidPSC(String pscID)
   {
      String pscCheck = "id='" + pscID + "'";
      List<Object> objs = db.getData("PSC", pscCheck);
      // If there is match found in database, return true.
      if(objs.size() > 0)
      {
         return true;
      }
      else
      {
         return false;
      } 
   } // End isValidPSC method
   
   // Check if phlebotomist exist based on Phlebotomist ID
   public boolean isValidPhleb(String phlebID)
   {
      String phlebCheck = "id='" + phlebID + "'";
      List<Object> objs = db.getData("Phlebotomist", phlebCheck);
      // If there is match found in database, return true.
      if(objs.size() > 0)
      {
         return true;
      }
      else
      {
         return false;
      } 
   } // End isValidPhleb method
   
   // Check if one or more lab test exist based on Lab Test ID
   public boolean isValidLabTest(ArrayList<String> labtestID)
   {
      int count = 0;
      int countCheck = 0;
      while(count < labtestID.size())
      {
         String labtestCheck = "id='" + labtestID.get(count) + "'";
         List<Object> objs = db.getData("LabTest", labtestCheck);
         // If there is match found in database, return true.
         if(objs.size() > 0)
         {
            countCheck++;
         }
         else
         {
            return false;
         }
         count++;
      }
      if(countCheck == labtestID.size())
      {
         return true;
      }
      else
      {
         return false;
      }      
   } // End isValidLabTest method

   // Check if one or more diagnosis exist based on Lab Test Code
   public boolean isValidDiagnosis(ArrayList<String> labtestCode)
   {
      int count = 0;
      int countCheck = 0;
      while(count < labtestCode.size())
      {
         String diagCheck = "code='" + labtestCode.get(count) + "'";
         List<Object> objs = db.getData("Diagnosis", diagCheck); 
         // If there is match found in database, return true.
         if(objs.size() > 0)
         {
            countCheck++;
         }
         else
         {
            return false;
         }
         count++;
      }   
      if(countCheck == labtestCode.size())
      {
         return true;
      }
      else
      {
         return false;
      }
   } // End isValidDiagnosis method
   
   // Check if the appointment is valid, by looking at each of data based on ID that exist or not      
   public boolean isValidAppointment(String patientID, String physicianID, String pscID, String phlebID, ArrayList<String> labtestID, ArrayList<String> labtestCode)
   {
      // Data Validation - If all test are passed, return true.
      if(!isValidPatient(patientID))
      {
         return false;
      }
      else if(!isValidPhysician(physicianID))
      {
         return false;
      }
      else if(!isValidPSC(pscID))
      {
         return false;
      }
      else if(!isValidPhleb(phlebID))
      {
         return false;
      }
      else if(!isValidLabTest(labtestID))
      {
         return false;
      }
      else if(!isValidDiagnosis(labtestCode))
      {
         return false;
      }     
      else
      {
         return true;
      }
   } // End isValidAppointment method
   
   // Add an appointment based on XML String.
   public String addAppointment(String xmlStyle) throws Exception, SAXException, IOException, XPathExpressionException
   {
      // Attributes
      ArrayList<String> labtestID = new ArrayList<String>();
      ArrayList<String> labtestCode = new ArrayList<String>();
      int count = 0;   
      
      // Begin XML, get current appointment ID incremented by 10
      String xmlString = getStartTag() + getBeginTag();
      String appointNumber = getAppointmentID();
      
      // XML Parsing
      Document doc = xmlParser(xmlStyle);
      
      // Number of Lab Test found in XML string
      int labtestCount = Integer.parseInt(path.evaluate("count(/appointment/labTests/test)", doc));
      
      // Get data from XML string
      String date = path.evaluate("/appointment/date", doc);
      String time = path.evaluate("/appointment/time", doc);
      if(time.length() == 4)
      {
         time = "0" + time + ":00";
      }      
      if(time.length() == 5)
      {
         time = time + ":00";
      }
      else if(time.length() == 7)
      {
         time = time + "0";
      }      
      String patientID = path.evaluate("/appointment/patientId", doc);
      String physicianID = path.evaluate("/appointment/physicianId", doc);
      String pscID = path.evaluate("/appointment/pscId", doc);
      String phlebID = path.evaluate("/appointment/phlebotomistId", doc);
      for(int i = 1; i <= labtestCount; i++)
      {
         String ltID = path.evaluate("/appointment/labTests/test[" + i + "]/@id", doc);
         String ltCode = path.evaluate("/appointment/labTests/test[" + i + "]/@dxcode", doc);
         labtestID.add(ltID);
         labtestCode.add(ltCode);
      }
      
      // Data validation before adding appointment
      if(!isValidAppointDateTime(date, time)) // Check if date and time don't conflict.
      {
         xmlString += getAppointErrorXML();
         xmlString += getEndTag();
      }
      else if(!checkAppointmentTime(date, time)) // Check if time are at least 30minutes gap before and next appointment
      {
         xmlString += getAppointErrorXML();
         xmlString += getEndTag();
      }
      else if(!isValidAppointment(patientID, physicianID, pscID, phlebID, labtestID, labtestCode)) // Check if ID exist in database
      {
         xmlString += getAppointErrorXML();
         xmlString += getEndTag();      
      }
      else
      {
         Appointment newAppt = new Appointment(appointNumber,java.sql.Date.valueOf(date),java.sql.Time.valueOf(time));
         List<AppointmentLabTest> tests = new ArrayList<AppointmentLabTest>();
         while(count < labtestID.size())
         {
            AppointmentLabTest test = new AppointmentLabTest(appointNumber,labtestID.get(count),labtestCode.get(count));
            test.setDiagnosis(getDiagnosis(labtestCode.get(count)));
            test.setLabTest(getLabTest(labtestID.get(count)));
            tests.add(test);
            count++;
         }      
         newAppt.setAppointmentLabTestCollection(tests);
         newAppt.setPatientid(getPatient(patientID));
         newAppt.setPhlebid(getPhleb(phlebID));
         newAppt.setPscid(getPSC(pscID));
         if(db == null)
         {
            db = new DB();
         }
         
         // Check if data is good to go before adding, then return XML string of recently added appointment
         boolean good = db.addData(newAppt);
         if(!good)
         {
            xmlString += getAppointErrorXML();
            xmlString += getEndTag();
         }
         else
         {
            xmlString += getUriXML() + appointNumber + "</uri>";
            xmlString += getEndTag();
         }
      }
      return xmlString;
   } // End addAppointment method     
}