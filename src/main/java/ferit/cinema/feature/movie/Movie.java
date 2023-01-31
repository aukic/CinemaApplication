package ferit.cinema.feature.movie;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration_min")
    private Integer duration_min;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "trailer")
    private String trailer;
}
