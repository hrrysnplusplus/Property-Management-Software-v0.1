/*
 * Real Estate Management Software v0.1
 * @author Harrysn Dao
 */
package propertymanagement;


import java.io.Serializable;
import java.util.Date;

/**
 * This class represents a sale transaction in the Car Dealership Software.
 */
public class SaleTransaction implements Serializable {
    private int customerId;
    private int employeeId;
    private String id;
    private Date date;
    private float salePrice;

    /**
     * Constructor initializes a SaleTransaction object with the provided values.
     * @param customerId
     * @param employeeId
     * @param id
     * @param date
     * @param salePrice
     */
    public SaleTransaction(int customerId, int employeeId, String id, 
            Date date, float salePrice) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.id = id;
        this.date = date;
        this.salePrice = salePrice;
    }

    /**
     * Get the customer ID of this transaction.
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Set the customer ID of this transaction.
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Get the employee ID of this transaction.
     * @return
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Set the employee ID of this transaction.
     * @param employeeId
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Get the property id of this transaction.
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Set the property id of this transaction.
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the transaction date of this transaction.
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the transaction date of this transaction.
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the sale price of this transaction.
     * @return
     */
    public float getSalePrice() {
        return salePrice;
    }

    /**
     * Set the sale price of this transaction.
     * @param salePrice
     */
    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return "SaleTransaction{" + "customerId=" + customerId + ", employeeId=" 
                + employeeId + ", Property ID=" + id + ", date=" + date + ", salePrice=" 
                + salePrice + '}';
    }
}