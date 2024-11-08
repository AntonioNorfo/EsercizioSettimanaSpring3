package antonio.lista_viaggi_utenti.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue
    private UUID id_utente;
    private String nome;
    private String cognome;
    private String email;
    private String password;

}
