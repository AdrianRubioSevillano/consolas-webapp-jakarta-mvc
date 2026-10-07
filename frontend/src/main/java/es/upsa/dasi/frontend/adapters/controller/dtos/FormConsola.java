package es.upsa.dasi.frontend.adapters.controller.dtos;

import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class FormConsola {

    @FormParam("id")
    @MvcBinding
    private long id;

    @FormParam("nombre")
    @MvcBinding
    @NotBlank
    @Size(min = 3, max = 100)
    private String nombre;

    @FormParam("fabricante")
    @MvcBinding
    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "NINTENDO|SONY|SEGA|MICROSOFT|ATARI")
    private String fabricante;


    @FormParam("fechaLanzamiento")
    @MvcBinding
    @NotBlank
    private String fechaLanzamiento;

    @FormParam("foto")
    @MvcBinding
    @NotBlank
    @Size(min = 15, max = 500)
    private String foto;
}
