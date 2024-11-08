package antonio.lista_viaggi_utenti.controllers;

import antonio.lista_viaggi_utenti.entities.OrganizzatoreEventi;
import antonio.lista_viaggi_utenti.entities.Utente;
import antonio.lista_viaggi_utenti.payloads.ResponseUserLoginDTO;
import antonio.lista_viaggi_utenti.payloads.UserLoginDTO;
import antonio.lista_viaggi_utenti.services.AuthService;
import antonio.lista_viaggi_utenti.tools.JWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWT jwt;

    @PostMapping("/login")
    public ResponseEntity<ResponseUserLoginDTO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {

        Object utenteAutenticato = authService.autenticaUtente(userLoginDTO.email(), userLoginDTO.password());

        String token;
        String tipoUtente;
        String nome;

        if (utenteAutenticato instanceof Utente) {
            Utente utente = (Utente) utenteAutenticato;
            token = jwt.createToken(utente.getId_utente().toString(), "Utente");
            tipoUtente = "Utente";
            nome = utente.getNome();
        } else {
            OrganizzatoreEventi organizzatore = (OrganizzatoreEventi) utenteAutenticato;
            token = jwt.createToken(organizzatore.getId_organizzatore().toString(), "Organizzatore");
            tipoUtente = "Organizzatore";
            nome = organizzatore.getUserName();
        }

        ResponseUserLoginDTO responseDTO = new ResponseUserLoginDTO(
                userLoginDTO.email(),
                nome,
                tipoUtente,
                token
        );

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/registrazione/utente")
    public ResponseEntity<Utente> registraUtente(@Valid @RequestBody Utente utente) {
        Utente nuovoUtente = authService.registraUtente(utente);
        return ResponseEntity.status(201).body(nuovoUtente);
    }

    @PostMapping("/registrazione/organizzatore")
    public ResponseEntity<OrganizzatoreEventi> registraOrganizzatore(@Valid @RequestBody OrganizzatoreEventi organizzatore) {
        OrganizzatoreEventi nuovoOrganizzatore = authService.registraOrganizzatore(organizzatore);
        return ResponseEntity.status(201).body(nuovoOrganizzatore);
    }
}
