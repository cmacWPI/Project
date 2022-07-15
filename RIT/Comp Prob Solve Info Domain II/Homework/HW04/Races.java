// Name: Chevy Mac
// Course: ISTE-121-01
// Homework: #4
// Description: Thread & Graphics - Animation
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
public class Races extends JFrame
{
   // Declarations - Protected 
   protected String racerCheck = null;
   protected int racers = 0;
   protected int finishLineWidth = 0;
   protected ImageIcon races;
   protected int i = 0;
   protected int counter = 0;
   protected boolean keepGoing = true;
   protected ArrayList<Object> threads = new ArrayList<Object>();
   
   public Races()
   {
      // Scanner for command line
      Scanner in = new Scanner(System.in);
      try
      {
         racerCheck = in.nextLine();
         // If no commands input --> Defaults to 5
         if(racerCheck.equals(""))
         {
            racers = 5;
         }
         else
         {
            racers = Integer.parseInt(racerCheck);
         }
      }
      catch(InputMismatchException ime)
      {
         System.out.println("Error: has to be numbers only");
         System.exit(0);
      }
      catch(NumberFormatException nfe)
      {
         System.out.println("Error: has to be numbers only");
         System.exit(0);
      }      
      setTitle("Off to the Races - by Chevy Mac"); // Sets Title for JFrame
      races = new ImageIcon("races.jpg"); //Sets ImageIcon
      
      // Sets JFrame size based on image's height and width
      setSize((int) (races.getIconWidth() * 20), (int) (racers * races.getIconHeight() * 1.5));
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      // Sets finishLine to draw
      finishLineWidth = (int) (races.getIconWidth() * 20) - (int) (races.getIconWidth() * 2);
      
      // Creates GridLayout and creates Lanes
      setLayout(new GridLayout(racers, 1));
      
      // Inner Class of JPanel
      class OneLane extends JPanel implements Runnable
      {

         // Declarations of Inner Class
         int x = 0;

         public void run()
         {
            counter++;
            String name = Integer.toString(counter); // Converts int to string
            setName(name); // Sets Thread Name by counter
            // Program starts after one second has passed. 
            try
            {
               Thread.sleep(1000);
            }
            catch(InterruptedException ie)
            {
				   ie.printStackTrace();
				}
            // Loop will run until one hits finish line
            while(keepGoing)
            {
               double rand1 = Math.random() * 50;
   			   try
               {
                  x = x + (int) ((Math.random() * races.getIconWidth())); // Moves Picture by random intervals based on image's width
                  repaint();
                  Thread.sleep((long) (Math.random() * (20 * rand1))); // Sleeps by random intervals
                  
                  // Checks if the x went over finishLine and then proceeds to stop other threads.
                  if(x >= finishLineWidth)
                  {
                     keepGoing = false;
                  }
               }
				   catch(InterruptedException ie)
               {
					   ie.printStackTrace();
               }
            }
            if(x >= finishLineWidth)
            {
               repaint();
            }                  
         }
         
         public void paint(Graphics g)
         {
            if(x <= finishLineWidth)
            {
               g.drawLine(finishLineWidth, 0, finishLineWidth, 1000);
               races.paintIcon(this, g, x, 0);
            }
            
            // Only if Winner is announced
            if(x >= finishLineWidth)
            {
               g.drawString("Winner is #" + getName(),  0, 20);
               g.drawLine(finishLineWidth, 0, finishLineWidth, 1000);
               races.paintIcon(this, g, x, 0);
            } 
         }
      }
      
      // Creates JPanel based on # of racers
      while(i < racers)
      {
		   OneLane OL = new OneLane();
         add(OL);				                       
		   Thread OLThread = new Thread(OL);
		   OLThread.start();
         threads.add(OLThread);
         i++;
      }
      
      // Joins Thread after all thread are done running
      Thread extra = new Thread()
      {
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
         }
      };
      extra.start(); 
   }  
   
   // Main Program
   public static void main(String args[])
   {
      Races jfMain = new Races();
      jfMain.setLocationRelativeTo(null);																	
      jfMain.setVisible(true);
   }
}