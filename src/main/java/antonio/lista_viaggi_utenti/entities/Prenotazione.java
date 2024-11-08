package antonio.lista_viaggi_utenti.entities;


import antonio.lista_viaggi_utenti.enums.StatoPrenotazione;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue
    private UUID id_prenotazione;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    private LocalDate data_prenotazione;
    
    @Enumerated(EnumType.STRING)
    private StatoPrenotazione stato;

}