package ferit.cinema.feature.auditorium;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuditoriumDto implements Serializable {
    private final Integer id;
    private final String name;
    private final Integer seatsNo;
}
