package domain.exceptions;

import adapters.rest.dtos.ErrorResponse;

public class ConsolaBadRequestException extends RuntimeException {
    private ErrorResponse[] errors;
    public ConsolaBadRequestException(ErrorResponse[] errors) {
        this.errors = errors;
    }
    public ErrorResponse[] getErrors() {
        return errors;
    }
}
