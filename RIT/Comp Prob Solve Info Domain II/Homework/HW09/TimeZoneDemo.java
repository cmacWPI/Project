// Name: Chevy Mac
// Course: ISTE-121-01
// Homework: #9
// Description: Showing how TimeZone class works
import java.util.*;
public class TimeZoneDemo
{
   public static void main(String[] args)
   {
      // Create an TimeZone Object with time zone ID
      System.out.println("Creating an TimeZone Object with ID as \"America/New_York\"");
      TimeZone tz = TimeZone.getTimeZone("America/New_York");
      System.out.println();
      
      // Display with only in getDisplayName()
      System.out.println("Printing with \"getDisplayName()\".");
      System.out.println("Gets the name of standard time, Example: Easten Standard Time, Pacific Standard Time, etc.");
      System.out.println("New York is in " + tz.getDisplayName());
      System.out.println();
      
      // Display with getDisplayName((boolean daylight, int style)
      // Int style with 0 --> abbreviation and 1 --> not in abbrevation.
      // True states if it is in daylight savings else it is standard time.
      
      // Abbreviation
      System.out.println("Printing with \"getDisplayName(boolean daylight, int style)\". Example: getDisplayName(true, 0)");
      System.out.println("True if it is in daylight savings, else return false if not in daylight savings.");
      System.out.println("If style is at 0, it prints out an abbreivation, else prints full name.");
      System.out.println("New York is in " + tz.getDisplayName(true, 0) + " with Daylight Savings.");
      System.out.println("New York is in " + tz.getDisplayName(false, 0) + " without Daylight Savings.");
      System.out.println();
      
      // Not in Abbreviation
      System.out.println("int style is set to 1");
      System.out.println("New York is in " + tz.getDisplayName(true, 1) + " with Daylight Savings.");
      System.out.println("New York is in " + tz.getDisplayName(false, 1) + " without Daylight Savings.");
      System.out.println();
      
      // Display with getDisplayName(Locale locale)
      System.out.println("Printing with \"getDisplayName(Locale locale)\". Example: Locale.US");
      System.out.println("New York is in " + tz.getDisplayName(Locale.US));
      System.out.println();
      
      // Display with getID
      System.out.println("Printing with \"getID()\".");
      System.out.println("It gets the ID of the TimeZone.");
      System.out.println("The TimeZone ID is " + tz.getID());
      System.out.println();
      
      // Set ID
      System.out.println("Now what happens if we set to \"America/Toronto\" with \"setID(String ID)\" and use \"getDisplayName()\".");
      tz.setID("America/Toronto");
      
      // Display with only in getDisplayName() after setID("America/Toronto")
      System.out.println("The ID is " + tz.getID());
      System.out.println("Toronto is in " + tz.getDisplayName());
      System.out.println();
      
      // Display with only in getTimeZone()
      System.out.println("Printing with \"getTimeZone(America/Los_Angeles)\"");
      System.out.println("It gets the ID of the TimeZone with string specified.");
      System.out.println("Los Angles ID: " + tz.getTimeZone("America/Los_Angeles"));
      System.out.println();
      
      // Display with getDefault()
      System.out.println("Printing with \"getDefault()\"");
      System.out.println("It gets the ID of the default TimeZone that was used to create an object with.");
      System.out.println("Default TimeZone ID: " + tz.getDefault());
      System.out.println();
      
      // Display with getDefault() after using setDefault(TimeZone zone)
      System.out.println("Printing with \"setDefault(TimeZone.getTimeZone(America/Toronto))\"");
      System.out.println("It sets the TimeZone to be default.");
      tz.setDefault(TimeZone.getTimeZone("America/Toronto"));
      System.out.println("Default TimeZone ID: " + tz.getDefault());
      System.out.println();
      
      // Display with getRawOffset()
      System.out.println("Printing with \"getRawOffset()\"");
      System.out.println("It gets the amount of time in millseconds of Coordinated Universal Time.");
      System.out.println("Toronto's offset is " + tz.getRawOffset());
      System.out.println();
      
      // Display with getDSTSavings()
      System.out.println("Printing with \"getDSTSavings()\"");
      System.out.println("It gets the amount of time in which it will be added or subtract to local time.");
      System.out.println("3600000 is default of one hour.");
      System.out.println("Toronto's DST saving is " + tz.getDSTSavings());
      System.out.println();
      
      // Checks if it uses Daylight Saving Time with useDaylightTime()
      System.out.println("Printing with \"useDaylightTime()\"");
      System.out.println("It checks if it uses Daylight Saving Time.");
      System.out.println("Does this TimeZone uses Daylight Saving Time (Toronto)? - " + tz.useDaylightTime());
      System.out.println();
      
      // Checks if it uses Daylight Saving Time with observesDaylightTime()
      System.out.println("Printing with \"observesDaylightTime()\"");
      System.out.println("It checks if the TimeZone is currently in Daylight Savings.");
      System.out.println("Does this TimeZone is currently in Daylight Saving Time (Toronto)? - " + tz.observesDaylightTime());
      System.out.println();
      
      // Checks if it is in same TimeZone with other zones.
      System.out.println("Printing with \"hasSameRules(TimeZone.getTimeZone(TimeZone zone))\"");
      System.out.println("It checks if the TimeZone is in same zone with the one being compared to.");
      System.out.println("New York: " + tz.hasSameRules(TimeZone.getTimeZone("America/New_York")));
      System.out.println("Los Angeles: " + tz.hasSameRules(TimeZone.getTimeZone("America/Los_Angeles")));
   }
}





