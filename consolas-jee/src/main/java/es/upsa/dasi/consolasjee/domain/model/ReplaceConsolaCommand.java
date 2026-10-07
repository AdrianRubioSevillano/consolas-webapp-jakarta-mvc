package es.upsa.dasi.consolasjee.domain.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class ReplaceConsolaCommand {
    private String nombre;
    private String fabricante;
    private LocalDate fechaLanzamiento;
    private String foto;
}
