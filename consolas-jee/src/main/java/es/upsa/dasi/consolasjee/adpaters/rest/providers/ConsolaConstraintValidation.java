package es.upsa.dasi.consolasjee.adpaters.rest.providers;

import adapters.rest.dtos.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Set;

@Provider
public class ConsolaConstraintValidation implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(constraintViolations.stream().map(constraintViolation -> ErrorResponse.builder()
                            .message(constraintViolation.getMessage())
                            .status(constraintViolation.getPropertyPath().toString())
                            .build())
                        .toList())
                .build();
    }
}
