package antonio.lista_viaggi_utenti.controllers;

import antonio.lista_viaggi_utenti.entities.Utente;
import antonio.lista_viaggi_utenti.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Utente> login(@RequestParam String email, @RequestParam String password) {
        Utente utente = authService.autenticaUtente(email, password);
        return ResponseEntity.ok(utente);
    }
}
