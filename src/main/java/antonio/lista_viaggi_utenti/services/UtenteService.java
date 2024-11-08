package antonio.lista_viaggi_utenti.services;

import antonio.lista_viaggi_utenti.entities.Utente;
import antonio.lista_viaggi_utenti.exceptions.ResourceNotFoundException;
import antonio.lista_viaggi_utenti.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente registraUtente(Utente utente) {
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }

    public Utente trovaUtentePerId(UUID idUtente) {
        return utenteRepository.findById(idUtente)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato"));
    }
}
