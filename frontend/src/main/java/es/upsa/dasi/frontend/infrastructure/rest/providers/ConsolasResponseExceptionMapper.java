package es.upsa.dasi.frontend.infrastructure.rest.providers;

import adapters.rest.dtos.ErrorResponse;
import domain.exceptions.ConsolaBadRequestException;
import domain.exceptions.ConsolaException;
import domain.exceptions.NotFoundConsolaException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

@Provider
public class ConsolasResponseExceptionMapper implements ResponseExceptionMapper<Exception> {
    @Override
    public Exception toThrowable(Response response) {
        return switch (response.getStatusInfo().toEnum()){

            case NOT_FOUND -> new NotFoundConsolaException(response.readEntity(ErrorResponse.class).getMessage());
            case BAD_REQUEST -> new ConsolaBadRequestException(response.readEntity(ErrorResponse[].class));
            default -> new ConsolaException(response.readEntity(ErrorResponse.class).getMessage());

        };
    }
}
