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
    private final List<User> users;
    private final List<SaleTransaction> transactions;
    
    private int userIdCounter = 1;
    private final Scanner sc;
    
     /**
     * Default constructor. Initializes the inventory, users, and transactions
     * tables.
     */
    public PropertyManagement(){
    
        
        this.propertyDatabase = new ArrayList<Property>();
        this.users = new ArrayList<User>();
        this.transactions = new ArrayList<SaleTransaction>();
        this.sc = new Scanner(System.in);
        
    }
    
      /**
     * Constructor. Initializes the inventory, users, and transactions to given values.
     */
    
    public PropertyManagement(List<Property> propertyDatabase, List<User> users, List<SaleTransaction> transactions){
        
        this.propertyDatabase = propertyDatabase;
        this.users = users;
        this.transactions = transactions;
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
                    case 3: deleteProperty(); break;
                    case 4: searchVehicle(); break;
                    case 5: showPropertiesByPrice(); break;
                    case 6: showAllUsers(); break;
                    case 7: addNewUser(); break;
                    case 8: updateUser(); break;
                    case 9: sellProperty(); break;
                    case 10: showAllSales(); break;
                    case 11: exitProgram = true; break;
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
        "4. Search for a property. \n" +
        "5. Show properties given a price range. \n" +
        "6. Show list of users. \n" +
        "7. Add a new user to the database. \n" +
        "8. Update user info. \n" +
        "9. Sell a property. \n" +
        "10. Show sale transactions. \n" +
        "11.. Exit program. \n");
        
    }
    
    
       /**
     * Auxilary private method to print out a list of properties in a formatted manner.
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
     * This method prints out all the properties currently in the inventory, in a
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
    
    public void searchVehicle(){
            sc.nextLine();
            System.out.print("\n Enter Property ID you wish to search for:  ");
            String id = sc.nextLine();
            
            List<Property> matchingProperty = new ArrayList<Property>();
            for (Property p : propertyDatabase) {
                if (p.getId().equals(id)) {
                    matchingProperty.add(p);
                    showProperty(matchingProperty);
                    return;
                }
            }
        }
    
    /**
     * This method allows the user to delete a property from the property database.
     * @param sc The scanner object used to read user input.
     */
    
    public void deleteProperty() {
        sc.nextLine();
        System.out.print ("\n Enter ID of property you want to delete: ");
        String id = sc.nextLine();
        
        for (Property p : propertyDatabase){
            if(p.getId().equals(id)){
                propertyDatabase.remove(p);
                break;
            }
        }
        
    }
    
     /**
     * This method allows the user to search for vehicles within a price range.
     * The list of vehicles found is printed out.
     * @param sc The scanner object used to read user input.
     */
    
    public void showPropertiesByPrice() throws BadInputException {
        System.out.print("\nEnter low price value (float): ");
        float lowValue = sc.nextFloat();
        if (lowValue < 0.0f)
                throw new BadInputException("Low price cannot be negative.");

        System.out.print("\nEnter high price value (float): ");
        float highValue = sc.nextFloat();
        if (highValue < 0.0f)
                throw new BadInputException("High price cannot be negative.");

        System.out.println("\nSelect Property type:\n"
                + "1. House\n"
                + "2. Apartment\n");
        int propertyType = sc.nextInt();
        if (propertyType < 1 || propertyType > 2)
            throw new BadInputException("Legal vehicle type values: 1-3.");

        ArrayList<Property> matchingProperties = new ArrayList<Property>();
        for (Property p : propertyDatabase) {
            if (v.getPrice() >= lowValue && v.getPrice() <= highValue) {
                if (propertyType == 1 && p instanceof House)
                    matchingProperties.add(p);
                else if(propertyType == 2 && p instanceof Apartment)
                    matchingProperties.add(p);
            
            }
        }

        if (matchingProperties.size() == 0)
            System.out.println("\nNo matching vehicles found.");
        else
            showProperty(matchingProperties);
    }
    
    
    /**
     * This method allows a new user to be added to the database.
     * @param sc The scanner object used to read user input.
     * @throws PropertyManagement.BadInputException
     */
    public void addNewUser() throws BadInputException {
        System.out.println("Select user type:\n"
                + "1. Customer\n"
                + "2. Employee");
        int userType = sc.nextInt();
        if (userType < 1 || userType > 3)
            throw new BadInputException("Legal user type values: 1-2.");

        sc.nextLine();
        System.out.print("\nEnter first name (string): ");
        String firstName = sc.nextLine();

        System.out.print("\nEnter last name (string): ");
        String lastName = sc.nextLine();

        if (userType == 1) {
            System.out.print("\nEnter phone number (string): ");
            String phoneNumber = sc.nextLine();

            System.out.print("\nEnter driver license number (int): ");
            int dlnumber = sc.nextInt();
            if (dlnumber < 0)
                throw new BadInputException("Driver license number cannot be negative.");

            users.add(new Customer(userIdCounter++, firstName, lastName,
                    phoneNumber, dlnumber));
        } else if (userType == 2) {
            System.out.print("\nEnter monthly salary (float): ");
            float monthlySalary = sc.nextFloat();
            if (monthlySalary < 0.0f)
                throw new BadInputException("Monthly salary cannot be negative.");

            System.out.print("\nEnter bank account number (int): ");
            int bankAccNumber = sc.nextInt();
            if (bankAccNumber < 0)
                throw new BadInputException("Driver license number cannot be negative.");

            users.add(new Employee(userIdCounter++, firstName, lastName,
                    monthlySalary, bankAccNumber));
        } else {
            System.out.println("Unknown user type. Please try again.");
        }
    }

    /**
     * This method can be used to update a user's information, given their user ID.
     * @param sc The scanner object used to read user input.
     * @throws Dealership.BadInputException
     */
    public void updateUser() throws BadInputException {
        System.out.print("\nEnter user ID: ");
        int userID = sc.nextInt();

        User user = null;
        for (User u : users) {
            if (u.getId() == userID)
                user = u;
        }

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        sc.nextLine();
        System.out.print("\nEnter first name (string): ");
        String firstName = sc.nextLine();

        System.out.print("\nEnter last name (string): ");
        String lastName = sc.nextLine();

        if (user instanceof Customer) {
            System.out.print("\nEnter phone number (string): ");
            String phoneNumber = sc.nextLine();

            System.out.print("\nEnter driver license number (int): ");
            int dlnumber = sc.nextInt();
            if (dlnumber < 0)
                throw new BadInputException("Driver license number cannot be negative.");

            user.setFirstName(firstName);
            user.setLastName(lastName);
            ((Customer)user).setPhoneNumber(phoneNumber);
            ((Customer)user).setDriverLicenceNumber(dlnumber);

        } else { //User is an employee
            System.out.print("\nEnter monthly salary (float): ");
            float monthlySalary = sc.nextFloat();
            if (monthlySalary < 0.0f)
                throw new BadInputException("Monthly salary cannot be negative.");

            System.out.print("\nEnter bank account number (int): ");
            int bankAccNumber = sc.nextInt();
            if (bankAccNumber < 0)
                throw new BadInputException("Driver license number cannot be negative.");

            user.setFirstName(firstName);
            user.setLastName(lastName);
            ((Employee)user).setMonthlySalary(monthlySalary);
            ((Employee)user).setBankAccountNumber(bankAccNumber);
        }
    }

    /**
     * Prints out a list of all users in the database.
     */
    public void showAllUsers() {
        System.out.println("---------------------------------------------------"
                + "------------------------------------------");
        System.out.format("| %10s | %9s | %12s | %12s | %25s          | %n",
                "USER TYPE", "USER ID", "FIST NAME", "LAST NAME", "OTHER DETAILS");
        System.out.println("---------------------------------------------------"
                + "------------------------------------------");
        for (User u : users) {
            System.out.println(u.getFormattedText());
        }
        System.out.println("---------------------------------------------------"
                + "------------------------------------------");
    }
    
    /**
     * This method is used to complete a Property sale transaction.
     * @param sc The scanner object used to read user input.
     * @throws Dealership.BadInputException
     */
    public void sellProperty() throws BadInputException {
        System.out.print("\nEnter customer ID (int): ");
        int customerId = sc.nextInt();
        //Check that the customer exists in database
        boolean customerExists = false;
        for (User u : users) {
            if (u.getId() == customerId)
                customerExists = true;
        }
        if (!customerExists) {
            System.out.print("\nThe customer ID you have entered does not exist in the database.\n"
                    + "Please add the customer to the database first and then try again.");
            return;
        }

        System.out.print("\nEnter employee ID (int): ");
        int employeeId = sc.nextInt();
        //Check that the employee exists in database
        boolean employeeExists = false;
        for (User u : users) {
            if (u.getId() == employeeId)
                employeeExists = true;
        }
        if (!employeeExists) {
            System.out.print("\nThe employee ID you have entered does not exist in the database.\n"
                    + "Please add the employee to the database first and then try again.");
            return;
        }

        sc.nextLine();
        System.out.print("\nEnter Property ID (string): ");
        String id = sc.nextLine();
        //Check that the vehicle exists in database
        Property p = findProperty(id);
        if (p == null) {
            System.out.print("\nThe vehicle with the VIN you are trying to sell "
                    + "does not exist in the database. Aborting transaction.");
            return;
        }

        Date currentDate = new Date(System.currentTimeMillis());

        System.out.print("\nEnter sale price (float): ");
        float price = sc.nextFloat();
        if (price < 0.0f)
            throw new BadInputException("Sale price cannot be negative.");

        SaleTransaction trans = new SaleTransaction(customerId, employeeId, id,
                currentDate, price);
        transactions.add(trans);
        propertyDatabase.remove(p); //Sold vehicles are automatically removed from the inventory.

        System.out.println("\nTransaction Completed!");
    }

    /**
     * Prints out a list of all recorded transactions.
     */
    public void showAllSales() {
        for (SaleTransaction sale : transactions) {
            System.out.println(sale.toString());
        }
    }

    /**
     * Auxiliary method used to find a property in the database, given its
     * id number.
     * @param id
     * @return The property found, or otherwise null.
     */
    public Property findProperty(String id) {
        for (Property p : propertyDatabase) {
            if (p.getId().equals(id))
                return p;
        }
        return null;
    }
    
    
    
     /**
     * This method is used to read the database from a file, serializable objects.
     * @return A new PropertyManagement object.
     */
    @SuppressWarnings("unchecked") // This will prevent Java unchecked operation warning when
    // converting from serialized Object to Arraylist<>
    public static PropertyManagement readDatabase() {
        System.out.print("Reading database...");
        PropertyManagement cds = null;

        // Try to read existing Property database from a file
        InputStream file = null;
        InputStream buffer = null;
        ObjectInput input = null;
        try {
            file = new FileInputStream("PropertyManagement.ser");
            buffer = new BufferedInputStream(file);
            input = new ObjectInputStream(buffer);
            
            // Read serilized data
            List<Property> propertyDatabase = (ArrayList<Property>) input.readObject();
            List<User> users = (ArrayList<User>) input.readObject();
            List<SaleTransaction> transactions = (ArrayList<SaleTransaction>) input.readObject();
            cds = new PropertyManagement(propertyDatabase, users, transactions);
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
            file = new FileOutputStream("PropertyManagement.ser");
            buffer = new BufferedOutputStream(file);
            output = new ObjectOutputStream(buffer);
            
            output.writeObject(propertyDatabase);
            output.writeObject(users);
            output.writeObject(transactions);
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
