// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #4
// Description: Threads & Other Things
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Lab4 extends JFrame
{
   // Declarations
   private JButton startButton;
   private JProgressBar jprogBar1 = new JProgressBar();
   private JProgressBar jprogBar2 = new JProgressBar();
   private JProgressBar jprogBar3 = new JProgressBar();
   private boolean keepGoing = true;
   private int start = 0;
   private int counter = 0;
   private String winner = null; 
   public Lab4()
   {
      setTitle("Lab 4"); // Sets Title for JFrame
      
      // Creates GridLayout and creates Start Button
      setLayout(new GridLayout(0, 1));
      startButton = new JButton("Lets start this show");
      add(startButton);
      
      // Inner Class of JPanel
      class InnerProgress extends JPanel
      {
         // Declarations of Inner Class
         JPanel createBars;
         JPanel createBars2;
         public JPanel createBars1()
         {
			   this.add(new JLabel("Progres Bar 1: ", SwingConstants.RIGHT));
			   jprogBar1.setOrientation(JProgressBar.HORIZONTAL);
			   jprogBar1.setMinimum(0);							
			   jprogBar1.setMaximum(100);									
			   jprogBar1.setValue(0);									
			   jprogBar1.setStringPainted(true);			
 			   jprogBar1.setIndeterminate(false);
			   this.add(jprogBar1);
            return this;
         }
         public JPanel createBars2()
         {
            createBars = new JPanel();
			   createBars.add(new JLabel("Progres Bar 2: ", SwingConstants.RIGHT));
			   jprogBar2.setOrientation(JProgressBar.HORIZONTAL);
			   jprogBar2.setMinimum(0);							
			   jprogBar2.setMaximum(100);									
			   jprogBar2.setValue(0);									
			   jprogBar2.setStringPainted(true);			
 			   jprogBar2.setIndeterminate(false);
			   createBars.add(jprogBar2);
            return createBars;
         }
//          public JPanel createBars3()
//          {
//             createBars2 = new JPanel();
// 			   createBars2.add(new JLabel("Progres Bar 3: ", SwingConstants.RIGHT));
// 			   jprogBar3.setOrientation(JProgressBar.HORIZONTAL);
// 			   jprogBar3.setMinimum(0);							
// 			   jprogBar3.setMaximum(100);									
// 			   jprogBar3.setValue(0);									
// 			   jprogBar3.setStringPainted(true);			
//  			   jprogBar3.setIndeterminate(false);
// 			   createBars2.add(jprogBar3);
//             return createBars2;
//          }
      }
      
      // Adds JPanel to JFrame
      InnerProgress program = new InnerProgress();
      add(program.createBars1());
      add(program.createBars2());
//       add(program.createBars3());
      MyProgessBar1 mpb1 = new MyProgessBar1();
      MyProgessBar2 mpb2 = new MyProgessBar2();  
//       MyProgessBar3 mpb3 = new MyProgessBar3();

      // Performs Action when Start Button is clicked     
		startButton.addActionListener(new ActionListener()
      {
			public void actionPerformed(ActionEvent ae) 
         {
            // Disables the button, starts the thread and when finished, joins other thread and prints message
            if(start == 0)
            {
               startButton.setEnabled(false);
               Thread extra = new Thread()
               {
                  public void run()
                  {
                     try
                     {
                        mpb1.join();
                        mpb2.join();
//                         mpb3.join();
                     }
                     catch(InterruptedException ie)
                     {
                     
                     }
                     startButton.setEnabled(true);
                     System.out.println("Main finished, count = " + counter);
                  }
               };
               extra.start();
               mpb1.start();
               mpb2.start();
//                mpb3.start();
               start++;
            }
            // Announce who is the winner
            else if(start == 1)
            {
               if(winner == "Thread A")
               {
                  System.out.println(winner + " finished first.");
               }
               else if(winner == "Thread B")
               {
                  System.out.println(winner + " finished first.");
               }
//                else if(winner == "Thread C")
//                {
//                   System.out.println(winner + " finished first.");
//                }
            }
			}
		});  
   }
   
   // Below code are protected class of Threads for ProgressBar
	protected class MyProgessBar1 extends Thread 
   {
		public void run() 
      {
         int i = 0;
			jprogBar1.setString(null); // Removes string, allows for auto updates
			jprogBar1.setStringPainted(true);
			jprogBar1.setIndeterminate(false);
         System.out.println("This ran Thread A");
			for(i = 0; i <=100; i++) 
         {
            if(keepGoing)
            {
				   jprogBar1.setValue(i);	// Change the progress bar's value
				   try
               {
                  Thread.sleep((long) (Math.random() * 100));
               }
				   catch(InterruptedException ie)
               {
					   System.out.println("Sleep interruption exception: " + ie.getMessage());
				   }
            }
			}
         if(keepGoing)
         {
            winner = "Thread A";
         } 
         keepGoing = false;
			jprogBar1.setStringPainted(true);
         counter++;
      }
   }

	protected class MyProgessBar2 extends Thread 
   {
		public void run() 
      {
         int i = 0;
			jprogBar2.setString(null); // Removes string, allows for auto updates
			jprogBar2.setStringPainted(true);
			jprogBar2.setIndeterminate(false);
         System.out.println("This ran Thread B");
			for(i = 0; i <=100; i++) 
         {
            if(keepGoing)
            {
				   jprogBar2.setValue(i); // Change the progress bar's value
				   try
               {
                  Thread.sleep((long) (Math.random() * 100));
               }
				   catch(InterruptedException ie)
               {
					   System.out.println("Sleep interruption exception: " + ie.getMessage());
				   }
            }
			}
         if(keepGoing)
         {
            winner = "Thread B";
         }  
         keepGoing = false;
			jprogBar2.setStringPainted(true);
         counter++;
      }
   }
// 	protected class MyProgessBar3 extends Thread 
//    {
// 		public void run() 
//       {
//          int i = 0;
// 			jprogBar3.setString(null); // Removes string, allows for auto updates
// 			jprogBar3.setStringPainted(true);
// 			jprogBar3.setIndeterminate(false);
//          System.out.println("This ran Thread C");
// 			for(i = 0; i <=100; i++) 
//          {
//             if(keepGoing)
//             {
// 				   jprogBar3.setValue(i); // Change the progress bar's value
// 				   try
//                {
//                   Thread.sleep((long) (Math.random() * 100));
//                }
// 				   catch(InterruptedException ie)
//                {
// 					   System.out.println("Sleep interruption exception: " + ie.getMessage());
// 				   }
//             }
// 			}
//          if(keepGoing)
//          {
//             winner = "Thread C";
//          }  
//          keepGoing = false;
// 			jprogBar3.setStringPainted(true);
//          counter++;
//       }
//    }

   // Main Program, creates GUI and starts the program.     
   public static void main(String[] args)
   {
      Lab4 jfMain = new Lab4();
		jfMain.pack();
		jfMain.setSize((int)(jfMain.getWidth() * 1.25), jfMain.getHeight());
      jfMain.setDefaultCloseOperation(EXIT_ON_CLOSE);
      jfMain.setLocationRelativeTo(null);																	
      jfMain.setVisible(true);    
   }
}