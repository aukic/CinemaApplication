package ferit.cinema.feature.user;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UserDto implements Serializable {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Date dateOfBirth;
}
