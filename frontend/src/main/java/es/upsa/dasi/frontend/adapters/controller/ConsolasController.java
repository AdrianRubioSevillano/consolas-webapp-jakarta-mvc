package es.upsa.dasi.frontend.adapters.controller;


import adapters.rest.dtos.ConsolaFullResponse;
import adapters.rest.dtos.ConsolaResponse;
import domain.exceptions.NotFoundConsolaException;
import es.upsa.dasi.frontend.adapters.controller.dtos.FormConsola;
import es.upsa.dasi.frontend.infrastructure.rest.ConsolasClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.*;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.MvcBinding;
import jakarta.mvc.binding.ParamError;
import jakarta.mvc.security.CsrfProtected;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.script.Bindings;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/consolas")
@ApplicationScoped
public class ConsolasController {

    @Inject
    @RestClient
    ConsolasClient consolasClient;

    @Inject
    Models models;

    @Inject
    BindingResult bindingResult;

    @Inject
    MvcContext mvcContext;


    @GET
    @Controller
    @UriRef("getAllConsolas")
    @View("/jsps/consolas.jsp")
    public void getAllConsolas() {

        List<ConsolaResponse> consolaResponses = consolasClient.fetchAllConsolas();
        models.put("consolas", consolaResponses);

    }


    @GET
    @Path("/{id}")
    @Controller
    @UriRef("getConsolaById")
    public String getConsolaById(@PathParam("id") long id) {

        try{
            ConsolaFullResponse consolaFullResponse = consolasClient.fetchConsolaById(id);
            models.put("consola", consolaFullResponse);
            return "/jsps/consola.jsp";
        }catch (NotFoundConsolaException e){
            models.put("id", id);
            return "/jsps/exceptions/notFound.jsp";
        }

    }


    @POST
    @Controller
    @CsrfProtected
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UriRef("insertConsola")
    public Response insertConsola(@Valid @BeanParam FormConsola formConsola) {

        if (bindingResult.isFailed()){
            Set<ParamError> allErrors = bindingResult.getAllErrors();

            Map<String, List<String>> errores = allErrors.stream().collect(Collectors.groupingBy(paramError -> paramError.getParamName().toString(),
                                                Collectors.mapping(paramError -> paramError.getMessage(), Collectors.toList())));

            models.put("errores", errores);
            models.put("form", formConsola);
            models.put("action", "INSERT");
            return Response.ok().entity("/jsps/form/consolaForm.jsp").build();
        }

        ConsolaFullResponse consolaFullResponse = consolasClient.insertConsola(formConsola);
        URI uri = mvcContext.uri("getConsolaById", Map.of("id", consolaFullResponse.getId()));
        return Response.seeOther(uri).build();

    }


    @PUT
    @Path("/{id}")
    @CsrfProtected
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UriRef("updateConsola")
    public Response updateConsola(@PathParam("id") long id, @Valid @BeanParam FormConsola formConsola) {

        try{
            if (bindingResult.isFailed()) {
                Set<ParamError> allErrors = bindingResult.getAllErrors();
                Map<String, List<String>> errores = allErrors.stream().collect(Collectors.groupingBy(paramError -> paramError.getParamName().toString(),
                        Collectors.mapping(paramError -> paramError.getMessage(), Collectors.toList())));

                models.put("errores", errores);
                models.put("form", formConsola);
                models.put("action", "UPDATE");
                return Response.ok().entity("/jsps/form/consolaForm.jsp").build();
            }

            consolasClient.updateConsola(id, formConsola);
            URI uri = mvcContext.uri("getAllConsolas");
            return Response.seeOther(uri).build();
        }catch (NotFoundConsolaException e){
            models.put("id", id);
            return Response.ok().entity("/jsps/exceptions/notFound.jsp").build();
        }

    }


    @DELETE
    @Path("/{id}")
    @Controller
    @UriRef("deleteConsola")
    public Response deleteConsola(@PathParam("id") long id) {

        try{
            consolasClient.deleteConsola(id);
            URI uri = mvcContext.uri("getAllConsolas");
            return Response.seeOther(uri).build();

        }catch (NotFoundConsolaException e){
            models.put("id", id);
            return Response.ok().entity("/jsps/exceptions/notFound.jsp").build();
        }

    }

}
