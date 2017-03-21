/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymanagement;

/**
 *
 * @author harry
 */
public class Apartment extends Property{
    
    /**
     * The apartment name.
     */
    protected String aptName;
    
    /**
     * The apartment number.
     */
    protected int aptNum;
    
    /**
     * Default constructor. 
     */
    public Apartment() {
        aptName = "";
        aptNum = 0;
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
     * @param aptName The apartment complex name.
     * @param aptNum The apartment number.
     */
    public Apartment(String id, String listing, String address, int numRoom, 
            float numBath, int squareFeet, float price, String aptName,
            int aptNum) {
        super(id, listing, address, numRoom, numBath, squareFeet, price);
        this.aptName = aptName;
        this.aptNum = aptNum;    
    }
    
    /**
     * Get the apartment complex name.
     * @return
     */
    public String getAptName(){
        return aptName;
    }
    
    /**
     * Set the apartment complex name.
     * @param subdivision
     */
    public void setAptName(String aptName){
        this.aptName = aptName;
    }
    
     /**
     * Get the apartment number.
     * @return
     */
    public int getAptNum(){
        return aptNum;
    }
    
    /**
     * Set the apartment number.
     * @param year
     */
    public void setAptNum(int aptNum){
        this.aptNum = aptNum;
    }
    
      /**
     * Get the attributes of the House, in a formatted text fashion.
     * @return Formatted text
     */
    @Override
    public String getFormattedText() {
        return String.format("| %14s | %12s | %8s | %20s | %10d | %10.2f | %11d | %10.2f | %10s | %6d | %n", 
                "Apartment", id, listing, address, numRoom, numBath, squareFeet, price, aptName, aptNum);
    }

    @Override
    public String toString() {
        return "House{" + "Property ID=" + id + ", Listing Type=" + listing + ", address=" + 
                address + ", # of rooms=" + numRoom + ", # of bathrooms=" + numBath + ", Square Feet=" + 
                squareFeet + ", price=" + price + ", Apartment Complex=" + aptName + ", Apartment #=" + aptNum + '}';
    }
    
}
