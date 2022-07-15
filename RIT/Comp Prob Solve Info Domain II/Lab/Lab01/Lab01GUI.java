// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #1
// Description: GUI Applications with I/O
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Lab01GUI extends JFrame implements ActionListener 
{
   // Declarations
   private JTextField jtfFirst = null;
	private JTextField jtfSecond = null;
	private JTextField jtfThird = null;
	private JTextField jtfFourth = null;
   private JButton jbButton1 = null;
   private JButton jbButton2 = null;
   private JButton jbButton3 = null;
	private JButton jbButton4 = null;


   public Lab01GUI()
   {
      JPanel Orders = new JPanel();
      setTitle("Chevy's Item Orders Calculator");
      Orders.setLayout(new GridLayout(4, 2, 4, 4));
																			
      JLabel jlFirst = new JLabel("Item name: ", SwingConstants.RIGHT);
      Orders.add(jlFirst);
      jtfFirst = new JTextField();
      Orders.add(jtfFirst);														
      JLabel jlSecond = new JLabel("Number of: ", SwingConstants.RIGHT);
      Orders.add(jlSecond);	
      jtfSecond = new JTextField();
      Orders.add(jtfSecond);
      JLabel jlThird = new JLabel("Cost: ", SwingConstants.RIGHT);
      Orders.add(jlThird);	
      jtfThird = new JTextField();
      Orders.add(jtfThird);
      JLabel jlFourth = new JLabel("Amount owed: ", SwingConstants.RIGHT);
      Orders.add(jlFourth);	
      jtfFourth = new JTextField();
      jtfFourth.setEnabled(false);
      Orders.add(jtfFourth);
      
      add(Orders);
      
      JPanel jpSouth = new JPanel(new FlowLayout());
      jbButton1 = new JButton("Calculate");
		jbButton2 = new JButton("Save");
      jbButton3 = new JButton("Clear");
		jbButton4 = new JButton("Exit");
      jpSouth.add(jbButton1);
      jpSouth.add(jbButton2);
      jpSouth.add(jbButton3);
      jpSouth.add(jbButton4);
      jbButton1.addActionListener(this);
		jbButton2.addActionListener(this);
      jbButton3.addActionListener(this);
		jbButton4.addActionListener(this);
      add(jpSouth, BorderLayout.SOUTH);
   }
   
   public void actionPerformed(ActionEvent ae)
	{
      BufferedWriter writeData = null;
      Object choice = ae.getSource();
      String sChoice = ae.getActionCommand();
      
      // Calcuate Button
      if(choice == jbButton1)
		{
			int NumberOf = Integer.parseInt(jtfSecond.getText());
			int Cost = Integer.parseInt(jtfThird.getText());
			double finalCalc = NumberOf * Cost;
         jtfFourth.setText(String.format("%.2f", finalCalc));
      }
      
      // Save Button (Saves the data to csv file)
      else if(choice == jbButton2)
		{
			int NumberOf = Integer.parseInt(jtfSecond.getText());
			int Cost = Integer.parseInt(jtfThird.getText());
			double finalCalc = NumberOf * Cost;
         jtfFourth.setText(String.format("%.2f", finalCalc));
         
		   File file = new File("121Lab1.csv");  
        	if(!file.exists())
			{         
            try
            {
               file.createNewFile(); 
            }
            catch(IOException ioe)
            {
               System.out.println("Error, cannot create new file");
            }
			}
         try
         {
		      writeData = new BufferedWriter(new FileWriter(file,true));
         }
         catch(IOException ioe)
         {
            System.out.println("Error opening the file for writing");
         }
          //Try to write the data to csv file
         try
         {
            writeData.write("\"" + jtfFirst.getText() + "\"");      
			   writeData.write(",");
			   writeData.write(jtfSecond.getText());      
			   writeData.write(",");
            writeData.write(jtfThird.getText());
            writeData.write(",");
            writeData.write(jtfFourth.getText());
            writeData.write(",\n"); 
         }
         catch(IOException ioe)
         {
            System.out.println("Error writing to the file");
         }
         //Try to close the file
         try
         {
            writeData.close();
         }
         catch(IOException ioe)
         {
            System.out.println("Error closing the file.");
         }
      }
      // Clear Button - Clears the data field
      else if(choice == jbButton3)
		{
         jtfFirst.setText(null);
			jtfSecond.setText(null);
			jtfThird.setText(null);
			jtfFourth.setText(null);
      }
      // Exit Button - Exits the Program
      else
      {
         System.exit(0);
      }
   }

   public static void main(String[] args)
   {															
      Lab01GUI jfMain = new Lab01GUI();
		jfMain.pack();
		jfMain.setSize((int)(jfMain.getWidth() * 1.25), (int)(jfMain.getHeight() * 1.05));
      jfMain.setDefaultCloseOperation(EXIT_ON_CLOSE);
      jfMain.setLocationRelativeTo(null);																	
      jfMain.setVisible(true); 
   } 
}


