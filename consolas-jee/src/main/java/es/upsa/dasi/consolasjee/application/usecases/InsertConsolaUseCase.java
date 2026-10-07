package es.upsa.dasi.consolasjee.application.usecases;

import es.upsa.dasi.consolasjee.domain.model.AddConsolaCommand;
import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.infrastructure.persistance.dao.dtos.ConsolaRow;

public interface InsertConsolaUseCase {
    Consola execute(AddConsolaCommand command);
}
