// Name: Chevy Mac
// Course: ISTE-121-01
// HW: #2
// Description: GUI from Hell (Inner Class for HellGUI.java)
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class HellListener implements ActionListener 
{
   // Declarations
   private JMenuItem mItemStart;
   private JMenuItem mItemEnd;
   private JMenuItem mItemExit;
   private JButton jbCheck;
   private JButton jbSubmit;
   private JButton jbReset;
   private JCheckBox jcbPositive;
   private JCheckBox jcbNegative;
   private JCheckBox jcbNetural;
   private JRadioButton jrbAdd;
   private JRadioButton jrbSubtract;
   private int value = 0;
   private JTextArea textArea;
   
   // Constructors
   public HellListener(JMenuItem _mItemStart, JMenuItem _mItemEnd, JMenuItem _mItemExit, JTextArea _textArea, 
   JButton _jbCheck, JButton _jbSubmit, JButton _jbReset, JCheckBox _jcbPositive, JCheckBox _jcbNegative, 
   JCheckBox _jcbNetural, JRadioButton _jrbAdd, JRadioButton _jrbSubtract)
   {
      mItemStart = _mItemStart;
      mItemEnd = _mItemEnd;
      mItemExit = _mItemExit;
      textArea = _textArea;
      jbCheck = _jbCheck;
      jbSubmit = _jbSubmit;
      jbReset = _jbReset;
      jcbPositive = _jcbPositive;
      jcbNegative = _jcbNegative;
      jcbNetural = _jcbNetural;
      jrbAdd = _jrbAdd;
      jrbSubtract = _jrbSubtract;      
      mItemStart.addActionListener(this);
      mItemEnd.addActionListener(this);
      mItemExit.addActionListener(this);
   }

   public void actionPerformed(ActionEvent e)
   {
      String sChoice = e.getActionCommand();
      // Start
      if(sChoice == "Start")
      {
         if(value == 0)
         {
            textArea.append("Try again.\n");
         }
         else
         {
            textArea.append("This program is already started.\n");
         }
      }
      // Upon first click, all buttons will be enabled, including check and raido buttons.
      else if(sChoice == "End")
      {
         if(value == 0)
         {
            jbCheck.setEnabled(true);
            jbSubmit.setEnabled(true);
            jbReset.setEnabled(true);
            jcbPositive.setEnabled(true);
            jcbNegative.setEnabled(true);
            jcbNetural.setEnabled(true); 
            jrbAdd.setEnabled(true);
            jrbSubtract.setEnabled(true); 
            textArea.append("So you have started it.\n");
            value = 1;
         }
         else
         {
            if(value == 1)
            {
               textArea.append("Are you trying to get out of this program? Not going to happen!\n");
            }
            else
            {
               textArea.append("Again? Still not happening!\n");
            }
            value = value + 15;
         }
      }
      // Exit
      else if(sChoice == "Exit")
      {
         if(value == 31)
         {
            System.exit(0);
         }
         else if(value == 16)
         {
            textArea.append("Again? Still not happening!\n");
         }
         else if(value < 16)
         {
            textArea.append("Exiting this program is impossible, until you solve the riddle.\n");
         }
      }     
   }
}