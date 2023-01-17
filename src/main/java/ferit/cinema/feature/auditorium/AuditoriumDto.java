package ferit.cinema.feature.auditorium;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuditoriumDto implements Serializable {
    private final Long id;
    private final String name;
    private final Integer seatsNo;
}
