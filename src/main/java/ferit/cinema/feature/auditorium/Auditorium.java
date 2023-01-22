package ferit.cinema.feature.auditorium;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "auditorium")
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 1)
    private String name;

    @Column(name = "seats_no", nullable = false)
    private Integer seatsNo;

    @Column(name = "\"timestamp\"")
    private LocalDateTime timestamp;
}