// Name: Chevy Mac
// Course: ISTE-121-01
// HW: #2
// Description: GUI from Hell
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HellGUI extends JFrame implements ActionListener 
{
   // Declarations
   private JMenuItem mItemStart = null;
   private JMenuItem mItemEnd = null;
   private JMenuItem mItemExit = null;
   private JMenuItem mItemAbout = null;
   private JButton jbCheck = null;
   private JButton jbSubmit = null;
   private JButton jbReset = null;
	private JButton jbExit = null;
   private JCheckBox jcbPositive = null;
   private JCheckBox jcbNegative = null;
   private JCheckBox jcbNetural = null;
   private JRadioButton jrbAdd = null;
   private JRadioButton jrbSubtract = null;
   private JTextArea textArea;
   int value = 0;
   int choiceValue = 0;
   int checkValue = 0;

   public HellGUI()
   {
      JPanel Hell = new JPanel(); // Creates JPanel
      setTitle("Hell GUI - Progress " + value + "%"); // Sets Title for JFrame
      Hell.setLayout(new BorderLayout());
      add(Hell);
      
      // Creates JTextArea and is postioned in center of JFrame.
      textArea = new JTextArea(10, 35);
      textArea.setEditable(false);
      add(textArea, BorderLayout.CENTER);
      JScrollPane scroll = new JScrollPane(textArea);
      add(scroll, "Center"); 
      
      // Creates Menu Bar
      JMenuBar mBar = new JMenuBar();
      setJMenuBar(mBar);
      JMenu mFile = new JMenu("File");
      mFile.setMnemonic('F');
      mBar.add(mFile);
      mItemStart = new JMenuItem("Start");
      mItemStart.setMnemonic('S');
      mFile.add(mItemStart);
      mItemEnd = new JMenuItem("End");
      mItemEnd.setMnemonic('E');
      mFile.add(mItemEnd);
      mItemExit = new JMenuItem("Exit");
      mItemExit.setMnemonic('E');
      mFile.add(mItemExit);
      JMenu mHelp = new JMenu("Help");
      mHelp.setMnemonic('H');
      mBar.add(mHelp);
      mItemAbout = new JMenuItem("About");
      mItemAbout.setMnemonic('A');
      mHelp.add(mItemAbout);
      mItemAbout.addActionListener(this);
      
      // Creates Radio Buttons and is postioned on either East or West of JFrame.
      JPanel radioSelections = new JPanel(new GridLayout(0, 1));
      radioSelections.add( new JLabel("Add/Subtract"));
      jrbAdd = new JRadioButton("Add");
      jrbSubtract = new JRadioButton("Subtract");
      jrbAdd.setEnabled(false);
      jrbSubtract.setEnabled(false);
      ButtonGroup buttons = new ButtonGroup();
      buttons.add(jrbAdd);
      buttons.add(jrbSubtract);
      radioSelections.add(jrbAdd);
      radioSelections.add(jrbSubtract);
      jrbAdd.addActionListener(this);
		jrbSubtract.addActionListener(this);
      //add(radioSelections, BorderLayout.WEST);
      
      // Creates Checkbox Buttons and is postioned on either East or West of JFrame.
      JPanel checkSelections = new JPanel(new GridLayout(0, 1));
      checkSelections.add(new JLabel("Signs"));
      jcbPositive = new JCheckBox("Positive");
      jcbNegative = new JCheckBox("Negative");
      jcbNetural = new JCheckBox("Netural");
      jcbPositive.setEnabled(false);
      jcbNegative.setEnabled(false);
      jcbNetural.setEnabled(false);
      checkSelections.add(jcbPositive);
      checkSelections.add(jcbNegative);
      checkSelections.add(jcbNetural);
      jcbPositive.addActionListener(this);
		jcbNegative.addActionListener(this);
		jcbNetural.addActionListener(this);      
      //add(checkSelections, BorderLayout.EAST);
      
      // Creates JButtons and position the buttons on either North or South of JFrame.
      JPanel buttonData = new JPanel(new FlowLayout());																	
      jbCheck = new JButton("Check");
		jbSubmit = new JButton("Submit");
      jbReset = new JButton("Reset");
		jbExit = new JButton("Exit");
      jbCheck.setEnabled(false);
      jbSubmit.setEnabled(false);
      jbReset.setEnabled(false);
      jbExit.setEnabled(false);
      buttonData.add(jbCheck);
      buttonData.add(jbSubmit);
      buttonData.add(jbReset);
      buttonData.add(jbExit);
      jbCheck.addActionListener(this);
		jbSubmit.addActionListener(this);
      jbReset.addActionListener(this);
		jbExit.addActionListener(this);
      jbCheck.setToolTipText("Check button only works after a certain requirement is met.");
      jbSubmit.setToolTipText("Submit what? Figure it out");
      jbReset.setToolTipText("Reset everything or is it?");
      jbExit.setToolTipText("Exits the program that is if you can unlock it by clearing the riddle.");
      //add(buttonData, BorderLayout.SOUTH);
      
      //Random Position of JPanel in JFrame on each startup
      int randomSet1 = (int) Math.round(Math.random() * 100);
      int randomSet2 = (int) Math.round(Math.random() * 100);
      if(randomSet1 >= 50)
      {
         add(buttonData, BorderLayout.NORTH);
      }
      else if(randomSet1 < 50)
      {
         add(buttonData, BorderLayout.SOUTH);
      }
      if(randomSet2 >= 50)
      {
         add(radioSelections, BorderLayout.EAST);
         add(checkSelections, BorderLayout.WEST);
      }
      else if(randomSet2 < 50)
      {
         add(radioSelections, BorderLayout.WEST);
         add(checkSelections, BorderLayout.EAST);
      }
      
      // Inner Class for Menu, as well as enabling all buttons at once, upon clicking one of menus.
      HellListener enableHell = new HellListener(mItemStart, mItemEnd, mItemExit, textArea, 
         jbCheck, jbSubmit, jbReset, jcbPositive, jcbNegative, jcbNetural, jrbAdd, jrbSubtract);
   }

   // Performs Action when button is clicked.
   public void actionPerformed(ActionEvent ae)
	{
      Object choice = ae.getSource();
      String sChoice = ae.getActionCommand();
      int remainingValue = 100 - value;
      // Check Button [JButton]
      if(choice == jbCheck)
      {
         if(value == 99)
         {
            value = value + 1;
            setTitle("Hell GUI - Progress " + value + "%");
         }
         else
         {
            textArea.append("Check what? Figure this button out!\n");
         }
      }
      // Submit Button [JButton]
      else if(choice == jbSubmit)
      {
         if(value == 0)
         {
            textArea.append("Try again, figure the riddle of submit button.\n");
         }
         else
         {
            textArea.append("Progress: " + value + "%. Remaining: " + remainingValue + "%\n");
         }
      }
      // Reset Button [JButton]
      else if(choice == jbReset)
      {
         textArea.append("No resetting! Oh oops, it did halfway reset.\n");
         if(value <= 5)
         {
            value = (int) Math.round(Math.random() * 2);
         }
         else if(value > 60)
         {
            value = (int) Math.round(Math.random() * 30);
         }
         else
         {
            value = (int) Math.round(Math.random() * 10);
         }
         setTitle("Hell GUI - Progress " + value + "%");
      }
      // Exit Button [JButton]
      else if(choice == jbExit)
      {
         System.exit(0);
      }
      // Positive Checkbox - Increases then decreases the value by random (large amount)
      else if(sChoice == "Positive")
      {
         if(choiceValue == 0)
         {
            jcbNegative.setEnabled(false);
            jcbNetural.setEnabled(false);
            choiceValue = 1;
            value = value + (int) Math.round(Math.random() * 7);
            if(value > 99)
            {
               value = 99;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else if(value <= 0)
            {
               value = 0;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else
            {
               setTitle("Hell GUI - Progress " + value + "%");
            }
         }
         else if(choiceValue == 1)
         {
            jcbNegative.setEnabled(true);
            jcbNetural.setEnabled(true);
            choiceValue = 0;
            value = value + (int) Math.round(Math.random() * -4);
            if(value > 99)
            {
               value = 99;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else if(value <= 0)
            {
               value = 0;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else
            {
               setTitle("Hell GUI - Progress " + value + "%");
            }
         }
      }
      // Negative Checkbox - Decreases then increases the value by random (Big amount)
      else if(sChoice == "Negative")
      {
         if(choiceValue == 0)
         {
            jcbPositive.setEnabled(false);
            jcbNetural.setEnabled(false);
            choiceValue = 1;
            value = value + (int) Math.round(Math.random() * -10);
            if(value > 99)
            {
               value = 99;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else if(value <= 0)
            {
               value = 0;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else
            {
               setTitle("Hell GUI - Progress " + value + "%");
            }
         }
         else if(choiceValue == 1)
         {
            jcbPositive.setEnabled(true);
            jcbNetural.setEnabled(true);
            choiceValue = 0;
            value = value + (int) Math.round(Math.random() * 2);
            if(value > 99)
            {
               value = 99;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else if(value <= 0)
            {
               value = 0;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else
            {
               setTitle("Hell GUI - Progress " + value + "%");
            }
         }
      }
      // Netural CheckBox - Increases then decreases the value by random (small amount)
      else if(sChoice == "Netural")
      {
         if(choiceValue == 0)
         {
            jcbPositive.setEnabled(false);
            jcbNegative.setEnabled(false);
            choiceValue = 1;
            value = value + (int) Math.round(Math.random() * 1);
            if(value > 99)
            {
               value = 99;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else if(value <= 0)
            {
               value = 0;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else
            {
               setTitle("Hell GUI - Progress " + value + "%");
            }
         }
         else if(choiceValue == 1)
         {
            jcbPositive.setEnabled(true);
            jcbNegative.setEnabled(true);
            choiceValue = 0;
            value = value + (int) Math.round(Math.random() * -2);
            if(value > 99)
            {
               value = 99;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else if(value <= 0)
            {
               value = 0;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else
            {
               setTitle("Hell GUI - Progress " + value + "%");
            }
         }
      }
      // Add Button [RadioButton]
      else if(sChoice == "Add")
      { 
         if(checkValue == 0)
         {
            value = value + (int) Math.round(Math.random() * -5);
            if(value > 99)
            {
               value = 99;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else if(value <= 0)
            {
               value = 0;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else
            {
               setTitle("Hell GUI - Progress " + value + "%");
            }
            checkValue = 1;
         }
      }
      // Subtract Button [RadioButton]
      else if(sChoice == "Subtract")
      {
         if(checkValue == 1)
         {
            value = value + (int) Math.round(Math.random() * 5);
            if(value > 99)
            {
               value = 99;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else if(value <= 0)
            {
               value = 0;
               setTitle("Hell GUI - Progress " + value + "%");
            }
            else
            {
               setTitle("Hell GUI - Progress " + value + "%");
            }
            checkValue = 0;
         }
      }
      // About - Shows Message Dialog
      else if(choice == mItemAbout)
      {
         JOptionPane.showMessageDialog(null, "Solve this applicaton's riddle to able to exit this program. Go to File > Start to begin or is it?");
      }
      if(value >= 100)
      {
         jbExit.setEnabled(true);
         JOptionPane.showMessageDialog(null, "Congratulations! You have solved this puzzle, press Exit to exit this program.");
      }
   }
   
   // Main Program - Creates GUI
   public static void main(String[] args)
   {															
      HellGUI jfMain = new HellGUI();
		jfMain.pack();
      jfMain.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      jfMain.setLocationRelativeTo(null);																	
      jfMain.setVisible(true); 
   } 
}