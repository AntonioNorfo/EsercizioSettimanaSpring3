package antonio.lista_viaggi_utenti.controllers;

import antonio.lista_viaggi_utenti.entities.Prenotazione;
import antonio.lista_viaggi_utenti.entities.Utente;
import antonio.lista_viaggi_utenti.services.PrenotazioneService;
import antonio.lista_viaggi_utenti.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/registrazione")
    public ResponseEntity<Utente> registraUtente(@RequestBody Utente utente) {
        Utente nuovoUtente = utenteService.registraUtente(utente);
        return ResponseEntity.status(201).body(nuovoUtente);
    }

    @PostMapping("/{idUtente}/prenotazioni")
    public ResponseEntity<Prenotazione> creaPrenotazione(@PathVariable UUID idUtente, @RequestParam UUID idEvento) {
        Prenotazione prenotazione = prenotazioneService.creaPrenotazione(idEvento, idUtente);
        return ResponseEntity.status(201).body(prenotazione);
    }
}
