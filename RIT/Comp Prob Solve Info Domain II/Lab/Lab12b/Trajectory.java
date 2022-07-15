import java.util.*;
import java.awt.*;

/** 
   Class Trajectory use to compute the distance and height of a projectile 
	@author Michael Floeser
*/

public class Trajectory implements MousySizes
{
   private final static double GRAVITY = 32.2;
   
   private double angle;   // Stored as Radians
   private double velocity;
   private double timer;
   private int xStart;
   private int yStart;

	Vector thePoints  = new Vector<Point>(); 
   /** Accepts degrees and initial velocity of object */
   
   public Trajectory(double a, double v, int startX, int startY )
   {
      angle = a * (Math.PI/180.0);  // Convert Degrees to Radians
		System.out.printf("Trajectory angle %.2f Degrees, %.2f Radians%n", a, angle);
      velocity = v;
      xStart = startX;
      yStart = startY;
   }
   
   /** Returns the height of this object at a given time */
   public double calcHeight(double atTime)
   {
      return velocity * atTime * Math.sin(angle) - 0.5 * GRAVITY * atTime *atTime;
   }
   
   /** Returns the distance of this object at a given time */
   public double calcDist(double atTime)
   {
      return velocity * atTime * Math.cos(angle);
   }
   
   /** Does the shooting and tracking of the projectile on the screen.  
			otherX, and otherY are the ending X&Y coord's of the opponents base
			
       @return false-Missed, true-Hit something 
    */
   public boolean fire( int otherX, int otherY )
   {
      thePoints = new Vector();
      Point aPoint;
      Shape opponent = (Shape)(new Rectangle(otherX, otherY-BASE_HEIGHT, BASE_WIDTH, BASE_HEIGHT));
      
      final double timeInc = 0.1;    // Increment the time by this constant
      boolean keepGoing = true;
      boolean hitOpponent = false;
      int h,d;
      
      timer = 0.0;
      
      int prevD = d = xStart;    // Starting locations of object
      int prevH = h = yStart;

      
//       System.out.println("Started at X= "+prevD+"  Y= "+prevH);
      
      while(keepGoing)
      {
         thePoints.add( new Point(d,h) );
         timer += timeInc;
         d = (int)(xStart+calcDist(timer));
         h = (int)(yStart-calcHeight(timer));
//         h = (int)(yStart-calcHeight(timer));
         
//            System.out.printf("x=%d  y=%d   Dist = %.2f  Height = %.2f   %n",
// 			  							d, h, calcDist(timer), calcHeight(timer) );
         
         if( d < 0 || d > WIN_WIDTH || h < 0)
         {
            keepGoing = false;
            System.out.println("Shot went out of bounds");
            break;
         }

         if ( opponent.contains(d,h) )
         {
            hitOpponent = true;
            System.out.println("Hit opponent.");
            thePoints.add( new Point(d,h) ); // Add it in when hitting other
            break;
         }
         
//          if ( ((Shape)theShapes.get(0)).contains(d,h) )
//          {
//             keepGoing = false;
//             System.out.println("Hit the ground");
//          }

				if( h > WIN_HEIGHT || d > WIN_WIDTH ) {
					keepGoing = false;
					System.out.println("trajectory out of bounds");
				}

//            Iterator it = theShapes.iterator();
//            
//            while( it.hasNext() )
//            {
//               Shape figure = (Shape) it.next();
//               if ( figure.contains(d,h) )
//               {
//                  keepGoing = false;
//                  System.out.println("Hit a shape"+figure);
//                  
//                  if (figure instanceof Rectangle)
//                  { 
//                     hitOpponent = true;
//                  }
//                  
//                  break;
//               }
//               
//            } // end while(it.hasMore())

      }  // end while(keepGoing)
//       
//       Point[] finalPoints = new Point[thePoints.size()];
//       
//       for(int i=0; i<thePoints.size(); i++)
//       {
//          double x = ((Point)thePoints.elementAt(i)).getX();
//          double y = ((Point)thePoints.elementAt(i)).getY();
//          
//          finalPoints[i] = new Point( (int)x, (int)y );
//       }
//       
//       theLines.add(finalPoints);
      
      return hitOpponent;
   }
   
	public Vector getThePoints() {
		return thePoints;
	}
   
} // end class Trajectory
