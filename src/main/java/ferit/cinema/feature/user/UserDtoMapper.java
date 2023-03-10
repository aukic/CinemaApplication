package ferit.cinema.feature.user;

import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public UserDto map(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setLoyaltyPoints(user.getLoyaltyPoints());
        userDto.setRole(user.getAppUserRole());
        return userDto;
    }
}
