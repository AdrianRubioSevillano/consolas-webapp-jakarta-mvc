package es.upsa.dasi.consolasjee.application.usecases;

import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.domain.model.ReplaceConsolaCommand;

public interface UpdateConsolaUseCase {
    Consola updateConsola(ReplaceConsolaCommand command, long id);
}
