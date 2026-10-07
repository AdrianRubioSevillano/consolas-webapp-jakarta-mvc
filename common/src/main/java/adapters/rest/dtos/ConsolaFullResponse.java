package adapters.rest.dtos;

import jakarta.json.bind.annotation.JsonbTransient;
import lombok.*;

import java.net.URI;
import java.time.LocalDate;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class ConsolaFullResponse {
    private long id;
    private String nombre;
    private String fabricante;
    private LocalDate fechaLanzamiento;
    private String foto;

    @JsonbTransient
    private URI uri;
}
