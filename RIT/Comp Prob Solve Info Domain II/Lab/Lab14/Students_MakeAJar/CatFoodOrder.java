import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
	Demo code for creating a Jar file from a GUI with a picture in it
	This is base code, need to make changes.
*/

public class CatFoodOrder extends JFrame implements ActionListener
{
	
	private JPanel jpCondiments, jpEntree;
	private JCheckBox jcbKetchup, jcbMustard, jcbPickles;
	private ButtonGroup bgEntree;
	//private JRadioButton jrbBeef, jrbChicken, jrbVeggie;
	private JComboBox jcbbEntree;
	private String []entrees = {"Tuna", "Chicken", "Salmon"};	// Items for combo box
	
	public CatFoodOrder() 
	{	
		//create a panel with label and combo box
		jpEntree = new JPanel();
		JLabel jlEntree = new JLabel("Select an entree");
		
		//combox box was list of entries in the parameter of type string
		jcbbEntree = new JComboBox(entrees);		// Add text to combo box
		jpEntree.add(jlEntree);
		jpEntree.add(jcbbEntree);

		//create a panel with check boxes
		jpCondiments = new JPanel();
		jcbKetchup = new JCheckBox("Milk");
		jcbMustard = new JCheckBox("Cream");
		jcbPickles = new JCheckBox("Water");
		jpCondiments.add(jcbKetchup);
		jpCondiments.add(jcbMustard);
		jpCondiments.add(jcbPickles);

		//create a panel with order button
		JPanel jpOrder = new JPanel();
		JButton jbOrder = new JButton("Place Order", new ImageIcon("images/cat.gif"));
		jpOrder.add(jbOrder);

		//set panels on JFrame
		add(jpEntree,     BorderLayout.NORTH);
		add(jpCondiments, BorderLayout.CENTER);
		add(jpOrder,      BorderLayout.SOUTH);

		//listener only for button which concludes order
		jbOrder.addActionListener(this);
	}

	//handle button press to place order
	public void actionPerformed(ActionEvent ae) 
	{
		StringBuffer sandwichOrder = new StringBuffer();
		
		//get the selected entree in combobox
		int intEntree = jcbbEntree.getSelectedIndex();		// how to find which # was selected
		
		sandwichOrder.append(entrees[intEntree] + " cat food with");	// index array to get text
		
		//get all selected condiments
		boolean atLeastOneCondiment = false;	//where any condiments selected	
		Component[] componentsOnPanel = jpCondiments.getComponents();
		for ( Component nextComp : componentsOnPanel ) 
		{
			JCheckBox cb = (JCheckBox) nextComp;
			if (cb.isSelected())
			{
		  		sandwichOrder.append(" " + cb.getText());
				atLeastOneCondiment = true;
			}
		}
		
		//if no condiments select, indicate it in the order
		if ( !atLeastOneCondiment )
				sandwichOrder.append(" no condiments");	

		
		//diplay final order
		JOptionPane.showMessageDialog(this, sandwichOrder.toString());
	}

	public static void main(String[] args) 
	{
		CatFoodOrder myFrame = new CatFoodOrder();
		
		//set JFrame properties
		myFrame.setTitle( "Cat food ordering");
		myFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		myFrame.pack();
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);

	}
}
