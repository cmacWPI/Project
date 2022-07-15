import java.io.*;
import java.nio.file.*;
import java.security.*;
/**
 *	DirectoryContents - Find how much space is stored in a specified directory. <br />
 *	Recursively traverse subdirctory structure <br />
 *	Michael Floeser
 * Updated by Jai Kang & Jim Leone
 */

public class DirectoryContents
{
   // Attributes go here
	private int fileCount = 0;
   private int dirCount = 0;
	long fileSize = 0;
   private int number = 0;
      
	// This is called with a starting directory in a file object
   public long getSpace( File currentDir )
   {
      File[] fileList = currentDir.listFiles(); //List all files in the current directory
      try
      {
		   for(int i = 0; i < fileList.length; i++)
		   {
		   	if(fileList[i].isDirectory()) //Checks if the file is directory
		   	{
		   		dirCount++; // +1 for each directory found
               System.out.println(i + ": " + fileList[i]);
		   		fileSize+= getSpace(fileList[i]); //Checks space inside the directory found.
               number = i;
		   	}
		   	else //If the file isn't directory
		   	{
		   		fileCount++; // +1 for each file found
               System.out.println(i + ": " + fileList[i]);
		   		fileSize += fileList[i].length();
               number = i;
		   	}
		   }
      }
      catch(NullPointerException npe)
      {
         System.out.println("Error.");
      }
      catch(AccessControlException ade)
      {
         System.out.println("Access is Denied :" + fileList[number]);
      }
		return fileSize;
   }
   
	public int getFileCount() 
	{
      return fileCount;
   }

   public int getDirCount() 
	{
      return dirCount;
   }
   
   public static void main(String[] args)
   {
      if(args.length > 0) {
         DirectoryContents fs = new DirectoryContents();
         long totalSpace = fs.getSpace(new File(args[0]));
         System.out.printf("Total space is %,d bytes = %,.2f MB%n",
										totalSpace,totalSpace/1000000.);
         System.out.printf("Files: %d   Directories: %d %n",
										fs.getFileCount(), fs.getDirCount());
      }
      else {
         System.out.println("No command line input");
         System.exit(1);
      }
   }
}