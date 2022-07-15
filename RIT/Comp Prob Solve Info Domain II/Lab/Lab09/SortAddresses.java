import java.util.*;
import java.io.*;
import java.text.*;

public class SortAddresses
{
   public static void main(String [] args)
   {
		// Load the entire array list
      ArrayList<Customer> custList = new CustomerList();

		for( Customer info : custList )
			System.out.println( info );
		
      Comparator<Customer> zipComp = new ZipComparator();
      Collections.sort( custList, zipComp );
    
      System.out.println("\n- - Sorted by ID:");
		for( Customer info : custList )
			System.out.println( info );


      Comparator<Customer> statecityComp = new StateCityComparator();
      Collections.sort( custList, statecityComp );
      
      System.out.println("\n- - Sorted by State and City:");
      for( Customer info : custList )
			System.out.println( info );

   } // end main
} // end SortAddresses

class StateCityComparator implements Comparator<Customer>
{
   public int compare(Customer first, Customer second)
   {
      String firS = first.getState();
      String secS = second.getState();
      
		int diff = firS.compareTo(secS);	// a diff # <1, 0, >1
		
		if(diff == 0) 
      {
         String firC = first.getCity();
         String secC = second.getCity();
		   diff = firC.compareTo(secC);	// a diff # <1, 0, >1
      }
      return diff;
      
   } // end compare()
} // end StateCityComparator

class ZipComparator implements Comparator<Customer>
{
   public int compare( Customer first, Customer second )
   {
      int fir = first.getZip();
      int sec = second.getZip();
      
      //  return fir-sec;
      
      if ( fir < sec  ) return -1;
      if ( fir == sec ) return 0;
      return 1;
      
   } // end compare()
} // end ZipComparator

class Customer
{
	private String address;
   private int    zip;
	private String city;
	private String state;   
   public Customer( String _address, String _city, String _state, int _zip )
   {
		address 	= _address;
		city		= _city;
		state		= _state;
		zip		= _zip;
   } // end Customer constructor

	public String getAddress()	{ return address;	}
   public String getCity()		{ return city;		}
	public String getState()	{ return state;	}
	public int getZip()      	{ return zip;     }
    
   public String toString()
   {
      return String.format("%-30s %-20s %4s %09d",
			getAddress(), getCity(), getState(), getZip() );
   } // end toString()
   
   
} // end class Customer

/** 
	Have the CustomerList class generate the customer list.
	Students: Note how this class is defined, what happens in the constructor
	and how the "new CustomerList()" is used in the main part of this program.
*/
class CustomerList extends ArrayList<Customer> {

	/** Students - replace this with a documentation on what this does, 
		and how it is used */
	public CustomerList() {		
		add(new Customer("132 Commerical Way","Cleveland","OH",442340000) );
		add(new Customer("2421 West Industrial Way","Berkeley","CA",947100000) );
		add(new Customer("2421 West Industrial Way","Berkeley","CA",947100000) );
		add(new Customer("4223 Halster Way","Berkeley","CA",947101234) );
		add(new Customer("4223 Halster Way","Berkeley","CA",947104321) );
		add(new Customer("1 Washington Complex","Boston","MA",21010000) );
		add(new Customer("1 Braddock Circle","Kenmore","NY",142230000) );
		add(new Customer("1 Braddock Circle","Kenmore","NY",142230000) );
		add(new Customer("1 Braddock Circle","Kenmore","NY",142230000) );
		add(new Customer("45511 Delaware Ave.","Buffalo","NY",142210000) );
		add(new Customer("3112 West Helm Street","Kingwood","TX",773390000) );
		add(new Customer("3112 West Helm Street","Kingwood","TX",773390000) );
		add(new Customer("41 Golem Terrace","New York","NY",100120000) );
		add(new Customer("1602 Jackson Ave.","Arlington","VA",222020000) );
		add(new Customer("8871 West Grange Drive","Cairo","NY",124130000) );
		add(new Customer("44 WhiteStallion Pike","Lindenwold","NJ",80210000) );
		add(new Customer("45A Sturgeon Dr., Bldg. 5","Ft. Pierce","FL",349510000) );
		add(new Customer("45A Sturgeon Dr., Bldg. 5","Ft. Pierce","FL",349510000) );
		add(new Customer("776 West Ninth St.","Myrtle Beach","SC",295790000) );
		add(new Customer("6665 Peachtree Lane","Atlanta","GA",303280000) );
		add(new Customer("3 Mapleview Drive","Huntsville","AL",358030000) );
		add(new Customer("67 Merrifield Ave.","Oceanside","NY",115720000) );
		add(new Customer("35 West 9th St.","New York","NY",100120000) );
		add(new Customer("35 West 9th St.","New York","NY",100120000) );
		add(new Customer("5531 E. Lansing Ave.","Ypsilanti","MI",481970000) );
		add(new Customer("7554 West 9th St.","New York","NY",100030000) );
		add(new Customer("4 Rocky Way","Colorado Springs","CO",809410000) );
		add(new Customer("4 Rocky Way","Colorado Springs","CO",809410000) );
		add(new Customer("45521 Pilgrim Circle","Nantucket","MA",25540000) );
		add(new Customer("3 Carnegie Circle","Pittsburgh","PA",151080000) );
		add(new Customer("15 Carnegie Circle","Pittsburgh","PA",151080000) );
		add(new Customer("16 Broad Street","Rochester","NY",146210000) );
		add(new Customer("16 Broad Street","Rochester","NY",146210000) );
		add(new Customer("5665 MassPike Circle","Sandy Hook","CT",64820000) );
		add(new Customer("3 Desert Trail","Las Vegas","NV",891170000) );
		add(new Customer("3 Confederate Ave.","Roanoke","VA",240100000) );
		add(new Customer("44 Rockman Blvd.","Rochester","NY",146230000) );	
	}

}