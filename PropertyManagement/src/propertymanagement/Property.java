/*
 * Real Estate Management Software v0.1
 * @author Harrysn Dao
 */
package propertymanagement;

import java.io.Serializable;

/**
 *
 * @author Harrysn Dao
 */
public abstract class Property implements Serializable{
    
    /**
     * The property ID
     */
    protected String id;

    /**
     * The type of listing (for sale or for rent)
     */
    protected String listing;

    /**
     * The address of the property
     */
    protected String address;
    
    /**
     * The number of rooms.
     */
    protected int numRoom;

    /**
     * The number of bathrooms.
     */
    protected float numBath;
    
    /**
     * The square footage of the property.
     */
    protected int squareFeet;

    /**
     * The price of the property
     */
    protected float price;
    
    
    /**
     * Default constructor used to initialize the class fields of the class. 
     * Since this is an abstract class, the constructor cannot be used to 
     * instantiate objects object of the class.
     */
    protected Property() {
        this.id = "";
        this.listing = "";
        this.address = "";
        this.numRoom = 0;
        this.numBath = 0.0f;
        this.squareFeet = 0;
        this.price = 0.0f;
    }

    /**
     * Constructor used to initialize the class fields of the class. Since this
     * is an abstract class, the constructor cannot be used to instantiate 
     * objects object of the class.
     * @param id The identification number of the property.
     * @param listing For sale or for rent.
     * @param address The address of the property.
     * @param numRoom The number of rooms.
     * @param numBath The number of baths.
     * @param squareFeet The square footage of the property.
     * @param price The sale or rent price of the property.
     */
    protected Property(String id, String listing, String address, int numRoom, 
            float numBath, int squareFeet, float price) {
        this.id = id;
        this.listing = listing;
        this.address = address;
        this.numRoom = numRoom;
        this.numBath = numBath;
        this.squareFeet = squareFeet;
        this.price = price;
    }
    
    /**
     * Get the property Id..
     * @return id The property id.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the property Id.
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the listing of the property.
     * @return
     */
    public String getListing() {
        return listing;
    }

    /**
     * Set the listing of the property.
     * @param listing
     */
    public void setListing(String listing) {
        this.listing = listing;
    }

    /**
     * Get the address of the property.
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address of the property.
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the number of rooms..
     * @return
     */
    public int getNumRoom() {
        return numRoom;
    }

    /**
     * Set the the number of rooms.
     * @param numRoom
     */
    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }

    /**
     * Get the number of baths.
     * @return
     */
    public float getNumBath() {
        return numBath;
    }

    /**
     * Set the number of baths.
     * @param numBath
     */
    public void setNumBath(float numBath) {
        this.numBath = numBath;
    }

    /**
     * Get the square footage of the property.
     * @return
     */
    public float getSquareFeet() {
        return squareFeet;
    }

    /**
     * Set the square footage of the property.
     * @param price
     */
    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }
    
    /**
     * Get the price of the property.
     * @return
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set the price of the property.
     * @param mileage
     */
    public void setPrice(float price) {
        this.price = price;
    }
    
    /**
     * Abstract method, to be implemented by subclasses of class Vehicle.
     */
    public abstract String getFormattedText();
    
}
