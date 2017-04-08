/*
 * Real Estate Management Software v0.1
 * @author Harrysn Dao
 */
package propertymanagement;

/**
 *
 * @author harry
 */
public class House extends Property{
    
    /**
     * The subdivision of the property.
     */
    protected String subdivision;
    
    /**
     * The year the house was built.
     */
    protected int year;
    
    /**
     * Default constructor. 
     */
    public House() {
        subdivision = "";
        year = 0;
    }

    /**
     * Constructor used to initialize the House object with the given param. 
     * @param id The identification number of the property.
     * @param listing For sale or for rent.
     * @param address The address of the property.
     * @param numRoom The number of rooms.
     * @param numBath The number of baths.
     * @param squareFeet The square footage of the property.
     * @param price The sale or rent price of the property.
     * @param subdivision The subdivision of the property.
     * @param year The year the home was built.
     */
    public House(String id, String listing, String address, int numRoom, 
            float numBath, int squareFeet, float price, String subdivision,
            int year) {
        super(id, listing, address, numRoom, numBath, squareFeet, price);
        this.subdivision = subdivision;
        this.year = year;    
    }
    
    /**
     * Get the subdivision of the house.
     * @return
     */
    public String getSubdivision(){
        return subdivision;
    }
    
    /**
     * Set the subdivision of the house.
     * @param subdivision
     */
    public void setSubdivision(String subdivision){
        this.subdivision = subdivision;
    }
    
     /**
     * Get the year the house was built.
     * @return
     */
    public int getYear(){
        return year;
    }
    
    /**
     * Set the year the house was built.
     * @param year
     */
    public void setYear(int year){
        this.year = year;
    }
    
      /**
     * Get the attributes of the House, in a formatted text fashion.
     * @return Formatted text
     */
    @Override
    public String getFormattedText() {
        return String.format("| %14s | %12s | %8s | %20s | %10d | %10.2f | %11d | %10.2f | %10s | %6d | %n", 
                "House", id, listing, address, numRoom, numBath, squareFeet, price, subdivision, year);
    }

    @Override
    public String toString() {
        return "House{" + "Property ID=" + id + ", Listing Type=" + listing + ", address=" + 
                address + ", # of rooms=" + numRoom + ", # of bathrooms=" + numBath + ", Square Feet=" + 
                squareFeet + ", price=" + price + ", Subdivision=" + subdivision + ", Year Built=" + year + '}';
    }
}
