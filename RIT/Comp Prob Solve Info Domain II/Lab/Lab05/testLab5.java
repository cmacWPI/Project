import java.util.*;
import java.io.*;
public class testLab5
{
   public static void main(String[] args)
   {
      DataInputStream dis = null;
      int zip = 0;
      String city = null;
      String state = null;
      int count = 0;
      try
      {
         dis = new DataInputStream(new FileInputStream("ZipCityState.dat"));
         while(true)
         {
             zip = dis.readInt();
             city = dis.readUTF();
             state = dis.readUTF();     
             count++;    
         }
//             zip = dis.readInt();
//             city = dis.readUTF();
//             state = dis.readUTF();
//             System.out.println(zip);
//             System.out.println(city);
//             System.out.println(state);
//             zip = dis.readInt();
//             System.out.println(zip);
//             city = dis.readUTF();
//             System.out.println(city);
//             state = dis.readUTF();
//             System.out.println(state);
      }
      catch(FileNotFoundException fnf)
      {
         System.out.println("File not found");
      }
      catch(EOFException eof)
      {
         System.out.println(zip);
         System.out.println(city);
         System.out.println(state);
         System.out.println(count);
         try
         {
            dis.close();
         }
         catch(IOException ioe)
         {
            System.out.println("Error closing the file");
         }
      }      
      catch(IOException ioe)
      {
         System.out.println(count);
         System.out.println("Error");
      }
   }
}