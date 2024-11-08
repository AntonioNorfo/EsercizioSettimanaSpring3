package antonio.lista_viaggi_utenti.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResponseUserLoginDTO(
        @Email(message = "Email non valida")
        @NotBlank(message = "L'email è obbligatoria")
        String email,

        @NotBlank(message = "Il nome è obbligatorio")
        String nome,

        @NotBlank(message = "Il tipo di utente è obbligatorio")
        String tipoUtente,

        @NotBlank(message = "Il token è obbligatorio")
        String token
) {
}
