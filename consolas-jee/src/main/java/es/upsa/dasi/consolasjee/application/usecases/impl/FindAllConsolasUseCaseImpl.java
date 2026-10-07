package es.upsa.dasi.consolasjee.application.usecases.impl;

import es.upsa.dasi.consolasjee.application.usecases.FindAllConsolasUseCase;
import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;


@ApplicationScoped
public class FindAllConsolasUseCaseImpl implements FindAllConsolasUseCase {

    @Inject
    Repository repository;

    @Override
    public List<Consola> execute() {
        return repository.findAllConsolas();
    }
}
