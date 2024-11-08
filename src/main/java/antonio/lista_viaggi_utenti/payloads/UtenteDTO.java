package antonio.lista_viaggi_utenti.payloads;

import java.util.UUID;

public record UtenteDTO(UUID id_utente, String nome, String cognome, String email) {
}
