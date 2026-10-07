package es.upsa.dasi.consolasjee.infrastructure.persistance.dao.impl;

import domain.exceptions.ConsolaRunTimeException;
import domain.exceptions.NotFoundConsolaException;
import es.upsa.dasi.consolasjee.infrastructure.persistance.dao.Dao;
import es.upsa.dasi.consolasjee.infrastructure.persistance.dao.dtos.ConsolaRow;
import jakarta.annotation.Resource;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataSourceDefinition(
        name = "jdbc/consolas",
        className = "org.postgresql.ds.PGSimpleDataSource",
        url = "jdbc:postgresql://localhost:5432/upsa",
        user = "${ENV=DB_USER:postgres}",
        password = "${ENV=DB_PASSWORD:postgres}",
        initialPoolSize = 1,
        maxPoolSize = 3,
        minPoolSize = 1
)

@ApplicationScoped
public class DaoImpl implements Dao {

    @Resource(name = "jdbc/consolas")
    DataSource dataSource;

    @Override
    public List<ConsolaRow> findAllConsolas() {

        final String SQL = """
                           SELECT c.id, c.nombre, c.fabricante, c.fecha_lanzamiento, c.foto
                           FROM consolas c
                           ORDER BY c.fecha_lanzamiento DESC
                           """;

        List<ConsolaRow> consolas = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();){

            while (resultSet.next()) {
                ConsolaRow consolaRow = ConsolaRow.builder()
                        .id(resultSet.getLong(1))
                        .nombre(resultSet.getString(2))
                        .fabricante(resultSet.getString(3))
                        .fechaLanzamiento(resultSet.getDate(4).toLocalDate())
                        .foto(resultSet.getString(5))
                        .build();
                consolas.add(consolaRow);
            }
            return consolas;
        }catch (SQLException sqlException){
            throw new ConsolaRunTimeException(sqlException.getMessage(), sqlException);
        }

    }

    @Override
    public Optional<ConsolaRow> findConsolaById(long id) {

        final String SQL = """
                           SELECT c.id, c.nombre, c.fabricante, c.fecha_lanzamiento, c.foto
                           FROM consolas c
                           WHERE c.id = ?
                           """;
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                ){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                if (!resultSet.next()) return Optional.empty();
                return Optional.of(ConsolaRow.builder()
                        .id(resultSet.getLong(1))
                        .nombre(resultSet.getString(2))
                        .fabricante(resultSet.getString(3))
                        .fechaLanzamiento(resultSet.getDate(4).toLocalDate())
                        .foto(resultSet.getString(5))
                        .build());
            }

        }catch (SQLException sqlException){
            throw new ConsolaRunTimeException(sqlException.getMessage(), sqlException);
        }

    }

    @Override
    public ConsolaRow insertConsola(ConsolaRow consolaRow) {

        final String SQL = """
                           INSERT INTO consolas(nombre, fabricante, fecha_lanzamiento, foto)
                           Values (?, ?, ?, ?)
                           """;
        final String[] GENERATED_KEYS = {"id"};

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL, GENERATED_KEYS);
                ){
            preparedStatement.setString(1, consolaRow.getNombre());
            preparedStatement.setString(2, consolaRow.getFabricante());
            preparedStatement.setDate(3, Date.valueOf(consolaRow.getFechaLanzamiento()));
            preparedStatement.setString(4, consolaRow.getFoto());
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                return consolaRow.withId(resultSet.getLong(1));
            }

        }catch (SQLException sqlException){
            throw new ConsolaRunTimeException(sqlException.getMessage(), sqlException);
        }

    }

    @Override
    public Optional<ConsolaRow> updateConsola(ConsolaRow consolaRow) {

        final  String SQL = """
                            UPDATE consolas
                            SET nombre = ?, fabricante = ?, fecha_lanzamiento = ?, foto = ?
                            WHERE id = ?
                            """;
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)
                ){

            preparedStatement.setString(1, consolaRow.getNombre());
            preparedStatement.setString(2, consolaRow.getFabricante());
            preparedStatement.setDate(3, Date.valueOf(consolaRow.getFechaLanzamiento()));
            preparedStatement.setString(4, consolaRow.getFoto());
            preparedStatement.setLong(5, consolaRow.getId());
            int i = preparedStatement.executeUpdate();
            if (i==0)  return Optional.empty();
            return Optional.of(consolaRow.withId(consolaRow.getId()));

        }catch (SQLException sqlException){
            throw new ConsolaRunTimeException(sqlException.getMessage(), sqlException);
        }


    }

    @Override
    public void deleteConsolaById(long id) {

        final String SQL = """
                           DELETE FROM consolas
                           WHERE id = ?
                           """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                ){
            preparedStatement.setLong(1, id);
            int i = preparedStatement.executeUpdate();
            if(i==0) throw new NotFoundConsolaException("No se ha encontrado la consola con ID: %d".formatted(id));

        }catch (SQLException sqlException){
            throw new ConsolaRunTimeException(sqlException.getMessage(), sqlException);
        }

    }
}
