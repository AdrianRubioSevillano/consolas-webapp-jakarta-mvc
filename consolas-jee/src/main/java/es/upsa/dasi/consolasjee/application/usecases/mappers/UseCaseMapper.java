package es.upsa.dasi.consolasjee.application.usecases.mappers;


import es.upsa.dasi.consolasjee.domain.model.AddConsolaCommand;
import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.domain.model.ReplaceConsolaCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface UseCaseMapper {
    Consola toConsola(AddConsolaCommand command);
    @Mapping(target = "id", source = "id")
    @Mapping(target = ".", source = "command")
    Consola toConsola(ReplaceConsolaCommand command, long id);
}
