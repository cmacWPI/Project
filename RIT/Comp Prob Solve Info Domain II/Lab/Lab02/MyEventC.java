// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #2
// Description: Handling Events, Menus & Text Areas
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyEventC extends JFrame implements ActionListener
{

   private JTextField currentValue = null;
   private JButton jbPlus = null;
   private JButton jbMinus = null;
   private JButton jbReset = null;
	private JButton jbQuit = null;
   int value = 0;

   public MyEventC()
   {
      JPanel EventC = new JPanel();
      setTitle("Part 4 Using seperate class for Reset Button");
      EventC.setLayout(new BorderLayout());
		add(EventC);
      JPanel valueData = new JPanel(new FlowLayout());																	
      JLabel data = new JLabel("Current Value: ");
      valueData.add(data);
      currentValue = new JTextField(5);
      valueData.add(currentValue);
      currentValue.setText("0");
      add(valueData, BorderLayout.NORTH);
      
      
      JPanel buttonData = new JPanel(new FlowLayout());																	
      jbPlus = new JButton("+");
		jbMinus = new JButton("-");
      jbReset = new JButton("Reset");
		jbQuit = new JButton("Quit");
      buttonData.add(jbPlus);
      buttonData.add(jbMinus);
      buttonData.add(jbReset);
      buttonData.add(jbQuit);
      jbPlus.addActionListener(this);
		jbMinus.addActionListener(this);
		jbQuit.addActionListener(this);
      add(buttonData, BorderLayout.SOUTH);
      ResetListener reset = new ResetListener(jbReset, value, currentValue);
      jbReset.addActionListener(reset);
   }

   public void actionPerformed(ActionEvent e)
   {
      String sChoice = e.getActionCommand();
      if(sChoice == "+")
      {
         value++;
         currentValue.setText(Integer.toString(value));
      }
      else if(sChoice == "-")
      {
         value--;
         currentValue.setText(Integer.toString(value));
      }
      else if(sChoice == "Quit")
      {
         System.exit(0);
      }
   }

   public static void main(String[] args)
   {															
      MyEventC jfMain = new MyEventC();
		jfMain.pack();
      jfMain.setDefaultCloseOperation(EXIT_ON_CLOSE);
      jfMain.setLocationRelativeTo(null);																	
      jfMain.setVisible(true); 
   } 
}


