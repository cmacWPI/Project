/** Interface for the Mousy.java and Trajectory.java programs.
	 Provided for 4002-219 Lab 6.
	 @author Michael Floeser
*/

public interface MousySizes {
	/** Window width */
	int WIN_WIDTH = 600;
	
	/** Window height */
	int WIN_HEIGHT = 600;
		
	/** Red's starting base X location */
	int BASE_X = (int) ( WIN_WIDTH * 7.0 / 8.0);
		
	/** Red's starting base X location */
	int BASE_Y = (int) ( WIN_HEIGHT * 7.0 / 8.0);
	
	/** Base width */
	int BASE_WIDTH = 50;
	
	/** Base height */
	int BASE_HEIGHT = 50;
	
	/** Velocity's power factor from release of mouse within the square */
	double POWER_FACTOR = 3.0;
}