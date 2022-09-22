package pl.edu.wat.projektai.ai.models;

        import lombok.Data;

        import javax.persistence.*;

@Data
@Entity
@Table(name = "Kierowca")
@SequenceGenerator(name = "s_id", initialValue = 100, sequenceName = "seq_id", allocationSize = 1)
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_id")
    @Column(name = "ID_Kierowcy", nullable = false)
    private Long id;
    @Column(name = "Imie", nullable = false)
    private String firstName;
    @Column(name = "Nazwisko", nullable = false)
    private String name;
    @Column(name = "PESEL", unique = true, nullable = false)
    private String pesel;
}
