// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #3
// Description: GUI w/ Inner classes & Binary
import java.io.*;
public class BytePe1
{
   public static void main(String args[])
   {
      String name = "";
      int ID = 0;
      double grade1 = 0;
      double grade2 = 0;
      double grade3 = 0;
      double grade4 = 0;
      double avgGrade = 0;
      try
      {
         FileInputStream fis = new 	FileInputStream("ClassList.dat");
		   BufferedInputStream bis = new BufferedInputStream(fis);
         DataInputStream dis = new DataInputStream(bis);
         System.out.printf("\n%-20s %9s %6s %6s %6s %6s %6s", "Name", "ID", 
            "Grade1", "Grade2", "Grade3", "Grade4", "Avg");
         for(int i = 0; i < 4; i++)
         {
            name = dis.readUTF();
            ID = dis.readInt();
            grade1 = dis.readDouble();
            grade2 = dis.readDouble();
            grade3 = dis.readDouble();
            grade4 = dis.readDouble();
            avgGrade = (grade1 + grade2 + grade3 + grade4) / 4;
            System.out.printf("\n%-20s %7s   %.3g   %.3g   %.3g   %.3g   %.3g", name, ID, grade1, grade2, grade3, grade4, avgGrade);
         }                  
         dis.close();
      }
      catch(Exception e)
      {
         System.out.println("Exception: " + e.getMessage());
		   e.printStackTrace();
      }
   }
}