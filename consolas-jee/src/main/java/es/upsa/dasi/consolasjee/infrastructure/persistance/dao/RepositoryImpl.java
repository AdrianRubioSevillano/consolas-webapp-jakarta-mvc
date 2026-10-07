package es.upsa.dasi.consolasjee.infrastructure.persistance.dao;

import domain.exceptions.NotFoundConsolaException;
import es.upsa.dasi.consolasjee.domain.model.Consola;
import es.upsa.dasi.consolasjee.domain.repository.Repository;
import es.upsa.dasi.consolasjee.infrastructure.persistance.dao.dtos.ConsolaRow;
import es.upsa.dasi.consolasjee.infrastructure.persistance.dao.mappers.DaoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class RepositoryImpl implements Repository {

    Dao dao;
    DaoMapper daoMapper;

    @Inject
    public RepositoryImpl(Dao dao, DaoMapper daoMapper) {
        this.dao = dao;
        this.daoMapper = daoMapper;
    }

    @Override
    public List<Consola> findAllConsolas() {
        return dao.findAllConsolas().stream().map(daoMapper::toConsola).toList();
    }

    @Override
    public Optional<Consola> findConsolaById(long id) {
        return dao.findConsolaById(id).map(daoMapper::toConsola);
    }

    @Override
    public Consola insertConsola(Consola consola) {
        ConsolaRow consolaRow = daoMapper.toConsolaRow(consola);
        ConsolaRow newConsola = dao.insertConsola(consolaRow);
        return daoMapper.toConsola(newConsola);
    }

    @Override
    public Consola updateConsola(Consola consola) {
        ConsolaRow consolaRow = daoMapper.toConsolaRow(consola);
        Optional<ConsolaRow> optConsola = dao.updateConsola(consolaRow);
        if (optConsola.isEmpty()) throw new NotFoundConsolaException("No se ha encontrado la consola con ID: %d".formatted(consolaRow.getId()));
        return daoMapper.toConsola(optConsola.get());
    }

    @Override
    public void deleteConsolaById(long id) {
        dao.deleteConsolaById(id);
    }
}
