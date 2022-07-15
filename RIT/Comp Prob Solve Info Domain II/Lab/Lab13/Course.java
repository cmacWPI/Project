// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #13
// Description: Course Data from XML
public class Course
{  
   // Declarations
   private String courseName;
   private String courseNumber;
   
   // Constructor
   public Course(String cName, String cNumber)
   {  
      courseName = cName;
      courseNumber = cNumber;
   }
   
   public String getCourseName()
   {  
      return courseName;
   }

   public String getCourseNumber()
   {  
      return courseNumber;
   }   
   
   // Returns String when method is called
   public String toString()
   {  
      return String.format("%n%14s: %-5s", getCourseNumber(), getCourseName());
   }
}
