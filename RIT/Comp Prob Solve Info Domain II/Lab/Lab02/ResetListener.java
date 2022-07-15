import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ResetListener implements ActionListener 
{
   private JButton jbReset;
   private int value;
   private JTextField currentValue;
   
   public ResetListener(JButton _jbReset, int _value, JTextField _currentValue)
   {
      jbReset = _jbReset;
      value = _value;
      currentValue = _currentValue;
   }

   public void actionPerformed(ActionEvent e)
   {
      String sChoice = e.getActionCommand();
      if(sChoice == "Reset")
      {
         currentValue.setText("0");
         value = 0;
      }
   }
}