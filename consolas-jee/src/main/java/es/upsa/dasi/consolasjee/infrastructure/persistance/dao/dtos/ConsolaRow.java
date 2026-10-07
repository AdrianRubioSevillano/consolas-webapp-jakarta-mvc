package es.upsa.dasi.consolasjee.infrastructure.persistance.dao.dtos;

import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class ConsolaRow {
    private long id;
    private String nombre;
    private String fabricante;
    private LocalDate fechaLanzamiento;
    private String foto;
}
