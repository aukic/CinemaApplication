package ferit.cinema.feature.user.service;

import ferit.cinema.feature.user.User;
import ferit.cinema.feature.user.UserDto;
import ferit.cinema.feature.user.UserDtoMapper;
import ferit.cinema.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return userDtoMapper.map(user);
    }
}
