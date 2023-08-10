package org.archicontribs.toolbox;

/**
 * The ToolboxException class is an Exception class that is thrown each time there is an issue in the toolbox plugin
 * @author Herve Jouin
 */
public class ToolboxException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception
     * @param message Describes the error that is raised
     */
    public ToolboxException (String message) {
        super (message);
    }
    
    /**
     * Creates a new exception
     * @param cause Exception that caused the error
     */
    public ToolboxException (Exception cause) {
        super (cause);
    }

    /**
     * Creates a new exception
     * @param message Describes the error that is raised
     * @param cause Exception that caused the error
     */
    public ToolboxException (String message, Throwable cause) {
        super (message, cause);
    }
}
