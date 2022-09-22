package pl.edu.wat.projektai.ai.models;

        import lombok.Data;

        import javax.persistence.*;

@Data
@Entity
@Table(name = "Pojazd")
@SequenceGenerator(name = "s_id", initialValue = 100, sequenceName = "seq_id", allocationSize = 1)
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_id")
    @Column(name = "ID_Pojazdu", nullable = false)
    private Long id;
    @Column(name = "Marka", nullable = false)
    private String brand;
    @Column(name = "Model", nullable = false)
    private String model;
    @Column(name = "Nr_Rejestracyjny", nullable = false)
    private String registerNr;
    @Column(name = "Nr_VIN", unique = true, nullable = false)
    private String vin;
    @Column(name = "Naprawa", nullable = false)
    private boolean repair;


}

