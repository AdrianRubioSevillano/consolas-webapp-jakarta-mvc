package es.upsa.dasi.consolasjee.adpaters.rest.providers;

import adapters.rest.dtos.ErrorResponse;
import domain.exceptions.NotFoundConsolaException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class ConsolaExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        return switch (e){
            case NotFoundConsolaException notFoundConsolaException -> Response.status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponse.builder()
                            .message(notFoundConsolaException.getMessage())
                            .status("404")
                            .build())
                    .build();
            default -> Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ErrorResponse.builder()
                            .message(e.getMessage())
                            .status("500")
                            .build())
                    .build();
        };
    }
}
