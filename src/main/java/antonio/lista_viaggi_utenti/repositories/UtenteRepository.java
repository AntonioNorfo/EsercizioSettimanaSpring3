package antonio.lista_viaggi_utenti.repositories;

import antonio.lista_viaggi_utenti.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UtenteRepository extends JpaRepository<Utente, UUID> {

}
