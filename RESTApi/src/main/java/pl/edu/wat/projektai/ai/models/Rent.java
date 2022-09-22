package pl.edu.wat.projektai.ai.models;

        import lombok.Data;

        import javax.persistence.*;
        import java.io.Serializable;
        import java.sql.Date;

@Data
@Entity
@Table(name = "Wynajem")
@SequenceGenerator(name = "s_id", initialValue = 100, sequenceName = "seq_id", allocationSize = 1)
public class Rent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_id")
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ID_Kierowcy", nullable = false)
    private Driver driver;
    @ManyToOne
    @JoinColumn(name = "ID_Pojazdu", nullable = false)
    private Vehicle vehicle;
    @Column(name = "Data_OD", nullable = false)
    private Date dateFrom;
    @Column(name = "Data_DO")
    private Date dateTo;
    @Column(name = "Koszt", nullable = false)
    private Long cost;
}

