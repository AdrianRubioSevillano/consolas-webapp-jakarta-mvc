package adapters.rest.dtos;


import lombok.*;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String status;
}
