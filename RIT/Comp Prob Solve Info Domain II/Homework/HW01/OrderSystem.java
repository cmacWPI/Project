// Name: Chevy Mac
// Course: ISTE-121-01
// HW: #1
// Description: GUI Applications with I/O (With continuation from Lab #1)
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class OrderSystem extends JFrame implements ActionListener 
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
   private JButton jbButton5 = null;
   private JButton jbButton6 = null;
   private JButton jbButton7 = null;
   String[] arrayStrings = null;
   int counter = 0;

   public OrderSystem()
   {
      JPanel Orders = new JPanel(); //Creates JPanel
      setTitle("Chevy's Item Orders Calculator"); // Sets Title for JFrame
      
      // Creates GridLayout to put JLabel and JTextField in
      Orders.setLayout(new GridLayout(0, 2, 5, 5)); 
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
      jtfFourth.setEnabled(false); // Disables input for [Amount owned]
      Orders.add(jtfFourth);
      add(Orders, BorderLayout.NORTH);

      // Creates FlowLayout for buttons and is postioned in south of JFrame
      JPanel jpSouth = new JPanel();
      jpSouth.setLayout(new FlowLayout());
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
      add(jpSouth, BorderLayout.CENTER);
      
      JPanel jpSouth2 = new JPanel();
      jpSouth2.setLayout(new FlowLayout());
      jbButton5 = new JButton("Load");
      jbButton6 = new JButton("<Prev");
      jbButton7 = new JButton("Next>");
      jpSouth2.add(jbButton5);
      jpSouth2.add(jbButton6);
      jpSouth2.add(jbButton7);
      jbButton5.addActionListener(this);
		jbButton6.addActionListener(this);
      jbButton7.addActionListener(this);
      add(jpSouth2, BorderLayout.SOUTH);
   }
   
   // Performs Action when button is clicked.
   public void actionPerformed(ActionEvent ae)
	{
      Object choice = ae.getSource();
      String sChoice = ae.getActionCommand();
      BufferedWriter writeData = null;
      BufferedReader readData = null;
      int i = 0;
      int record = -1;
      String finalString = "";
      
      // Calcuate Button - Calcuates the data based on input, has errors if input is not interger
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
            writeData.write("\n"); 
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
      else if(choice == jbButton4)
      {
         System.exit(0);
      }
      // Load Button - Loads all of data from csv file and put all into a single array.
      // Also displays the first record inside the file.
      else if(choice == jbButton5)
      {
         try
         {
		      readData = new BufferedReader(new FileReader("121Lab1.csv"));
            // Reads the first record of line
            String line = readData.readLine();
            // While the record is not null, it will keep going
            while((line) != null)
            {
               if(i == 0)
               {
                  finalString = finalString + line;
                  i++;
                  record = record + 2;
               }
               else
               {
                  finalString = finalString + "," + line;
                  record++;
               }
               line = readData.readLine();
            }
            if(record != -1)
            {
               arrayStrings = finalString.split(",");
               jtfFirst.setText(arrayStrings[0]);
			      jtfSecond.setText(arrayStrings[1]);
			      jtfThird.setText(arrayStrings[2]);
			      jtfFourth.setText(arrayStrings[3]);
               JOptionPane.showMessageDialog(null, "Total Number of Records: " + record);
            }
            else
            {
               JOptionPane.showMessageDialog(null, "Missing file or no data to read.");
            }
         }
         catch(IOException ioe)
         {
            JOptionPane.showMessageDialog(null, "Missing file or no data to read.");
         }
         //Try to close the file after reading
         try
         {
            readData.close();
         }
         catch(IOException ioe)
         {
            System.out.println("Error closing the file.");
         }
      }
      // <Prev Button - Loads the previous data of current record
      else if(choice == jbButton6)
      {
         if(counter >= 4)
         {
            counter = counter - 4;
            jtfFirst.setText(arrayStrings[counter]);
			   jtfSecond.setText(arrayStrings[counter + 1]);
			   jtfThird.setText(arrayStrings[counter + 2]);
			   jtfFourth.setText(arrayStrings[counter + 3]);
         }
         else
         {
            JOptionPane.showMessageDialog(null, "There is no more data in previous direction.");
         }
      }
      // Next> Button - Loads the next data of current record
      else if(choice == jbButton7)
      {
         if(counter < arrayStrings.length - 4)
         {
            counter = counter + 4;
            jtfFirst.setText(arrayStrings[counter]);
			   jtfSecond.setText(arrayStrings[counter + 1]);
			   jtfThird.setText(arrayStrings[counter + 2]);
			   jtfFourth.setText(arrayStrings[counter + 3]);
         }
         else
         {
            JOptionPane.showMessageDialog(null, "There is no more data in next direction.");
         }
      }
   }
   
   // Main Program - Creates GUI
   public static void main(String[] args)
   {															
      OrderSystem jfMain = new OrderSystem();
		jfMain.pack();
		jfMain.setSize((int)(jfMain.getWidth() * 1.25), jfMain.getHeight());
      jfMain.setDefaultCloseOperation(EXIT_ON_CLOSE);
      jfMain.setLocationRelativeTo(null);																	
      jfMain.setVisible(true); 
   } 
}