package antonio.lista_viaggi_utenti.payloads;

import java.time.LocalDate;
import java.util.UUID;
import antonio.lista_viaggi_utenti.enums.StatoPrenotazione;

public record PrenotazioneDTO(UUID id_prenotazione, UUID id_evento, UUID id_utente, LocalDate data_prenotazione, StatoPrenotazione stato) {
}
