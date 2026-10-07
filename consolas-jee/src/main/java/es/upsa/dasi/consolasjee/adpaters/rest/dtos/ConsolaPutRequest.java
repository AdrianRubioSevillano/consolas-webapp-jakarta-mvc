package es.upsa.dasi.consolasjee.adpaters.rest.dtos;

import es.upsa.dasi.consolasjee.infrastructure.validation.Url;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class ConsolaPutRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String nombre;

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "NINTENDO|SONY|SEGA|MICROSOFT|ATARI")
    private String fabricante;

    @Past
    private LocalDate fechaLanzamiento;

    @NotBlank
    @Size(min = 15, max = 500)
    @Url
    private String foto;
}
