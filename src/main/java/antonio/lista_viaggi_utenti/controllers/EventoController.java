package antonio.lista_viaggi_utenti.controllers;

import antonio.lista_viaggi_utenti.entities.Evento;
import antonio.lista_viaggi_utenti.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/tutti")
    public ResponseEntity<List<Evento>> getTuttiGliEventi() {
        List<Evento> eventi = eventoService.trovaTuttiGliEventi();
        return ResponseEntity.ok(eventi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoPerId(@PathVariable UUID id) {
        Evento evento = eventoService.trovaEventoPerId(id);
        return ResponseEntity.ok(evento);
    }
}
