package antonio.lista_viaggi_utenti.controllers;

import antonio.lista_viaggi_utenti.entities.Prenotazione;
import antonio.lista_viaggi_utenti.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/crea")
    public ResponseEntity<Prenotazione> creaPrenotazione(@RequestParam UUID idEvento, @RequestParam UUID idUtente) {
        Prenotazione prenotazione = prenotazioneService.creaPrenotazione(idEvento, idUtente);
        return ResponseEntity.status(201).body(prenotazione);
    }
}
