package es.upsa.dasi.frontend.adapters.controller;


import adapters.rest.dtos.ConsolaFullResponse;
import domain.exceptions.NotFoundConsolaException;
import es.upsa.dasi.frontend.adapters.controller.dtos.FormConsola;
import es.upsa.dasi.frontend.adapters.controller.mappers.ControllerMapper;
import es.upsa.dasi.frontend.infrastructure.rest.ConsolasClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.mvc.View;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/form")
@ApplicationScoped
public class FormController {

    @Inject
    @RestClient
    ConsolasClient consolasClient;

    @Inject
    Models models;

    @Inject
    ControllerMapper controllerMapper;

    @GET
    @Path("/insert")
    @Controller
    @UriRef("getFormInsert")
    @View("/jsps/form/consolaForm.jsp")
    public void getFormInsert(){
        models.put("action", "INSERT");
    }

    @GET
    @Path("/update/{id}")
    @Controller
    @UriRef("getFormUpdate")
    @View("/jsps/form/consolaForm.jsp")
    public String getFormUpdate(@PathParam("id") long id) {

        try{
            ConsolaFullResponse consolaFullResponse = consolasClient.fetchConsolaById(id);
            FormConsola formConsola = controllerMapper.toFormConsola(consolaFullResponse);
            models.put("action", "UPDATE");
            models.put("form", formConsola);
            return "/jsps/form/consolaForm.jsp";
        }catch (NotFoundConsolaException e){
            models.put("id", id);
            return "/jsps/exceptions/notFound.jsp";
        }

    }


    @GET
    @Path("/delete/{id}")
    @Controller
    @UriRef("getFormDelete")
    public String getFormDelete(@PathParam("id") long id) {

        try{
            ConsolaFullResponse consolaFullResponse = consolasClient.fetchConsolaById(id);
            FormConsola formConsola = controllerMapper.toFormConsola(consolaFullResponse);
            models.put("action", "DELETE");
            models.put("form", formConsola);
            return "/jsps/form/consolaForm.jsp";
        }catch (NotFoundConsolaException e){
            models.put("id", id);
            return "/jsps/exceptions/notFound.jsp";
        }

    }
}
