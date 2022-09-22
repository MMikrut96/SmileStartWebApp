package pl.edu.wat.projektai.ai.dto;

import lombok.Data;
import pl.edu.wat.projektai.ai.models.User;

@Data
public class UserDto {


    private String login;

    private String password;

    private String token;

    public UserDto(User user){
        setLogin(user.getLogin());
        setPassword(user.getPassword());
    }
}
