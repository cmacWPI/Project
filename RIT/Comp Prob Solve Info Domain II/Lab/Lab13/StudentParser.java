// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #13
// Description: XML
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.util.ArrayList;
/**
 *  ISTE-121 Lab XML
 *	An XML parser for student data
 *	Original LBE by: Jai Kang
 * Converted to lab by: Maxwell Sweikert, Jonathan Theismann, Michael Floeser
 */
public class StudentParser 
{
   private DocumentBuilder builder;
   private XPath path;

   /**
    * Constructs a parser that can parse student lists.
    */
   public StudentParser() throws ParserConfigurationException 
   {
      DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
      builder = dbfactory.newDocumentBuilder();
      XPathFactory xpfactory = XPathFactory.newInstance();
      path = xpfactory.newXPath();
   }

   /**
    * Parses an XML file containing student data.
    * @param fileName the name of the file
    */
   public ArrayList<Student> parse(String fileName) throws SAXException, IOException, XPathExpressionException 
   {
      File f = new File(fileName);
      Document doc = builder.parse(f);
      
      // ArrayList to hold Student Objects
      ArrayList<Student> student = new ArrayList<Student>();
      int studentCount = Integer.parseInt(path.evaluate("count(/students/student)", doc));

      System.out.println("*** Students Listing ***");
      for (int i = 1; i <= studentCount; i++)
      {
         // Parse the XML data of ID, Last Name, and First Name
         int ID = Integer.parseInt(path.evaluate("/students/student[" + i + "]/id", doc));
         String lName = path.evaluate("/students/student[" + i + "]/person/lastperson", doc);
         String fName = path.evaluate("/students/student[" + i + "]/person/firstperson", doc);
         
         // Create a person object with first and last name.
         Person person = new Person(fName, lName);
         
         // Create an Course ArrayList to hold Course objects
         ArrayList<Course> course = new ArrayList<Course>();
         
         // Parse through courses with courseCount and add course object to Course ArrayList 
         int courseCount = Integer.parseInt(path.evaluate("count(/students/student[" + i + "]/courses/course)", doc));
         for(int j = 1; j <= courseCount; j++)
         {
            String courseName = path.evaluate("/students/student[" + i + "]/courses/course[" + j + "]/@name", doc);
            String courseID = path.evaluate("/students/student[" + i + "]/courses/course[" + j + "]/@coursenumber", doc);
            Course courses = new Course(courseName, courseID);
            course.add(courses);
         }
         
         // Create Student Object and add it to Student ArrayList
         Student students = new Student(ID, person, course, courseCount);
         student.add(students);
      }
      return student;
   }
   
   public void printReport(ArrayList<Student> student) 
   {
		// Loops through Student ArrayList and prints the student object
      for(Student anStudent : student)
      {
         System.out.println(anStudent.toString());
      }
   }
   
   /**
      Specify the filename and parse the data. 
   */
   public static void main(String []args) throws Exception 
   {
      // define a default file to parse
	   String parseThis = "studentCourses.xml";

	   StudentParser studentParser = new StudentParser();
      
      // if we have one item specified on the command line, use it
      if( args.length == 1 )
      {
         parseThis = args[0];
      }
      else if(args.length > 1)
      {
         System.out.println("Specify only one file on the command line.\n"+
                "If you have only one filename, it may contain spaces.\n"+
                "Place double quotes around the filename, and rerun. \n"+
                "For example: \"C:/Program files\"");
         return;
      }
      
      // else use the default parse file
      ArrayList<Student> students = studentParser.parse(parseThis);
      
      // Calls printReport method
      studentParser.printReport(students);
   } // end main
} // end class StudentParser





