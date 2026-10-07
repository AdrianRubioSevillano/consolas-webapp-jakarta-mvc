package es.upsa.dasi.consolasjee.adpaters.rest;


import adapters.rest.dtos.ConsolaFullResponse;
import adapters.rest.dtos.ConsolaResponse;
import domain.exceptions.NotFoundConsolaException;
import es.upsa.dasi.consolasjee.adpaters.rest.dtos.ConsolaPostRequest;
import es.upsa.dasi.consolasjee.adpaters.rest.dtos.ConsolaPutRequest;
import es.upsa.dasi.consolasjee.adpaters.rest.mappers.ResourceMapper;
import es.upsa.dasi.consolasjee.application.usecases.*;
import es.upsa.dasi.consolasjee.domain.model.AddConsolaCommand;
import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.domain.model.ReplaceConsolaCommand;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;
import java.util.Optional;

@Path("/consolas")
public class ConsolasResource {

    FindAllConsolasUseCase findAllConsolasUseCase;
    FindConsolasByIdUseCase findConsolasByIdUseCase;
    InsertConsolaUseCase insertConsolaUseCase;
    UpdateConsolaUseCase updateConsolaUseCase;
    DeletConsolaUseCase deletConsolaUseCase;

    ResourceMapper resourceMapper;


    @Inject
    public ConsolasResource(FindAllConsolasUseCase findAllConsolasUseCase, FindConsolasByIdUseCase findConsolasByIdUseCase, InsertConsolaUseCase insertConsolaUseCase, UpdateConsolaUseCase updateConsolaUseCase, DeletConsolaUseCase deletConsolaUseCase, ResourceMapper resourceMapper) {
        this.findAllConsolasUseCase = findAllConsolasUseCase;
        this.findConsolasByIdUseCase = findConsolasByIdUseCase;
        this.insertConsolaUseCase = insertConsolaUseCase;
        this.updateConsolaUseCase = updateConsolaUseCase;
        this.deletConsolaUseCase = deletConsolaUseCase;
        this.resourceMapper = resourceMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllConsolas() {

        List<Consola> consolas = findAllConsolasUseCase.execute();
        List<ConsolaResponse> list = consolas.stream().map(resourceMapper::toConsolaResponse).toList();
        return Response.ok().entity(list).build();

    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findConsolasById(@PathParam("id") long id, @Context UriInfo uriInfo) {

        Optional<Consola> optConsola = findConsolasByIdUseCase.findConsolaById(id);
        return optConsola.map(consola -> resourceMapper.toConsolaFullResponse(consola, uriInfo))
                            .map(consolaFullResponse -> Response.ok().entity(consolaFullResponse).build())
                            .orElseThrow(()-> new NotFoundConsolaException("No se ha encontrado ninguna conola con id: %d".formatted(id)));

    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertConsola(@Context UriInfo uriInfo, @Valid ConsolaPostRequest consolaPostRequest) {

        AddConsolaCommand addConsolaCommand = resourceMapper.toAddConsolaCommand(consolaPostRequest);
        Consola consola = insertConsolaUseCase.execute(addConsolaCommand);
        ConsolaFullResponse consolaFullResponse = resourceMapper.toConsolaFullResponse(consola, uriInfo);
        return Response.created(consolaFullResponse.getUri()).entity(consolaFullResponse).build();

    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateConsola(@PathParam("id") long id, @Valid ConsolaPutRequest consolaPutRequest){

        ReplaceConsolaCommand replaceConsolaCommand = resourceMapper.toReplaceConsolaCommand(consolaPutRequest);
        Consola consola = updateConsolaUseCase.updateConsola(replaceConsolaCommand, id);
        return Response.noContent().build();

    }


    @DELETE
    @Path("/{id}")
    public Response deleteConsola(@PathParam("id") long id) {
        deletConsolaUseCase.execute(id);
        return Response.noContent().build();
    }
}
