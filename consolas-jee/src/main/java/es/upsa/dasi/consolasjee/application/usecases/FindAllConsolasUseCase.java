package es.upsa.dasi.consolasjee.application.usecases;

import es.upsa.dasi.consolasjee.domain.model.Consola;

import java.util.List;

public interface FindAllConsolasUseCase {
    List<Consola> execute();
}
