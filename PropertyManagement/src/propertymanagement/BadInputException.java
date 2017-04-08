/*
 * Real Estate Management Software v0.1
 * @author Harrysn Dao
 */
package propertymanagement;


/**
 * Custom Exception type, used to report bad input from user.
 */
public class BadInputException extends Exception {

    /**
     * Constructor, allows custom message assignment for thrown exception.
     * @param message
     */
    public BadInputException(String message) {
        super(message);
    }
}
