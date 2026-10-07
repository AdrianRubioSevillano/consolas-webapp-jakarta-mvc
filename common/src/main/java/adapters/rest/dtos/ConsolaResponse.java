package adapters.rest.dtos;

import lombok.*;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class ConsolaResponse {
    private long id;
    private String nombre;
    private String foto;
}
