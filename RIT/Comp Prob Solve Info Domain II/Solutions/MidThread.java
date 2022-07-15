// A solution for Practicum 1 - Counter (Threads)
import java.util.*;
public class MidThread
{
   private int counter = 0;
   private static final String THREAD_LOCK = "Lock";

   public static void main(String[] args)
   {
      new MidThread();
   }
   
   public MidThread()
   {
      Vector<InnerThread> threads = new Vector<InnerThread>();
      for(int i = 1; i <= 4; i++)
      {
         InnerThread it = new InnerThread(i);
         it.start();
         threads.add(it);
      }
      
      for(InnerThread it : threads)
      {
         try
         {
            it.join();
         }
         catch(InterruptedException ie)
         {
      
         }
      }

      System.out.println("Final count is " + counter);
   }
   
   class InnerThread extends Thread
   {
      public InnerThread(int num)
      {
         super("" + num);
      }
      
      public void run()
      {
         for(int i = 0; i < 10; i++)
         {
            synchronized(THREAD_LOCK) // relock.lock()
            {
               counter++;
               System.out.println("Thread " + getName() + " counter " + counter);
            }
            // Thread will sleep for 1ms
            try
            {
               sleep(1);
            }
            catch(InterruptedException ie)
            {
               System.out.println("Error in Thread " + getName());
            }
         }
      }
   }  

}