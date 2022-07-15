/** Exercise the user written ExamStack class
	This is supplied starter code
	219 Practical 2 
	
	@author Chevy Mac
*/
import java.util.*;
public class TestExam2Stack {

	public static void main(String [] args){
		
		// Create a stack object 
		ExamStack es = new ExamStack();
		
		// Push some objects onto the stack
		es.push("Alpha");
		es.push("Beta");
		es.push("Gamma");
		es.push("Delta");
		es.push("Epsilon");
		
		// Peek at the top object
		String top = es.peek();
		System.out.println("Top item is "+ top );
		
		System.out.println("Stack removal of items:");
		// Get all the objects off the stack within a loop using the isEmpty() method
		// loop
		while(!es.isEmpty())
      {
			// Get the top item off the stack & print it
			String item = es.pop();
			System.out.println( ">  " + item );
		}
		// end loop
		
		
		
		// After everything is off the stack, 
		// make sure the isEmpty() method is really working
		if( es.isEmpty() )
			System.out.println("The stack is now empty. This is good.");
		else
			System.out.println("The stack says it is not empty. This is bad.\n"+
									 "Did you add the loop to the main program?");
		
	}
}

class ExamStack
{
   private LinkedList<String> stackItem = new LinkedList<String>();
   public void push(String item) 
   {
      stackItem.addFirst(item);
   }
   public String peek()
   {
      return stackItem.getFirst();
   }
   public String pop() 
   {
      return stackItem.removeFirst();
   }
   public boolean isEmpty() 
   {
      return (stackItem.size() == 0);
   }
}