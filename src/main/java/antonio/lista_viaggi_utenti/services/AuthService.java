package antonio.lista_viaggi_utenti.services;

import antonio.lista_viaggi_utenti.entities.Utente;
import antonio.lista_viaggi_utenti.exceptions.UnauthorizedException;
import antonio.lista_viaggi_utenti.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente autenticaUtente(String email, String password) {
        Utente utente = utenteRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("Credenziali non valide , biricchino"));
        if (!passwordEncoder.matches(password, utente.getPassword())) {
            throw new UnauthorizedException("Credenziali non valide , furbacchiotto");
        }
        return utente;
    }
}
