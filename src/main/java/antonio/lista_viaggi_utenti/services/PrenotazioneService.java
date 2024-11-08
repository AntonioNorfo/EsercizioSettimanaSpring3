package antonio.lista_viaggi_utenti.services;

import antonio.lista_viaggi_utenti.entities.Evento;
import antonio.lista_viaggi_utenti.entities.Prenotazione;
import antonio.lista_viaggi_utenti.entities.Utente;
import antonio.lista_viaggi_utenti.enums.StatoPrenotazione;
import antonio.lista_viaggi_utenti.exceptions.BadRequestException;
import antonio.lista_viaggi_utenti.exceptions.ResourceNotFoundException;
import antonio.lista_viaggi_utenti.repositories.EventoRepository;
import antonio.lista_viaggi_utenti.repositories.PrenotazioneRepository;
import antonio.lista_viaggi_utenti.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public Prenotazione creaPrenotazione(UUID idEvento, UUID idUtente) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new ResourceNotFoundException("Evento non trovato"));
        if (evento.getNumPostiDisponibili() <= 0) {
            throw new BadRequestException("Non ci sono posti disponibili");
        }

        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato"));

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setEvento(evento);
        prenotazione.setUtente(utente);
        prenotazione.setData_prenotazione(LocalDate.now());
        prenotazione.setStato(StatoPrenotazione.CONFERMATA);

        evento.setNumPostiDisponibili(evento.getNumPostiDisponibili() - 1);
        eventoRepository.save(evento);

        return prenotazioneRepository.save(prenotazione);
    }
}
