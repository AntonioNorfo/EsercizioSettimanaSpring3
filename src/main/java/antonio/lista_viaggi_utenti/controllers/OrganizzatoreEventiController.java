package antonio.lista_viaggi_utenti.controllers;

import antonio.lista_viaggi_utenti.entities.Evento;
import antonio.lista_viaggi_utenti.entities.OrganizzatoreEventi;
import antonio.lista_viaggi_utenti.exceptions.UnauthorizedException;
import antonio.lista_viaggi_utenti.services.EventoService;
import antonio.lista_viaggi_utenti.services.OrganizzatoreEventiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/organizzatori")
public class OrganizzatoreEventiController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private OrganizzatoreEventiService organizzatoreService;

    @PreAuthorize("hasRole('ORGANIZZATORE')")
    @PostMapping("/{idOrganizzatore}/eventi/crea")
    public ResponseEntity<Evento> creaEvento(@PathVariable UUID idOrganizzatore, @RequestBody Evento evento) {

        OrganizzatoreEventi organizzatore = organizzatoreService.trovaOrganizzatorePerId(idOrganizzatore);
        evento.setOrganizzatore(organizzatore);
        Evento nuovoEvento = eventoService.creaEvento(idOrganizzatore, evento);
        return ResponseEntity.status(201).body(nuovoEvento);
    }

    @PreAuthorize("hasRole('ORGANIZZATORE')")
    @PutMapping("/{idOrganizzatore}/eventi/aggiorna/{idEvento}")
    public ResponseEntity<Evento> aggiornaEvento(@PathVariable UUID idOrganizzatore, @PathVariable UUID idEvento, @RequestBody Evento eventoAggiornato) {
        Evento evento = eventoService.trovaEventoPerId(idEvento);
        if (!evento.getOrganizzatore().getId_organizzatore().equals(idOrganizzatore)) {
            throw new UnauthorizedException("Non autorizzato a modificare questo evento");
        }
        Evento eventoAggiornatoRes = eventoService.aggiornaEvento(idEvento, eventoAggiornato);
        return ResponseEntity.ok(eventoAggiornatoRes);
    }

    @PreAuthorize("hasRole('ORGANIZZATORE')")
    @DeleteMapping("/{idOrganizzatore}/eventi/elimina/{idEvento}")
    public ResponseEntity<String> eliminaEvento(@PathVariable UUID idOrganizzatore, @PathVariable UUID idEvento) {
        Evento evento = eventoService.trovaEventoPerId(idEvento);
        if (!evento.getOrganizzatore().getId_organizzatore().equals(idOrganizzatore)) {
            throw new UnauthorizedException("Non autorizzato a eliminare questo evento");
        }
        eventoService.eliminaEvento(idEvento);
        return ResponseEntity.status(204).body("Evento eliminato con successo");
    }
}
