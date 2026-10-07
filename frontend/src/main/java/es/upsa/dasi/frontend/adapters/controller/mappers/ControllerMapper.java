package es.upsa.dasi.frontend.adapters.controller.mappers;


import adapters.rest.dtos.ConsolaFullResponse;
import es.upsa.dasi.frontend.adapters.controller.dtos.FormConsola;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface ControllerMapper {
    FormConsola toFormConsola(ConsolaFullResponse consolaFullResponse);
}
