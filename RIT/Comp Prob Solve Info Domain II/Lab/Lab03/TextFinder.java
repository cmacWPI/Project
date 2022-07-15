// Name: Chevy Mac
// Coursse: ISTE-121-01
// Lab: #3
// Description: GUI w/ Inner classes & Binary
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
public class TextFinder extends WindowAdapter
{
   private JTextArea textArea;
   private JTextField jtfFind = null;
   private JButton jbFind;
   private JButton jbClear;
   private String findText = null;
   public TextFinder() 
   {
      JFrame findFrame = new JFrame();
      JPanel Finder = new JPanel(); // Creates JPanel
      findFrame.setTitle("Find"); // Sets Title for JFrame
      Finder.setLayout(new BorderLayout());
      findFrame.add(Finder);
      findFrame.addWindowListener(this);
      JPanel FieldText = new JPanel(new GridLayout(1,1)); 
      textArea = new JTextArea(10, 35);
      FieldText.add(textArea);
      JScrollPane scroll = new JScrollPane(textArea);
      FieldText.add(scroll, "Center");
      findFrame.add(FieldText, BorderLayout.NORTH);
      
      
      JPanel FindText = new JPanel(new FlowLayout());      
      jtfFind = new JTextField(15);
      FindText.add(new JLabel("Find", JLabel.CENTER));
      FindText.add(jtfFind); 
      findFrame.add(FindText, BorderLayout.CENTER);

      JPanel FindButton = new JPanel(new FlowLayout());
      jbFind = new JButton("Find");
		jbClear = new JButton("Clear");
      FindButton.add(jbFind);
      FindButton.add(jbClear);
      findFrame.add(FindButton, BorderLayout.SOUTH);
         
      class FindButton
      {
         public void FindFunction()
         {
		      jbFind.addActionListener(new ActionListener()	
            {
			      public void	actionPerformed(ActionEvent ae)
			      {
                  findText = jtfFind.getText();
                  highlight(textArea, findText);
                  textArea.requestFocusInWindow();
				   }
		      }
            ); 
         }
      }
      
		jbClear.addActionListener(new ActionListener()	
      {
			public void	actionPerformed(ActionEvent ae)
			{
            Highlighter remove = textArea.getHighlighter();
            remove.removeAllHighlights();
		   }
		}
      );      
      
      FindButton functionFind = new FindButton();
      
      functionFind.FindFunction();
      
		findFrame.pack();
      findFrame.setLocationRelativeTo(null);																	
      findFrame.setVisible(true); 
   }
   
   public void highlight(JTextComponent textField, String findString) 
   {
      Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
      try
      {
         Highlighter find = textField.getHighlighter();
         Document doc = textField.getDocument();
         String text = doc.getText(0, doc.getLength());
         int pos = 0;
         int found = 0;
         
         if(text.indexOf(findString) != -1)
         {
            pos = text.indexOf(findString, pos);
            find.addHighlight(pos, pos + findString.length(), painter);
            found = 1;
         }
         if(found == 0)
         {
            JOptionPane.showMessageDialog(null, "No Match Found.");
         }
      }
      catch(BadLocationException e)
      {
         e.printStackTrace();
      }
   }   	
   
 	public void windowClosing(WindowEvent e) 
   {
      System.out.println("Thank you for using finder");
      System.exit(0);		 	
   }
  
   // Main Program - Creates GUI
   public static void main(String[] args)
   {															
      TextFinder jfMain = new TextFinder();
   } 
}