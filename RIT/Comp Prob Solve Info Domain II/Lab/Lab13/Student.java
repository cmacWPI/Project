// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #13
// Description: Whole Student Data from XML
import java.util.ArrayList;
public class Student
{
   // Declariations
   private int ID;
   private Person Person;
   private ArrayList<Course> courses;
   private int index;
   private int courseCount;
   
   public Student(int studentID, Person aPerson, ArrayList<Course> course, int CC)
   {  
      ID = studentID;
      Person = aPerson;
      courses = course;
      courseCount = CC;
   }

   public int getID()
   {  
      return ID;
   }
   
   public String getIDName()
   {  
      return Person.getFirstName() + " " + Person.getLastName();
   }
   
   public String toString()
   {
      String finalStudent = "ID: " + getID() + " " + getIDName();
      for(int count = 0; count < courseCount; count++)
      {
         finalStudent+= courses.get(index).toString();
         index++;
      }
      return finalStudent;
   }
}
