package antonio.lista_viaggi_utenti.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record EventoDTO(
        UUID id_evento,

        @NotBlank(message = "Il titolo è obbligatorio")
        String titolo,

        @NotBlank(message = "La descrizione è obbligatoria")
        String descrizione,

        @NotNull(message = "La data è obbligatoria")
        @FutureOrPresent(message = "La data deve essere nel presente o nel futuro")
        LocalDate data,

        @NotBlank(message = "Il luogo è obbligatorio")
        String luogo,

        @Min(value = 1, message = "Il numero di posti disponibili deve essere almeno 1")
        int numPostiDisponibili,

        UUID id_organizzatore
) {
}
