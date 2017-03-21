/*
 * Real Estate Management Software v0.1
 * @author Harrysn Dao
 */
package propertymanagement;

/**
 *
 * @author Harrysn Dao
 */

import java.io.*;
import java.util.*;



public class PropertyManagement {

    private final List<Property> propertyDatabase;
    
    private int userIdCounter = 1;
    private final Scanner sc;
    
     /**
     * Default constructor. Initializes the inventory, users, and transactions
     * tables.
     */
    public PropertyManagement(){
    
        
        this.propertyDatabase = new ArrayList<Property>();
        this.sc = new Scanner(System.in);
        
    }
    
      /**
     * Constructor. Initializes the inventory, users, and transactions to given values.
     */
    
    public PropertyManagement(List<Property> propertyDatabase){
        
        this.propertyDatabase = propertyDatabase;
        this.sc = new Scanner(System.in);
    }
    
    /**
     * This method serves as the main interface between the program and the user.
     * The method interacts with the user by printing out a set of options, and
     * asking the user to select one.
     */
    public void runSoftware(){
        
        
        int choice = 0;
        boolean exitProgram = false;
        do {
            printMenu();
            try {
                choice = sc.nextInt();
                
                switch (choice) {
                    case 1: showAllProperties(); break;
                    case 2: addNewProperty(); break;
                    case 4: exitProgram = true; break;
                    default: System.err.println("Please select a number between 1 and 11.");
                }
            } catch (InputMismatchException ex) {
                System.err.println("Input missmatch. Please try again.");
                sc.nextLine();
                continue;
            } catch (BadInputException ex) {
                System.err.println("Bad input. "+ex.getMessage());
                System.err.println("Please try again.");
                sc.nextLine();
                continue;
            }
        }
        
        while(!exitProgram);
        
        
    }
    
    /**
     * Auxiliary method that prints out the operations menu.
     */
    private static void printMenu() {
        
        System.out.println(
        "\n1. Show all existing properties in the database. \n" + 
        "2. Add a new property to the database. \n" + 
        "3. Remove a property from the database (given its property id). \n" +
        "4. Exit program. \n");
        
    }
    
    
       /**
     * Auxilary private method to print out a list of vehicles in a formatted manner.
     */
    private void showProperty(List<Property> Property) {
        System.out.println("---------------------------------------------------"
                + "-------------------------------------------------------");
        System.out.format("| %14s | %12s | %8s | %20s | %6s | %9s | %10s | %17s | %n", 
                "Property Type", "Property ID", "Listing", "Address", "# of Rooms", "# of Baths", "Square Foot", "EXTRA DETAILS      ");
        System.out.println("---------------------------------------------------"
                + "-------------------------------------------------------");
        for (Property p : Property) {
            System.out.println(p.getFormattedText());
        }
        System.out.println("---------------------------------------------------"
                + "-------------------------------------------------------");
    }

    /**
     * This method prints out all the vehicle currently in the inventory, in a
     * formatted manner.
     */
    public void showAllProperties() {
        showProperty(propertyDatabase);
    }
    
    
    
    
     /**
     * This method allows the user to enter a new property to the property
     * database.
     * @param sc The scanner object used to read user input.
     * @throws Dealership.BadInputException
     */
    
