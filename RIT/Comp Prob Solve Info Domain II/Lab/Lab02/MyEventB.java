// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #2
// Description: Handling Events, Menus & Text Areas
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyEventB extends JFrame implements ActionListener
{

   private JTextField currentValue = null;
   private JButton jbPlus = null;
   private JButton jbMinus = null;
   private JButton jbReset = null;
	private JButton jbQuit = null;
   int value = 0;

   public MyEventB()
   {
      JPanel EventB = new JPanel();
      setTitle("Part 3 Using getActionCommand");
      EventB.setLayout(new BorderLayout());
		add(EventB);
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
      jbReset.addActionListener(this);
		jbQuit.addActionListener(this);
      add(buttonData, BorderLayout.SOUTH);
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
      else if(sChoice == "Reset")
      {
         currentValue.setText("0");
         value = 0;
      }
      else if(sChoice == "Quit")
      {
         System.exit(0);
      }
   }

   public static void main(String[] args)
   {															
      MyEventB jfMain = new MyEventB();
		jfMain.pack();
      jfMain.setDefaultCloseOperation(EXIT_ON_CLOSE);
      jfMain.setLocationRelativeTo(null);																	
      jfMain.setVisible(true); 
   } 
}


