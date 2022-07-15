// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #2
// Description: Handling Events, Menus & Text Areas
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyMenu extends JFrame
{
   private JMenuItem mItemInc = null;
   private JMenuItem mItemDec = null;
   private JMenuItem mItemReset = null;
   private JMenuItem mItemExit = null;
   int value = 0;
   
   public MyMenu()
   {
      JPanel Menu = new JPanel();
      setTitle("My Menu");
      Menu.setLayout(new BorderLayout());
      add(Menu);
      JTextArea textArea = new JTextArea(20, 15);
      textArea.setEditable(false);
      add(textArea);
      JScrollPane scroll = new JScrollPane(textArea);
      add(scroll, "Center"); 
      JMenuBar mBar = new JMenuBar();
      setJMenuBar(mBar);
      JMenu mFile = new JMenu("Count");
      mBar.add(mFile);
      mItemInc = new JMenuItem("Inc");
      mFile.add(mItemInc);
      mItemDec = new JMenuItem("Dec");
      mFile.add(mItemDec);
      mItemReset = new JMenuItem("Reset");
      mFile.add(mItemReset);
      mItemExit = new JMenuItem("Exit");
      mFile.add(mItemExit);
      MyListenerCnt menuBar = new MyListenerCnt(mItemInc, mItemDec, mItemReset, mItemExit, value, textArea);
   }

   public static void main(String[] args)
   {															
      MyMenu jfMain = new MyMenu();
		jfMain.pack();
      jfMain.setDefaultCloseOperation(EXIT_ON_CLOSE);
      jfMain.setLocationRelativeTo(null);																	
      jfMain.setVisible(true); 
   } 
}