    public void addNewProperty() throws BadInputException {
        System.out.println("Select property type:\n"
                + "1. House\n"
                + "2. Apartment\n");
        int propertyType = sc.nextInt();
        if (propertyType <1 || propertyType>2)
            throw new BadInputException("Must enter 1 for house or 2 for apartment.");
        
        sc.nextLine();
        System.out.print("\nEnter Property ID (string): ");
        String id = sc.nextLine();
        if (id.length() > 5)
            throw new BadInputException("VIN should not be more that 5 characters long.");
        
        System.out.print("\nFor a rental property enter 1. For a property on sale enter 2 (int): ");
        int listingchoice = sc.nextInt();
        if (listingchoice < 1 || listingchoice >2)
            throw new BadInputException("You must enter 1 for rent or 2 for sale.");
        
        String listing;
        if (listingchoice == 1){
            listing = "Rent";
        }else listing = "Sale";
        
        sc.nextLine();
        System.out.print("\nEnter property address (string): ");
        String address = sc.nextLine();
        
        System.out.print("\nHow many rooms does this property have? (int): ");
        int numRoom = sc.nextInt();
        if (numRoom < 0)
            throw new BadInputException("Number of rooms cannot be negative.");
        
        System.out.print("\nHow many bathrooms does this property have? (float): ");
        float numBath = sc.nextFloat();
        if (numBath < 0)
            throw new BadInputException("Number of bathrrooms cannot be negative.");
        
        System.out.print("\nHow many square feet is the property? (int): ");
        int squareFeet = sc.nextInt();
        
        
        if (listing.equals("Rent")){
        System.out.print("\nEnter Monthly Rent (float): ");
        }else System.out.print("\nEnter Sales Price (float): ");
            
        float price = sc.nextFloat();
        if (price < 0.0f)
            throw new BadInputException("Price cannot be negative.");
        
            
        if (propertyType == 1) {
            sc.nextLine();
            System.out.print("\nEnter Subdivision (string): ");
            String subdivision = sc.nextLine();
            
            System.out.print("\nEnter year built (int); ");
            int year = sc.nextInt();
            
            House h = new House(id, listing, address, numRoom, numBath, 
                    squareFeet, price, subdivision, year);
            propertyDatabase.add(h);
        } else if (propertyType == 2) {
            
            sc.nextLine();
            System.out.print("\nEnter the Apartment Name: (string): ");
            String aptName = sc.nextLine();
            
            System.out.print("\nEnter the Apartment Number), (int): ");
            int aptNum = sc.nextInt();
            
            
            Apartment a = new Apartment(id, listing, address, numRoom, numBath, 
                    squareFeet, price, aptName, aptNum);
            propertyDatabase.add(a);
        }else {
            System.out.println("Unknown vehicle type entered. Please try again.");
        }
        
    }
    
     /**
     * This method is used to read the database from a file, serializable objects.
     * @return A new PropertyManagement object.
     */
    @SuppressWarnings("unchecked") // This will prevent Java unchecked operation warning when
    // convering from serialized Object to Arraylist<>
    public static PropertyManagement readDatabase() {
        System.out.print("Reading database...");
        PropertyManagement cds = null;

        // Try to read existing Property database from a file
        InputStream file = null;
        InputStream buffer = null;
        ObjectInput input = null;
        try {
            file = new FileInputStream("Property Listings.txt");
            buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);
            
            // Read serilized data
            List<Property> propertyDatabase = (ArrayList<Property>) input.readObject();
  
            cds = new PropertyManagement(propertyDatabase);
            cds.userIdCounter = input.readInt();
            
            input.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.toString());
        } catch (FileNotFoundException ex) {
            System.err.println("Database file not found.");
        } catch (IOException ex) {
            System.err.println(ex.toString());
        } finally {
            close(file);
        }
        System.out.println("Done.");
        
        return cds;
    }
    
    /**
     * This method is used to save the Property database as a 
     * serializable object.
     * @param cds
     */
    public void writeDatabase() {
        System.out.print("Writing database...");
        //serialize the database
        OutputStream file = null;
        OutputStream buffer = null;
        ObjectOutput output = null;
        try {
            file = new FileOutputStream("Property Listings.txt");
            buffer = new BufferedOutputStream(file);
            output = new ObjectOutputStream(buffer);
            
            output.writeObject(propertyDatabase);
            output.writeInt(userIdCounter);
            
            output.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        } finally {
            close(file);
        }
        System.out.println("Done.");
    }
    
    /**
     * Auxiliary convenience method used to close a file and handle possible
     * exceptions that may occur.
     * @param c
     */
    public static void close(Closeable c) {
        if (c == null) {
            return;
        }
        try {
            c.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }
    
      
    /**
     * The main method of the program.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          PropertyManagement cds = readDatabase();
        
        // If you could not read from the file, create a new database.
        if (cds == null) {
            System.out.println("Creating a new database.");
            cds = new PropertyManagement();
        }
        
        cds.runSoftware();
        cds.writeDatabase();
    }
    
}
