// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #5
// Description: Threads & Byte I/Os
import java.util.*;
import java.io.*;
import java.util.ArrayList;
public class Lab5
{
 	ArrayList<String> cityList = new ArrayList<String>();
   ArrayList<Object> threads = new ArrayList<Object>();
   private boolean check = true;
   private int i = 0;
   private int num = 0;
   private int test = 0;
   private int c = 0;
   
	public static void main(String [] args)
	{
      Scanner in = new Scanner(System.in);
      String[] data;
      String city = in.nextLine();
		new Lab5(city);
	}
	
	public Lab5(String city)
	{
      String[] cityData;
      cityData = city.split(" ");
      Thread rF = new Thread();
      try
      {
         DataOutputStream dos = new DataOutputStream(new FileOutputStream("ZipCityState.dat"));
      }
      catch(FileNotFoundException fnf)
      {
         System.out.println("File not found");
      }
      while(check) 
      {
         i++;
         rF = new readFile("Lab5File" + i + ".dat");
         rF.start();
         threads.add(rF);
      }
      Thread extra = new Thread()
      {
         DataInputStream dis = null;
         File objectList = new File("CityArrayList.ob");
         public void run()
         {
            try
            {
               for(int t = 0; t < i; t++)
               {
                  ((Thread)threads.get(t)).join();
               }
            }
            catch(InterruptedException ie)
            {
            
            }
            try
            {
               dis = new DataInputStream(new FileInputStream("ZipCityState.dat"));
               while(true)
               {
                  int zip = dis.readInt();
                  String city = dis.readUTF();
                  String state = dis.readUTF();     
                  num++;
               }
            }
            catch(FileNotFoundException fnf)
            {
               System.out.println("File not found");
            }
            catch(EOFException eof)
            {
               try
               {
                  dis.close();
               }
               catch(IOException ioe)
               {
                  System.out.println("Error closing the file");               
               }
               try
               {
                  FileOutputStream fileOutput = new FileOutputStream("CityArrayList.ob");
                  ObjectOutputStream writeOb = new ObjectOutputStream(fileOutput);
                  writeOb.writeObject(cityList);
               }
               catch(FileNotFoundException fnf) 
               {
                  System.out.println("File not found");
			      }
               catch(IOException ioe)
               {
                  System.out.println("Error writing object to file.");               
               }
               System.out.printf("Loaded list size has %,2d", cityList.size());
               System.out.printf(" cities.%nZipCityState.dat has %,2d", num);
               System.out.printf(" cities.%nCityArrayList.ob is %,2d", objectList.length());
               System.out.printf(" bytes.%n");
               if(!cityData[0].equals(""))
               {
                  while(c < cityData.length)
                  {
                     if(cityList.indexOf(cityData[c]) == -1)
                     {
                        System.out.printf(cityData[c] + " was NOT found%n");
                     }
                     else
                     {
                        System.out.printf(cityData[c] + " was found at position %,2d", cityList.indexOf(cityData[c]));
                        System.out.printf("%n");
                     }
                     c++;
                  }
               }
            }
            catch(IOException ioe)
            {
               System.out.println("Error encountered while reading the file.");               
            }
         }
      };
      extra.start();
	}
   
	class readFile extends Thread 
   {
		private String filename;
		
		public readFile(String filename)
		{
			this.filename = filename;
		}   
   
		public void run() 
      {
			DataInputStream dis = null;
         DataOutputStream dos = null;
			int zip = 0;
         String city = null;
         String state = null;
         double longitude = 0.0;
         double latitudes = 0.0;
         int timeZone = 0;
         int DST = 0;
			int count = 0;
			try
         {
				dis = new DataInputStream(new FileInputStream(filename));
            dos = new DataOutputStream(new FileOutputStream("ZipCityState.dat", true));
				while(true) 
            {
					zip = dis.readInt();
               city = dis.readUTF();
               state = dis.readUTF();
               longitude = dis.readDouble();
               latitudes = dis.readDouble();
               timeZone = dis.readInt();
               DST = dis.readInt();               
					synchronized(cityList) 
               {
						cityList.add(city);
                  dos.writeInt(zip);
                  dos.writeUTF(city);
                  dos.writeUTF(state);
						count++;
 					}
				}
			}
			catch(FileNotFoundException fnf) 
         {
            check = false;
			}
			catch(EOFException eof) 
         {
            System.out.printf("File " + filename + " completed, record count is %,2d%n",count);
				try
            {
					dis.close();
               dos.close();
				}
				catch(IOException ioe) 
            {
					System.out.println("Error closing file " + filename);
				}
			}	
			catch(IOException ioe)
         {
				System.out.println("Error reading file " + filename);
			}     
      }
   }
   
}