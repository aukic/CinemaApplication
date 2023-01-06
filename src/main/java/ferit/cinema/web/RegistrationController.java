package ferit.cinema.web;

import ferit.cinema.domain.RegistrationRequest;
import ferit.cinema.domain.RegistrationService;
import ferit.cinema.feature.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<User> register(@RequestBody RegistrationRequest request){
        User registeredUser = registrationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
