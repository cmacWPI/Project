import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyListenerCnt implements ActionListener 
{
   private JMenuItem mItemInc;
   private JMenuItem mItemDec;
   private JMenuItem mItemReset;
   private JMenuItem mItemExit;
   private int value;
   private JTextArea textArea;
   
   public MyListenerCnt(JMenuItem _mItemInc, JMenuItem _mItemDec, JMenuItem _mItemReset, JMenuItem _mItemExit, int _value, JTextArea _textArea)
   {
      mItemInc = _mItemInc;
      mItemDec = _mItemDec;
      mItemReset = _mItemReset;
      mItemExit = _mItemExit;
      value = _value;
      textArea = _textArea;
      mItemInc.addActionListener(this);
      mItemDec.addActionListener(this);
      mItemReset.addActionListener(this);
      mItemExit.addActionListener(this);
   }

   public void actionPerformed(ActionEvent e)
   {
      String sChoice = e.getActionCommand();
      if(sChoice == "Inc")
      {
         value++;
         textArea.append(Integer.toString(value) + "\n");
      }
      else if(sChoice == "Dec")
      {
         value--;
         textArea.append(Integer.toString(value) + "\n");
      }
      else if(sChoice == "Reset")
      {
         value = 0;
         textArea.append(Integer.toString(value) + "\n");
      }
      else if(sChoice == "Exit")
      {
         System.exit(0);
      }     
   }
}