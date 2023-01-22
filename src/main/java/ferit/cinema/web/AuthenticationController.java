package ferit.cinema.web;

import ferit.cinema.domain.AuthenticationRequest;
import ferit.cinema.domain.AuthenticationResponse;
import ferit.cinema.domain.RegistrationRequest;
import ferit.cinema.domain.AuthenticationService;
import ferit.cinema.feature.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
