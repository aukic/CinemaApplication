package ferit.cinema.web;

import ferit.cinema.feature.user.User;
import ferit.cinema.feature.user.UserDto;
import ferit.cinema.feature.user.UserRepository;
import ferit.cinema.feature.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @RequestMapping("/{userEmail}")
    public UserDto getUserByEmail(@PathVariable String userEmail) throws AccountNotFoundException {
        UserDto userDto;
        try{
            userDto = userService.getUserByEmail(userEmail);
        }catch (IllegalStateException e){
            throw new AccountNotFoundException();
        }
        return userDto;
    }
}
