
package antonio.lista_viaggi_utenti.payloads;

import java.time.LocalDate;
import java.util.UUID;

public record EventoDTO(UUID id_evento, String titolo, String descrizione, LocalDate data, String luogo, int numPostiDisponibili, UUID id_organizzatore) {
}
