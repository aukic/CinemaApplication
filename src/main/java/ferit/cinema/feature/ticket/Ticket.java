package ferit.cinema.feature.ticket;

import ferit.cinema.feature.movieauditorium.MovieAuditorium;
import ferit.cinema.feature.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_auditorium_id", nullable = false)
    private MovieAuditorium movieAuditorium;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "price", length = 1)
    private String price;

}