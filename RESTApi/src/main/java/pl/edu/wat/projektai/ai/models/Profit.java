package pl.edu.wat.projektai.ai.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "Zarobek")
@SequenceGenerator(name = "s_id", initialValue = 100, sequenceName = "seq_id", allocationSize = 1)
public class Profit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_id")
    @Column(name = "ID_Zarobku", unique = true, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ID_Kierowcy", nullable = false)
    private Driver driver;
    @Column(name = "Data", nullable = false)
    private Date date;
    @Column(name = "Wysokosc_Zarobku", nullable = false)
    private Long income;
}
