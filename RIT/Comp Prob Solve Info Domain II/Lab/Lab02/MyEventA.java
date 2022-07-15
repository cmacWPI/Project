// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #2
// Description: Handling Events, Menus & Text Areas
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyEventA extends JFrame implements ActionListener
{

   private JTextField currentValue = null;
   private JButton jbPlus = null;
   private JButton jbMinus = null;
   private JButton jbReset = null;
	private JButton jbQuit = null;
   int value = 0;

   public MyEventA()
   {
      JPanel EventA = new JPanel();
      setTitle("Part 2 Using getSource");
      EventA.setLayout(new BorderLayout());
		add(EventA);
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
      if(e.getSource() == jbPlus)
      {
         value++;
         currentValue.setText(Integer.toString(value));
      }
      else if(e.getSource() == jbMinus)
      {
         value--;
         currentValue.setText(Integer.toString(value));
      }
      else if(e.getSource() == jbReset)
      {
         currentValue.setText("0");
         value = 0;
      }
      else if(e.getSource() == jbQuit)
      {
         System.exit(0);
      }
   }

   public static void main(String[] args)
   {															
      MyEventA jfMain = new MyEventA();
		jfMain.pack();
      jfMain.setDefaultCloseOperation(EXIT_ON_CLOSE);
      jfMain.setLocationRelativeTo(null);																	
      jfMain.setVisible(true); 
   } 
}


