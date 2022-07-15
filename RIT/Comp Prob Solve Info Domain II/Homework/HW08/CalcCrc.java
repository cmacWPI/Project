// CalcCrc.java
import java.text.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;
public class CalcCrc
{
   private final int BF_SIZE = 8192;
   public static void main(String [] args)
   {
      CalcCrc cc = new CalcCrc();
      String filename = args.length>0 ? args[0] : "CalcCrc.java";	// Gets the filename
      long   crc;
      File fil = new File( filename );
      crc = cc.getCrc( fil  );

      System.out.println("Information for : "+ filename              );
      System.out.println("CRC             : "+ Long.toHexString(crc).toUpperCase() );
      System.out.println("Name            : "+ fil.getName()         );
      System.out.println("Path Name       : "+ fil.getAbsolutePath() );
      System.out.println("File size       : "+ fil.length()          );
      System.out.println("Directory?      : "+ fil.isDirectory()     );
      System.out.println("File?           : "+ fil.isFile()          );
      System.out.println("Hidden?         : "+ fil.isHidden()        );
      System.out.println("Exist?          : "+ fil.exists()          );
      
      long ts = fil.lastModified();
      Format sdf = new SimpleDateFormat( "MM/dd/yy hh:mm:ss aa");
		System.out.printf( "Last Modified  :: %1$tF %1$tT \n", ts);
		System.out.printf( "Last Modified  :: %1$tF %1$tr \n", ts);
      System.out.println("Last Modified   : "+ sdf.format(new Date(ts)));
   }
	
	/**
		Given a file object, return the CRC.
		Students, you may use this method in your homework, it will not be
		considered plagiarism.
		What you need to do is add comments, explaining what the code does.
		Look up the methods in the CRC32 javadocs. Don't copy/paste explainations.
	*/
   public long getCrc( File aFile )
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
		
   } // end getCrc() method
   
} // end CalcCRC class