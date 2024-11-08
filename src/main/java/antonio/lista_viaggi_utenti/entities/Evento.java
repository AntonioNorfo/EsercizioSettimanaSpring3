package antonio.lista_viaggi_utenti.entities;

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
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue
    private UUID id_evento;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int numPostiDisponibili;

    @ManyToOne
    @JoinColumn(name = "id_organizzatore")
    private OrganizzatoreEventi organizzatore;

}
