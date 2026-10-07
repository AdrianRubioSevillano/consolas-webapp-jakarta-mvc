package domain.exceptions;

public class ConsolaException extends Exception {
    public ConsolaException() {
    }

    public ConsolaException(String message) {
        super(message);
    }

    public ConsolaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsolaException(Throwable cause) {
        super(cause);
    }

    public ConsolaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
