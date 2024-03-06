public class FormatException extends Exception {
    private final String errorCode;

    public FormatException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void logException() {
        System.err.println("Error Code: " + errorCode + ", Error Message: " + getMessage());
        // In a real-world application, this could be logging the error information
        // to a log file or system.
    }
}