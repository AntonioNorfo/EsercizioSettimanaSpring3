package antonio.lista_viaggi_utenti.controllers;

import antonio.lista_viaggi_utenti.entities.Evento;
import antonio.lista_viaggi_utenti.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/organizzatori")
public class OrganizzatoreEventiController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/{idOrganizzatore}/eventi/crea")
    public ResponseEntity<Evento> creaEvento(@PathVariable UUID idOrganizzatore, @RequestBody Evento evento) {
        Evento nuovoEvento = eventoService.creaEvento(idOrganizzatore, evento);
        return ResponseEntity.status(201).body(nuovoEvento);
    }

    @PutMapping("/{idOrganizzatore}/eventi/aggiorna/{idEvento}")
    public ResponseEntity<Evento> aggiornaEvento(@PathVariable UUID idOrganizzatore, @PathVariable UUID idEvento, @RequestBody Evento eventoAggiornato) {
        Evento evento = eventoService.aggiornaEvento(idEvento, eventoAggiornato);
        return ResponseEntity.ok(evento);
    }

    @DeleteMapping("/{idOrganizzatore}/eventi/elimina/{idEvento}")
    public ResponseEntity<String> eliminaEvento(@PathVariable UUID idOrganizzatore, @PathVariable UUID idEvento) {
        eventoService.eliminaEvento(idEvento);
        return ResponseEntity.status(204).body("Evento eliminato con successo");
    }
}
