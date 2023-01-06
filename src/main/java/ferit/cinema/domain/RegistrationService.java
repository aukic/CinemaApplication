package ferit.cinema.domain;

import ferit.cinema.feature.user.AppUserRole;
import ferit.cinema.feature.user.User;
import ferit.cinema.feature.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public User register(RegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email is not valid");
        }
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                new Timestamp(System.currentTimeMillis()),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        );
        userService.signUpUser(user);

        return user;
    }
}
