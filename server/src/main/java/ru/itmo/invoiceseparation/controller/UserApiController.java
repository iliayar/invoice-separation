package ru.itmo.invoiceseparation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.invoiceseparation.api.UserApi;
import ru.itmo.invoiceseparation.entities.User;
import ru.itmo.invoiceseparation.entities.UserRepository;
import ru.itmo.invoiceseparation.model.Credentials;

@RestController
public class UserApiController implements UserApi {

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    public ResponseEntity<Void> userLoginPost(Credentials body) {
        for (User user : userRepository.findAll()) {
            log.info(user.toString());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> userRegisterPost(Credentials body) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
