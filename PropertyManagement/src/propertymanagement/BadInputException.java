/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
