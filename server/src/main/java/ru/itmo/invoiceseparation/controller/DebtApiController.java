package ru.itmo.invoiceseparation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.invoiceseparation.api.DebtApi;

@RestController
public class DebtApiController implements DebtApi {

    public String debtGet(String userId) {
        return userId;
    }

    public ResponseEntity<Void> debtPost(String body) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}