package pl.edu.wat.projektai.ai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.projektai.ai.models.User;
import pl.edu.wat.projektai.ai.services.AuthorizationService;

@RestController
public class UserController {

    private static AuthorizationService authorizationService;

    @Autowired
    public UserController(AuthorizationService authorizationService){this.authorizationService = authorizationService;}

    @PostMapping("/add-user")
    public ResponseEntity<User> addUser(String login){
        return new ResponseEntity<User>(authorizationService.addUser(login), HttpStatus.OK);
    }
}
