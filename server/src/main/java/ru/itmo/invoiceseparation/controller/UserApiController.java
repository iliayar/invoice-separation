package ru.itmo.invoiceseparation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.invoiceseparation.api.UserApi;
import ru.itmo.invoiceseparation.model.Credentials;

@RestController
public class UserApiController implements UserApi {

    public ResponseEntity<Void> userLoginPost(Credentials body) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> userRegisterPost(Credentials body) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
