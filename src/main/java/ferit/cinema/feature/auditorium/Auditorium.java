package ferit.cinema.feature.auditorium;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "auditorium")
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 1)
    private String name;

    @Column(name = "seats_no", nullable = false)
    private Integer seatsNo;

    @Column(name = "\"timestamp\"")
    private LocalDateTime timestamp;
}