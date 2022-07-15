/*
 *  Threads50.java     ------ STUDENT VERSION ------       <br>
 *
 *  @author M.Floeser
 *  @version 1.2
 *  Program to create and show multiple threads.
 *  First the students have to debug the code, get it to work, then
 *  modify it to have multiple running threads create new threads.
 *  There are MANY errors to fix: Missing code, syntax errors, logic errors. <br>
 *  ISTE-121 In Class practice/puzzle
 */

import javax.swing.*;
import java.util.*;

/** This class extends Thread to create the separate threads (1st ERROR).
    Threads50 when finished will print threads from 1 through the number entered on the command line. 
    The number entered on the command line must be below 50.
    Each of the even number threads created will create a new thread that is 50 more than the even numbered
    thread. Care must be taken to not create new threads from the wrong place.
*/
public class Threads50 extends Thread
{
    // Define a class variable for "all instances" of the threads to use.
    public long finishCount = 0;
    private static final String THREAD_LOCK = "";

    // Constant to wait one second
    private final int ONE_SECOND = 1;

    /* Threads50 constructor, set's the threads name.
       @params name Name of the thread
       @params _calcValue This is a calculated value
    */
    public Threads50(String name, long _calcValue) 
    {
      super(name);
    } //end Threads50() constructor

    /**
     * Sleeps for a while, then print out the names of the Threads.
     */

    public void run() 
    {

      // Get the name of the thread, specified by the super(name) in 
      // the constructor.  Turn it into an int so we can tell which thread # this is.
      try 
      {
         // Have the thread wait a random number of milliseconds (1ms <= x <=1 sec)
         // Sleep is not really a good way to stagger threads, but we will use it here
         sleep((long)Math.random() * ONE_SECOND + 1);
         /** Print the thread number. Add 1 to finishCount before printing the thread name information. */
         synchronized(THREAD_LOCK) 
         {
            finishCount++;
            System.out.println("Hello I am thread " + getName() +
                          " finished " + finishCount);            
         }
      }
      catch(InterruptedException e) 
      {
         System.out.println(e.getMessage());
      }   //catch

    }  //end run()

///////////////////////////////////////////////////////////////////
    /**
     * Creates multiple threads based on the user specified number
     */

    public static void main ( String[] args ) 
    {

      int x = 0;               // Count number of threads
      long calcValue;      // Calculated value
      Vector<Threads50> threads = new Vector<Threads50>();
      
      if (args.length != 1)  // did user specify any input
      {
        System.err.println( "Usage:  java MyThreads number-of-threads" );
        System.exit(1);
      }
      
      // Get the number of threads to start from the command line
      try
      {
         x = Integer.parseInt(args[0]);
      }
      catch(NumberFormatException nfe)
      {
         System.out.println("Thread50: Invaid input, not a number. Bye!");
         System.exit(0);
      }
      catch(ArrayIndexOutOfBoundsException ae)
      {
         System.out.println("Thread50: No input found. Bye!");
         System.exit(0);
      }
      if(x < 0) //Make sure input is ok, give user an informative message if it is wrong.
      {
          System.err.println("Threads50: Invalid number.  Bye!");
          System.exit(0);
      }
      else
      {
         try
         {
            // Start the number of threads specified
            for(int i = 0; i < x; i++) 
            {
                 calcValue = x/1; 
                 String number = Integer.toString(x);
                 // Shortcut way to name a thread and execute the thread(s)
                 Threads50 t50 = new Threads50(num);
                 t50.start();
                 threads.add(t50);
            }
            for(Threads50 t50 : threads)
            {
               try
               {
                  t50.join();
               }
               catch(InterruptedException ie)
               {
            
            }
         }}
         catch(Exception e)
         { 
         }
      }

    }  // end main() method

}//Threads50.java