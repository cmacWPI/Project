import java.util.*;
import components.data.*;
import business.*;
import service.*;

public class Project2
{
   public static void main(String[] args) throws Exception
   {
      LAMSService service = new LAMSService();
      BusinessLayer business = new BusinessLayer();
      AppointmentService test = new AppointmentService();
      System.out.println(service.initialize());
      String x = "<?xml version='1.0' encoding='utf-8' standalone='no'?><appointment><date>2017-09-01</date><time>10:15</time><patientId>210</patientId><physicianId>20</physicianId><pscId>500</pscId><phlebotomistId>100</phlebotomistId><labTests><test id='86900' dxcode='292.9' /><test id='86609' dxcode='307.3' /></labTests></appointment>";
      System.out.println(test.addAppointment(x));
      x = "<?xml version='1.0' encoding='utf-8' standalone='no'?><appointment><date>2017-09-01</date><time>10:15</time><patientId>210</patientId><physicianId>20</physicianId><pscId>500</pscId><phlebotomistId>100</phlebotomistId><labTests><test id='86900' dxcode='292.9' /><test id='86609' dxcode='307.3' /></labTests></appointment>";
      System.out.println(test.addAppointment(x));
   }
}