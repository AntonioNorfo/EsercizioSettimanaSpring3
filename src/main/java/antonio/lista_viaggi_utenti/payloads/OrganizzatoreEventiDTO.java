package antonio.lista_viaggi_utenti.payloads;

import java.util.UUID;

public record OrganizzatoreEventiDTO(UUID id_organizzatore, String userName, String email) {
}
