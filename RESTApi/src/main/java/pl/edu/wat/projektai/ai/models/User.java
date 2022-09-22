package pl.edu.wat.projektai.ai.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Uzytkownik")
@SequenceGenerator(name = "s_id", initialValue = 100, sequenceName = "seq_id", allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_id")
    @Column(name = "ID_Uzytkownika", unique = true, nullable = false)
    private Long id;
    @Column(name = "Login", nullable = false)
    private String login;
    @Column(name = "Haslo", nullable = false)
    private String password;
}
