package domain.exceptions;

public class ConsolaRunTimeException extends RuntimeException {
    public ConsolaRunTimeException() {
    }

    public ConsolaRunTimeException(String message) {
        super(message);
    }

    public ConsolaRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsolaRunTimeException(Throwable cause) {
        super(cause);
    }

    public ConsolaRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
