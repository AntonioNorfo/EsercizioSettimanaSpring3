package antonio.lista_viaggi_utenti.services;

import antonio.lista_viaggi_utenti.entities.OrganizzatoreEventi;
import antonio.lista_viaggi_utenti.exceptions.ResourceNotFoundException;
import antonio.lista_viaggi_utenti.repositories.OrganizzatoreEventiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizzatoreEventiService {

    @Autowired
    private OrganizzatoreEventiRepository organizzatoreEventiRepository;

    public OrganizzatoreEventi trovaOrganizzatorePerId(UUID idOrganizzatore) {
        return organizzatoreEventiRepository.findById(idOrganizzatore)
                .orElseThrow(() -> new ResourceNotFoundException("Organizzatore non trovato"));
    }
}
