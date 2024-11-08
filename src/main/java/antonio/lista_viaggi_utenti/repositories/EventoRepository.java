package antonio.lista_viaggi_utenti.repositories;

import antonio.lista_viaggi_utenti.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {

}
