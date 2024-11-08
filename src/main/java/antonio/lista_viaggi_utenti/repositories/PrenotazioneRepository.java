package antonio.lista_viaggi_utenti.repositories;

import antonio.lista_viaggi_utenti.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    
}
