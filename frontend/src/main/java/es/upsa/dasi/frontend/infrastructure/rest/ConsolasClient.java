package es.upsa.dasi.frontend.infrastructure.rest;


import adapters.rest.dtos.ConsolaFullResponse;
import adapters.rest.dtos.ConsolaResponse;
import es.upsa.dasi.frontend.adapters.controller.dtos.FormConsola;
import es.upsa.dasi.frontend.infrastructure.rest.providers.ConsolasResponseExceptionMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "consolas.rest.client")
@RegisterProvider(ConsolasResponseExceptionMapper.class)
public interface ConsolasClient {

    @GET
    @Path("/consolas")
    @Produces(MediaType.APPLICATION_JSON)
    List<ConsolaResponse> fetchAllConsolas();

    @GET
    @Path("/consolas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ConsolaFullResponse fetchConsolaById(@PathParam("id") long id);


    @POST
    @Path("/consolas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ConsolaFullResponse insertConsola(FormConsola formConsola);


    @PUT
    @Path("/consolas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    void updateConsola(@PathParam("id") long id, FormConsola formConsola);


    @DELETE
    @Path("/consolas/{id}")
    void deleteConsola(@PathParam("id") long id);

}
