package ferit.cinema.feature.user.service;

import ferit.cinema.feature.user.UserDto;

public interface UserService {
    UserDto getUserByEmail(String email);
}
