// Name: Chevy Mac
// Course: ISTE-121-01
// Homework: #8
// Description: Test to find Duplicates
import java.text.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;
public class TestDups
{
   /**
   * Main Program
   */
   public static void main(String[] args)
   {
      String startingDir = args.length>0 ? args[0] : "C:\\Public\\";	// Gets the directory
      System.out.println("Find Duplicate Files - by Chevy Mac");
      System.out.println("Find Duplicates. Processing: " + startingDir);
      double startTime = System.currentTimeMillis();
      
      File dir = new File(startingDir);
      FindDups fileDup = new FindDups();
      
      long fileCount = fileDup.listFiles(dir);
      System.out.println("Files gathered = " + fileCount);
      
      //fileDup.getHashTable(); // For Testing only
      File output = new File("FindDups.txt");
      
      System.out.println("Writing output file: " + output);
      fileDup.getDuplicates(output);
      
      double endTime = System.currentTimeMillis();
      double totalTime = (endTime - startTime) / 1000;
      DecimalFormat realTime = new DecimalFormat("#.####");
      System.out.println("Processing took: " + realTime.format(totalTime) + " seconds");
   }
}