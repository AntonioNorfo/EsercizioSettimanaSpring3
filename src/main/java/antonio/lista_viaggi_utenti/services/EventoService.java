package antonio.lista_viaggi_utenti.services;

import antonio.lista_viaggi_utenti.entities.Evento;
import antonio.lista_viaggi_utenti.entities.OrganizzatoreEventi;
import antonio.lista_viaggi_utenti.exceptions.ResourceNotFoundException;
import antonio.lista_viaggi_utenti.repositories.EventoRepository;
import antonio.lista_viaggi_utenti.repositories.OrganizzatoreEventiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private OrganizzatoreEventiRepository organizzatoreEventiRepository;


    public Evento creaEvento(UUID idOrganizzatore, Evento evento) {
        OrganizzatoreEventi organizzatore = organizzatoreEventiRepository.findById(idOrganizzatore)
                .orElseThrow(() -> new ResourceNotFoundException("Organizzatore non trovato"));

        evento.setOrganizzatore(organizzatore);
        return eventoRepository.save(evento);
    }

    public Evento aggiornaEvento(UUID idEvento, Evento eventoAggiornato) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new ResourceNotFoundException("Evento non trovato"));

        evento.setTitolo(eventoAggiornato.getTitolo());
        evento.setDescrizione(eventoAggiornato.getDescrizione());
        evento.setData(eventoAggiornato.getData());
        evento.setLuogo(eventoAggiornato.getLuogo());
        evento.setNumPostiDisponibili(eventoAggiornato.getNumPostiDisponibili());
        return eventoRepository.save(evento);
    }

    public void eliminaEvento(UUID idEvento) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new ResourceNotFoundException("Evento non trovato"));
        eventoRepository.delete(evento);
    }

    public List<Evento> trovaTuttiGliEventi() {
        return eventoRepository.findAll();
    }

    public Evento trovaEventoPerId(UUID idEvento) {
        return eventoRepository.findById(idEvento)
                .orElseThrow(() -> new ResourceNotFoundException("Evento non trovato"));
    }
}
