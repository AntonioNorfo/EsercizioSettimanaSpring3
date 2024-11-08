package antonio.lista_viaggi_utenti.repositories;

import antonio.lista_viaggi_utenti.entities.OrganizzatoreEventi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizzatoreEventiRepository extends JpaRepository<OrganizzatoreEventi, UUID> {

}
