/** Exercise the user written ExamQueue class 
	This is supplied starter code
	219 Practical 2 
	
	@author Chevy Mac
*/
import java.util.*;
public class TestExam2Queue {

	public static void main(String [] args){
		
		// Create a queue object 
		ExamQueue eq = new ExamQueue();
		
		// Push some objects onto the Queue
		eq.enqueue("Alpha");
		eq.enqueue("Beta");
		eq.enqueue("Gamma");
		eq.enqueue("Delta");
		eq.enqueue("Epsilon");
		
		// Peek at the top object
		String top = eq.peek();
		System.out.println("Top item is "+ top );
		
		System.out.println("Queue removal of items:"); // DO NOT CHANGE CODE ABOVE HERE
		
		// Get all the objects off the Queue within a loop using the isEmpty() method
		// loop
      while(!eq.isEmpty())
      {
			// Get the top item off the Queue & print it  (DO NOT CHANGE THESE TWO LINES OF CODE)
			String item = eq.dequeue();
			System.out.println( ">  " + item );
		}
		// <<<<< end loop
		
		
																// DO NOT CHANGE CODE BELOW HERE IN THIS CLASS
		// After everything is off the Queue, 
		// make sure the isEmpty() method is really working
		if( eq.isEmpty() )
			System.out.println("The Queue is now empty. This is good.");
		else
			System.out.println("The Queue says it is not empty. This is bad.\n"+
									 "Did you add the loop to the main program?");
		
	}
}

// YOU NEED TO COMPLETE THE "ExamQueue" CLASS

// Add all your code into this class
class ExamQueue
{
   private LinkedList<String> queueItem = new LinkedList<String>();
   public void enqueue(String item) 
   {
      queueItem.addLast(item);
   }
   public String peek()
   {
      return queueItem.getFirst();
   }
   public String dequeue() 
   {
      return queueItem.removeFirst();
   }
   public boolean isEmpty() 
   {
      return (queueItem.size() == 0);
   }
}