// Name: Chevy Mac
// Course: ISTE-121-01
// Homework: #6
// Description: Caesar Cipher Server
import java.net.*;
import java.io.*;
public class CaesarServer implements CaesarConstants
{
   // Declartions
   private static int SHIFT_VALUE;
   private String message;
   private String encrypt = "";
   private char encryptChar;
   private String decrypt = "";
   private char decryptChar;
   private int ALPHABET_SIZE = 26;
   private int CHARACTER_POSITION;
   private int VALUE;
   private String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

   public CaesarServer()
   {
      // Declartions
      ServerSocket ss = null;
      try
      {
         ss = new ServerSocket(PORT_NUMBER);
         while(true)
         {
            Socket cs = ss.accept();  // Waits here until a client connects.
            ThreadServer ts = new ThreadServer(cs); 
            ts.start(); // Starts thread
         }
      }
      catch(BindException be)
      {
			System.out.println("Server is already running on this computer, stopping the program.");
      }
      catch(IOException ioe)
      {
			System.out.println("Error.");
      }  
   }

   class ThreadServer extends Thread
   {
      // Declartions
      Socket cs;
      public ThreadServer(Socket cs) 
      {
		   this.cs = cs;
		}
      
      public void run()
      {
         // Declartions
         BufferedReader br;
			PrintWriter pw;
			String clientMsg;
         try 
         {
            br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(cs.getOutputStream()));
            while(br.ready())
            {
			      clientMsg = br.readLine();	// From client
               if(clientMsg.equals("ENCRYPT")) // Checks if client sends "ENCRYPT" message
               {
                  pw.println("OK"); // Sents "OK" to client
                  pw.flush();
                  
                  message = br.readLine().toLowerCase(); // Reads from client for "original" message to encrypt then lowercase it.
                  
                  // Checks if message is number, prints out error, and doesn't run the rest of "encrypt" program.
                  if(message.matches("[0-9]+"))
                  {
                     pw.println("Invalid response from server - no text processed.");
                     pw.flush();	
                  }
                  else
                  {
                     // Begins encrypting message
                     for(int i = 0; i < message.length(); i++)
                     {
                        CHARACTER_POSITION = ALPHABET.indexOf(message.charAt(i)); // Gets the value of characater location in message.
                        VALUE = (CHARACTER_POSITION + SHIFT_VALUE) % ALPHABET_SIZE; // Sets the value to shift letters in message.
                        encryptChar = ALPHABET.charAt(VALUE); // Gets the shifted letter by it's value.
                        encrypt+= encryptChar; // Appends each encrypted letter.
                     }
                     pw.println(encrypt); // Sends encrypted message to client
                     pw.flush();
                     encrypt = ""; // Sets encrypted message back to blank.
                  }
               }
               else if(clientMsg.equals("DECRYPT")) // Checks if client sends "DECRYPT" message
               {
                  pw.println("OK"); // Sents "OK" to client
                  pw.flush(); 
                  
                  message = br.readLine().toLowerCase(); // Reads from client for "encrypted" message to decrypt then lowercase it.
                  
                  // Checks if message is number, prints out error, and doesn't run the rest of "decrypt" program.
                  if(message.matches("[0-9]+"))
                  {
                     pw.println("Invalid response from server - no text processed.");
                     pw.flush();	
                  }
                  else
                  {
                     // Begins decrypting message
                     for(int i = 0; i < message.length(); i++)
                     {
                        CHARACTER_POSITION = ALPHABET.indexOf(message.charAt(i)); // Gets the value of characater location in message.
                        VALUE = (CHARACTER_POSITION - SHIFT_VALUE) % ALPHABET_SIZE; // Sets the value to shift letters in message.
                        // Checks if value is negative
                        if(VALUE < 0)
                        {
                           VALUE = ALPHABET_SIZE + VALUE; // Sets the value by subtracting from alphabet size.
                        }
                        decryptChar = ALPHABET.charAt(VALUE); // Gets the shifted letter by it's value.
                        decrypt+= decryptChar; // Appends each decrypted letter.
                     }
                     pw.println(decrypt); // Sends decrypted message to client
                     pw.flush();
                     decrypt = ""; // Sets decrypted message back to blank.
                  } 
               }
               else if(clientMsg.equals("ERROR")) // Checks if client sends "ERROR" message
               {
                  pw.println("ERROR");
                  pw.flush();	
               }
            }
			}
			catch(IOException io) 
         { 
				System.out.println("Error."); 
			}
      }
   }  
   
   // Main program, reads from arguments/command line.
   public static void main(String[] args)
   {
      // Checks if command line prompt is only a single argument
      if(args.length == 1)
      {
         try
         {
            // Checks if the number is between 1-25.
            if((Integer.parseInt(args[0]) > 0) && (Integer.parseInt(args[0]) < 26))
            {
               SHIFT_VALUE = Integer.parseInt(args[0]);
            }
            else
            {
               System.out.println("Shift value must be between 1-25 in argument, the value has been set to default value of 3.");
               SHIFT_VALUE = DEFAULT_SHIFT_VALUE;
            }
         }
         catch(NumberFormatException fe)
         {
            System.out.println("Argument isn't in number value, the value has been set to default value of 3.");
            SHIFT_VALUE = DEFAULT_SHIFT_VALUE;
         }       
      }
      else
      {
         System.out.println("Has to be only single integer (no words either), the value has been set to default value of 3.");
         SHIFT_VALUE = DEFAULT_SHIFT_VALUE;
      }
      // Starts up the server
      new CaesarServer();
   }
}