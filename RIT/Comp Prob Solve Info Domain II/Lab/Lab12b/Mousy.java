import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** 
	Mousy - Lab 6 - 4002-219
	Follow the lab sheet to get this working.
*/

public class Mousy extends JFrame implements MousySizes 
{
	private final static int BASE_X = (int)((double)WIN_WIDTH*7/8.0);
	private final static int BASE_Y = (int)((double)WIN_HEIGHT*7/8.0);

	public static void main(String [] args)
   {
		new Mousy();
	}
	
	public Mousy() 
   {
// 		JPanel jpTitles = new JPanel( new GridLayout(0,1) );
// 		add( jpTitles , BorderLayout.NORTH );
// 			jpTitles.add( new JLabel("Lab 6 - Mousy graphics & other stuff", JLabel.CENTER ) );
// 			jpTitles.add( new JLabel("Click and drag the mouse here>           < does it move?", JLabel.CENTER ) );
// 			jpTitles.add( new JLabel("How about in here >                      < does it move?", JLabel.CENTER ) );
		MouseMove mm = new MouseMove();
		add( mm , BorderLayout.CENTER);

		setTitle("My Trajectory");
		setSize(WIN_WIDTH,WIN_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setVisible( true );
	}
	//////////////////	//////////////////	//////////////////	//////////////////	//////////////////
	public class MouseMove extends JPanel 
   {

		private int counter=0;
		
		private int startX = 30;								// start 30px from the left
		private int startY = (int)(WIN_HEIGHT*7.0/8.0);	// start 7/8th the way down the screen
		private int endX=0;
		private int endY=0;

		// STEP 5 - do something with x & y
		private int x=0;				// Mouse X position
		private int y=0;				// Mouse Y position
		
		private int [] pointsX = new int[1];
		private int [] pointsY = new int[1];
		
		// MouseMove constructor
		public MouseMove() 
      {

			// Adding a mouse motion listener to the 
			addMouseMotionListener( new MouseMotionAdapter() 
         {

// STEP 3 - add the mouse moved method
				public void mouseMoved(MouseEvent mEvent) 
            {
               System.out.printf("mouseMoved() %d times to (%d,%d)%n", ++counter, mEvent.getX(), mEvent.getY());
            }

				public void mouseDragged(MouseEvent e) // When Mouse is pressed & dragged only
            {
					x = e.getX();
					y = e.getY();

// STEP 6		// Only draw the line in the box, when we are dragging in the blue box.
					if (x < startX + BASE_WIDTH && x > startX && y > startY - BASE_HEIGHT && y < startY)
					{
						repaint();
					}
				}			
			});	// end of addMouseMotionListener


////// STEP 7 CODE /////////////////////////
			addMouseListener(new MouseAdapter() 
         {
            public void mouseReleased(MouseEvent me)
            {
               int mx = me.getX();
               int my = me.getY();
					if( mx < startX+BASE_WIDTH  && mx>startX 	 && // only do something when released in bounds
						 my > startY-BASE_HEIGHT && my < startY ) 
					{	 
						shoot(mx, my );
					}
            }       	
			});

		} // end MouseMove constructor

		// When the mouse is released, throw the projectile
		private void shoot(int x, int y) 
      {
			double angle;
			double lineLen;
			
			endX = x;
			endY = y;	
			// From the XY start and XY end of the line, calculate the angle from the horizontal
			angle = -Math.atan( ((double)startY-endY) / (startX-endX) ) * 180.0/Math.PI;
			
			// from the start XY to the end XY, calculate the length of the line, for how much velocity
			lineLen = Math.sqrt( Math.pow(Math.abs(startX-endX),2) + Math.pow( Math.abs( startY-endY),2));
			
			// Convert the lineLen into some number for this screen size.  Use a constant POWER_FACTOR
			double velocity = lineLen * POWER_FACTOR;
			
// 			System.out.printf("Released at x=%d  y=%d length=%.4f angle=%.4f   velocity=%.4f %n"   
// 													, endX, endY, lineLen, angle, velocity );
				
			// Create a Trajectory object what starts with the angle and velocity of the projectile, from an X & Y
			Trajectory tra = new Trajectory( angle, velocity, startX, startY );
			
			// Fire at a base located at X & Y, return true if it was hit, else false
			boolean hit = tra.fire( BASE_X, BASE_Y );
			
			// Get the points from the projectile.  These are objects of the Point class
			Vector drawPoints = tra.getThePoints();
			
// 			System.out.println( drawPoints );
// 			System.out.printf("Last point (%.2f, %.2f) %n",
// 						 ((Point)drawPoints.get(drawPoints.size()-1)).getX(), ((Point)drawPoints.get(drawPoints.size()-1)).getY() );
			
			
			// move the Point objects from the vector to an array to draw them
			int nPoints = drawPoints.size();
         pointsX = new int[nPoints];
			pointsY = new int[nPoints];
			
         for(int j=0; j < nPoints; j++)
         {
            pointsX[j] = (int)((Point)(drawPoints.get(j))).getX();
            pointsY[j] = (int)((Point)(drawPoints.get(j))).getY();
         }
			// STEP 7 - Very last thing added here
         repaint();
         
		}
				
		public void paintComponent(Graphics g) 
      {
			// STEP 2 - Something really super is missing here
		   super.paintComponent(g);
 			g.drawString("",x,y);
			
			// STEP 4 - Add the blue and red bases
			g.setColor(Color.BLUE);
         g.drawRect(startX, startY - BASE_HEIGHT, BASE_WIDTH, BASE_HEIGHT);
			g.setColor(Color.RED);
         g.drawRect(BASE_X, BASE_Y - BASE_HEIGHT, BASE_WIDTH, BASE_HEIGHT);	
         		
			// STEP 5 - Draw simple line from start to mouse cursor
			g.drawLine(startX, startY, startX, startY);
         
			// STEP 7 - Polyline
         g.drawPolyline(pointsX, pointsY, pointsX.length);
		}
	}
}