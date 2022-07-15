// Name: Chevy Mac
// Course: ISTE-121-01
// Homework: #3
// Description: Binary and CSV (Text) I/O
import java.io.*;
import java.util.*;
public class Baseball
{
   public static void main(String args[])
   {
      // Declarations
      BufferedReader readData = null;
      int record = 0;
      int check = 0;
      int invaidData = 1;
      boolean validData = true;
      String[] arrayStrings = null;
      String[] testString = null;
      String firstName = "";
      String lastName = "";
      int birthDay = 0;
      int birthMonth = 0;
      int birthYear = 0;
      int weight = 0;
      double height = 0.0;
      String[] BaseballHeaders = {"Birth Day", "Birth Month", "Birth Year", "Weight", "Height"};
      String file = null;
      
      // Scanner object
      Scanner in = new Scanner(System.in);
      String filename = in.nextLine();
      
      // Checks if the file is .csv else prints error and exits the program.
      if(filename.substring(filename.length() - 4, filename.length()).equals(".csv"))
      {
         file = filename.substring(0, filename.length() - 4);
      }
      else
      {
         System.out.println("Error: requires csv files.");
         System.exit(0);
      }
      
      // Start of program
      try
      {
         // Loads and read the csv file while writing data to .bin file
		   readData = new BufferedReader(new FileReader(file + ".csv"));
         FileOutputStream fileOutput = new FileOutputStream(file + ".bin");
         ObjectOutputStream writeData = new ObjectOutputStream(fileOutput);
         // Reads the first record of line
         String line = readData.readLine();
         // While the record is not null, it will keep going
         while((line) != null)
         {
            if(record > 0) // Skips the header
            {
               arrayStrings = line.split(","); // Seperates the comma 
               try
               {
                  if(arrayStrings.length == 7)
                  {
                     String test1 = arrayStrings[0];
                     String test2 = arrayStrings[1];
                     int test3 = Integer.parseInt(arrayStrings[2]);
                     check++;
                     int test4 = Integer.parseInt(arrayStrings[3]);
                     check++;
                     int test5 = Integer.parseInt(arrayStrings[4]);
                     check++;
                     int test6 = Integer.parseInt(arrayStrings[5]);
                     check++;
                     double test7 = Double.parseDouble(arrayStrings[6]);
                  }
                  else
                  {
                     System.err.println("Error found in: " + arrayStrings[0]);
                     System.err.println("Not enough fields in the record");
                     System.out.println();
                     validData = false;
                     invaidData++;
                  }
               }
               
               // Prints the error and makes validData become false
               catch(NumberFormatException ioe)
               {
                  System.err.println("Error found in: " + line);
                  System.err.println("Offending item is: " + BaseballHeaders[check]);
                  System.out.println();
                  validData = false;
                  invaidData++;
               }
               catch(ArrayIndexOutOfBoundsException ioe)
               {
                  System.err.println("Error found in: " + line);
                  System.err.println("Offending item is: " + BaseballHeaders[check]);
                  System.out.println();
                  validData = false;
                  invaidData++;
               }
               if(validData != false)
               {
                  writeData.writeUTF(arrayStrings[0]);
                  writeData.writeUTF(arrayStrings[1]);
                  writeData.writeInt(Integer.parseInt(arrayStrings[2]));
                  writeData.writeInt(Integer.parseInt(arrayStrings[3]));
                  writeData.writeInt(Integer.parseInt(arrayStrings[4]));
                  writeData.writeInt(Integer.parseInt(arrayStrings[5]));
                  writeData.writeDouble(Double.parseDouble(arrayStrings[6]));
               } 
            }
            line = readData.readLine();
            validData = true;
            check = 0;
            record++;
            // Checks if the line is blank, and allows to skip it.
            try
            {
               if((line) != null)
               {
                  testString = line.split(",");
               }
               String test1 = testString[0];
               String test2 = testString[1];
               int test3 = Integer.parseInt(testString[2]);
               int test4 = Integer.parseInt(testString[3]);
               int test5 = Integer.parseInt(testString[4]);
               int test6 = Integer.parseInt(testString[5]);
               double test7 = Double.parseDouble(testString[6]);
            }
            catch(ArrayIndexOutOfBoundsException ae)
            {
               line = readData.readLine();
               record++;
               invaidData++;
            }
            //Used to bypass exception with try code
            catch(NumberFormatException ioe)
            {

            }
         }
         writeData.close(); // Closes the .bin file after finished writing.
      }
      catch(IOException ioe)
      {
         System.out.println("There is error with the file.");
         System.exit(0);
      }
      //Try to close the file after reading .csv file
      try
      {
         readData.close();
      }
      catch(IOException ioe)
      {
         System.out.println("Error closing the file.");
         System.exit(0);
      }
      catch(NullPointerException ioe)
      {
         System.out.println("There is error with the file.");
      }
      
      //Attempts to read data from .bin file and prints it.
      try
      {
         FileInputStream fis = new FileInputStream(file + ".bin");
         ObjectInputStream ois = new ObjectInputStream(fis);
         System.out.println("Read in " + record + " records from " + file + ".cvs");
         System.out.println("Wrote out " + (record - invaidData) + " records to " + file + ".bin");
         System.out.printf("\n%-1s       %7s     %s   %s", " First & Last Name", "Birthdate", "Weight", "Height");
         System.out.println();
         for(int i = 0; i < record - invaidData; i++)
         {
            firstName = ois.readUTF();
            lastName = ois.readUTF();
            birthDay = ois.readInt();
            birthMonth = ois.readInt();
            birthYear = ois.readInt();
            weight = ois.readInt();
            height = ois.readDouble();
            System.out.printf("\n%-1s %s  %s/%s/%s   %6s      %.3g", firstName, lastName, birthMonth, birthDay, birthYear, weight, height);
         }
         ois.close(); // Closes the .bin file after reading it.
      }
      catch(Exception e)
      {
         System.out.println("Exception: " + e.getMessage());
		   e.printStackTrace();
      }
   }
}