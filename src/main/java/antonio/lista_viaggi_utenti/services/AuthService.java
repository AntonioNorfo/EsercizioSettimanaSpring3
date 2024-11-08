package antonio.lista_viaggi_utenti.services;

import antonio.lista_viaggi_utenti.entities.OrganizzatoreEventi;
import antonio.lista_viaggi_utenti.entities.Utente;
import antonio.lista_viaggi_utenti.exceptions.UnauthorizedException;
import antonio.lista_viaggi_utenti.repositories.OrganizzatoreEventiRepository;
import antonio.lista_viaggi_utenti.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private OrganizzatoreEventiRepository organizzatoreRepository;


    public Object autenticaUtente(String email, String password) {

        Utente utente = utenteRepository.findByEmail(email).orElse(null);
        if (utente != null) {
            if (passwordEncoder.matches(password, utente.getPassword())) {
                return utente;
            } else {
                throw new UnauthorizedException("Credenziali non valide per l'utente normale");
            }
        }

        OrganizzatoreEventi organizzatore = organizzatoreRepository.findByEmail(email).orElse(null);
        if (organizzatore != null) {
            if (passwordEncoder.matches(password, organizzatore.getPassword())) {
                return organizzatore;
            } else {
                throw new UnauthorizedException("Credenziali non valide per l'organizzatore");
            }
        }

        throw new UnauthorizedException("Credenziali non valide");
    }

    public Utente registraUtente(Utente utente) {
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }

    public OrganizzatoreEventi registraOrganizzatore(OrganizzatoreEventi organizzatore) {
        organizzatore.setPassword(passwordEncoder.encode(organizzatore.getPassword()));
        return organizzatoreRepository.save(organizzatore);
    }
}
