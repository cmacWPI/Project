// Name: Chevy Mac
// Course: ISTE-121-01
// Homework: #8
// Description: Find the duplicates and writes the file output.
import java.text.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;
public class FindDups
{
   // Declarations
   private int fileCount = 0;
   private int dupFile = 0;
   private int dupGroup = 0;
   private final int BF_SIZE = 8192;
   long crc;
   Hashtable<Long, ArrayList<File>> fileCRC = new Hashtable<Long, ArrayList<File>>();
   
   /**
      Gets all files along with CRC and put on hashtables
      @param file of current Directory
      @return a number of files
   */
   public long listFiles(File currentDir)
   {
      File[] fileList = currentDir.listFiles(); //List all files in the current directory
		for(int i = 0; i < fileList.length; i++)
		{
		   if(fileList[i].isDirectory()) //Checks if the file is directory
		   {
            if(fileList[i].isHidden()) //Checks if the directory is hidden
            {
               System.out.println(fileList[i] + " is hidden.");
            }
            listFiles(fileList[i]);
		   }
		   else //If the file isn't directory
		   {
		      fileCount++; // +1 for each file found.
            if(fileList[i].isHidden()) //Checks if the file is hidden and prints out result,
            {
               System.out.println(fileList[i] + " is hidden.");
            }
            else
            {
               // Gets CRC value of each file, puts into hash table.
               crc = getCrc(fileList[i]);            
               insert(crc, fileList[i]);
            }
		   }
		}
      return fileCount;
   }

   /**
      Puts two values into hashtable, checks
      @param long of crcValue
      @param file of file directory
   */
   public void insert(long crcValue, File fileName) 
   {
      ArrayList<File> theFile = fileCRC.get(crcValue);
      
      // If nothing found, adds arrayList and the file else, add the file to existing arrayList.
	   if(theFile == null) 
      {
		   theFile = new ArrayList<File>();
		   theFile.add(fileName);
	   } 
	   else 
      {
         theFile.add(fileName);
   	}
      
      // Put to hash table
      fileCRC.put(crcValue, theFile);
   }
   
   /**
      Gets the HastTables (For Testing Only)
   */
   public void getHashTable()
   {
      System.out.println(fileCRC);
   }
   
   /**
      Finds the duplicates and write to output file.
      @param file output for writing
   */
   public void getDuplicates(File output)
   {
      // Declarations
      BufferedWriter writeData = null;
      
      //Try to open the file for writing
      try
      {
         writeData = new BufferedWriter(new FileWriter(output , false));
      }
      catch(IOException ioe)
      {
         System.out.println("Error opening the file for writing.");
      }
      
      // Sorts the data by CRC value.
      SortedSet<Long> sortedSet = new TreeSet<Long>(fileCRC.keySet());
      Iterator<Long> it = sortedSet.iterator();
	 
      while(it.hasNext()) 
      {
  		   long crcNum = it.next(); // Gets the CRC value
	   	ArrayList<File> files = fileCRC.get(crcNum);	// Get the files(s) based on CRC value.
		   if(files.size() > 1 )
         {
            dupGroup++;
            try
            {
               for(int i = 0; i < files.size(); i++)
               {
                  dupFile++;
                  long ts = files.get(i).lastModified();
                  Format sdf = new SimpleDateFormat( "MM/dd/yy hh:mm:ss aa");
                  String crcData = Long.toHexString(crcNum).toUpperCase() + "\t" + files.get(i).length() + " " + sdf.format(new Date(ts)) + " " + files.get(i);
				      
                  // Write the data to output file
                  writeData.write(crcData);
                  writeData.newLine();
				      //writeData.write("\n"); //"Enter"
               }
               writeData.newLine();
            }
            catch(IOException ioe)
            {
               System.out.println("Error writing to the file.");
            }
         }
      }
      System.out.println("Duplicate files = " + dupFile);
      System.out.println("Duplicate groups = " + dupGroup);
      try
      {
         writeData.close();
      }
      catch(IOException ioe)
      {
         System.out.println("Error closing the file.");
      }   
   }
   
   /**
      Gets the CRC value from computing CEC-32 of file's data stream.
      @param file for getting CRC value
      @return CRC value of file
   */
   public long getCrc(File aFile)
   {
      byte [] buffer = new byte[BF_SIZE];
      CRC32 crc = new CRC32();
      int len = 0;
      long crcValue = -1;

      crc.reset();
      try
      {
         BufferedInputStream bis = new BufferedInputStream(new FileInputStream(aFile));

         while ((len = bis.read(buffer)) > -1) 
         {
            crc.update(buffer,0,len);
         }
         
         crcValue = crc.getValue();
         bis.close();
      }
      catch ( Exception e )
      {
         e.printStackTrace();
      }
      
      return crcValue;
   }   
  
}