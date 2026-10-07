package es.upsa.dasi.consolasjee.application.usecases.impl;

import es.upsa.dasi.consolasjee.application.usecases.DeletConsolaUseCase;
import es.upsa.dasi.consolasjee.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class DeletConsolaUseCaseImpl implements DeletConsolaUseCase {

    @Inject
    Repository repository;

    @Override
    public void execute(long id) {
        repository.deleteConsolaById(id);
    }
}
