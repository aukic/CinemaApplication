package ferit.cinema.feature.user;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UserDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Double loyaltyPoints;
    private AppUserRole role;
}
