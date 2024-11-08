package antonio.lista_viaggi_utenti.payloads;

import antonio.lista_viaggi_utenti.enums.StatoPrenotazione;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotazioneDTO(
        UUID id_prenotazione,

        @NotNull(message = "L'ID dell'evento è obbligatorio")
        UUID id_evento,

        @NotNull(message = "L'ID dell'utente è obbligatorio")
        UUID id_utente,

        @NotNull(message = "La data della prenotazione è obbligatoria")
        LocalDate data_prenotazione,

        @NotNull(message = "Lo stato della prenotazione è obbligatorio")
        StatoPrenotazione stato
) {
}
